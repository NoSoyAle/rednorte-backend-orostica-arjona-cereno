package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.entities.DisponibilidadDoctor;
import com.clinica.agenda.repository.CitaRpository;

import org.springframework.web.reactive.function.client.WebClient;
import com.clinica.agenda.entities.dto.PacienteDTO;
import com.clinica.agenda.enums.DiaSemana;
import com.clinica.agenda.repository.DisponibilidadDoctorRepository;

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


        return CitaRepo.save(cita);
    }


    @Override
    public Cita actualizar(Long id, Cita cita){
        Cita existente = buscarPorId(id);

        existente.setEstado(cita.getEstado());
        existente.setDoctor(cita.getDoctor());

        return CitaRepo.save(existente);
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
}
    