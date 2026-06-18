package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.entities.DisponibilidadDoctor;
import com.clinica.agenda.repository.CitaRpository;

import org.springframework.web.reactive.function.client.WebClient;
import com.clinica.agenda.entities.dto.PacienteDTO;
import com.clinica.agenda.enums.DiaSemana;
import com.clinica.agenda.repository.DisponibilidadDoctorRepository;
import com.clinica.agenda.dto.NotificacionCitaDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImplement implements CitaService {

    //Debo implementar 6 metodos
    @Autowired
    private CitaRpository CitaRepo;

    @Autowired
    private DisponibilidadDoctorRepository disponibilidadDoctorRepository;

    @Autowired
    private WebClient pacienteWebClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List <Cita> listarTodos(){
        return CitaRepo.findAll();
    }

    @Override
    public Cita buscarPorId(Long id){
        return CitaRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    public Cita guardar(Cita cita){

        PacienteDTO paciente = pacienteWebClient
                .get()
                .uri("/" + cita.getPacienteId())
                .retrieve()
                .bodyToMono(PacienteDTO.class)
                .block();

        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        Cita citaGuardada = CitaRepo.save(cita);
        enviarNotificacion("CITA_CREADA", citaGuardada, paciente.getEmail(), paciente.getNombre(), paciente.getApellido());
        return citaGuardada;
    }


    @Override
    public Cita actualizar(Long id, Cita cita){
        Cita existente = buscarPorId(id);

        existente.setEstado(cita.getEstado());
        existente.setDoctor(cita.getDoctor());

        Cita citaActualizada = CitaRepo.save(existente);

        PacienteDTO paciente = pacienteWebClient
                .get()
                .uri("/" + citaActualizada.getPacienteId())
                .retrieve()
                .bodyToMono(PacienteDTO.class)
                .block();

        if (paciente != null) {
            String tipo = "CITA_CANCELADA".equals(citaActualizada.getEstado().name()) ? "CITA_CANCELADA" : "CITA_ACTUALIZADA";
            enviarNotificacion(tipo, citaActualizada, paciente.getEmail(), paciente.getNombre(), paciente.getApellido());
        }

        return citaActualizada;
    }

    @Override
    public void eliminar(Long id){
        CitaRepo.deleteById(id);
    }


    @Override
    public List<Cita> obtenerCitasDoctor(Long doctorId) {
        return CitaRepo.findByDoctorId(doctorId);
    }

    @Override
    public List<LocalTime> obtenerHorariosDisponibles(
            Long doctorId,
            LocalDate fecha) {

        DiaSemana diaSemana = DiaSemana.valueOf(
                fecha.getDayOfWeek().name());

        DisponibilidadDoctor disponibilidad =
                disponibilidadDoctorRepository.findByDoctor_IdAndDiaSemana(
                                doctorId,
                                diaSemana)
                        .stream()
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No existe disponibilidad para ese día"));

        List<Cita> citas =
                CitaRepo.findByDoctorIdAndFecha(
                        doctorId,
                        fecha);

        java.util.Set<LocalTime> horasOcupadas =
                citas.stream()
                        .map(Cita::getHoraInicio)
                        .collect(Collectors.toSet());

        List<LocalTime> disponibles = new ArrayList<>();

        LocalTime horaActual =
                disponibilidad.getHoraInicio();

        while (horaActual.isBefore(
                disponibilidad.getHoraFin())) {

            if (!horasOcupadas.contains(horaActual)) {
                disponibles.add(horaActual);
            }

            horaActual = horaActual.plusMinutes(
                    disponibilidad.getDuracionMinutos());
        }

        return disponibles;
    }

    private void enviarNotificacion(String tipo, Cita cita, String email, String nombre, String apellido) {
        try {
            NotificacionCitaDTO notificacion = new NotificacionCitaDTO();
            notificacion.setTipo(tipo);
            notificacion.setPacienteEmail(email);
            notificacion.setPacienteNombre(nombre);
            notificacion.setPacienteApellido(apellido);
            notificacion.setDoctorNombre(cita.getDoctor().getNombre());
            notificacion.setDoctorApellido(cita.getDoctor().getApellido());
            notificacion.setDoctorCorreo(cita.getDoctor().getCorreo());
            notificacion.setFecha(cita.getFecha().toString());
            notificacion.setHoraInicio(cita.getHoraInicio().toString());
            notificacion.setHoraFin(cita.getHoraFin().toString());
            notificacion.setEstado(cita.getEstado().name());

            String mensajeJson = objectMapper.writeValueAsString(notificacion);
            rabbitMQProducer.enviarMensaje(mensajeJson);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación: " + e.getMessage());
        }
    }
}
    