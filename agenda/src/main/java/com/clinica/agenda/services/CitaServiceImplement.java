package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.repository.CitaRpository;

import org.springframework.web.reactive.function.client.WebClient;
import com.clinica.agenda.entities.dto.PacienteDTO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImplement implements CitaService {

    //Debo implementar 6 metodos
    @Autowired
    private CitaRpository CitaRepo;

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

        cita.setNombrePaciente(
                paciente.getNombre() + " " +
                paciente.getApellido());

        cita.setRutPaciente(
                paciente.getRut());

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
}
    