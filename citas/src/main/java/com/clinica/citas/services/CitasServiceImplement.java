package com.clinica.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.clinica.citas.repository.CitaRepositorie;
import com.clinica.citas.entities.Cita;
import com.clinica.citas.enums.EstadoCita;
import java.util.List;

@Service
public class CitasServiceImplement implements CitasService {
    @Autowired
    private CitaRepositorie citasRepository;

    @Autowired
    private WebClient webClient; 
    
    @Override
    public List<Cita> listarCitas() {
        return citasRepository.findAll();
    }

    @Override
    public List<Cita> listarCitasPorEstado(EstadoCita estado) {
        return citasRepository.findByEstado(estado);
    }

    @Override
    public List<Cita> listarCitasConfirmadas() {
        return citasRepository.findByConfirmadaTrue();
    }

    @Override
    public Cita crearCita(Cita cita) {
        return citasRepository.save(cita);
    }

    @Override
    public Cita buscarCita(Long id) {
        return citasRepository.findById(id).orElse(null);
    }

    @Override
    public Cita actualizarEstadoCita(Long id, Cita cita) {
        Cita existingCita = citasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        
        existingCita.setEstado(cita.getEstado());
        if (cita.getMotivoCancelacion() != null) {
            existingCita.setMotivoCancelacion(cita.getMotivoCancelacion());
        }
        if (cita.getConfirmada() != null) {
            existingCita.setConfirmada(cita.getConfirmada());
        }
        
        return citasRepository.save(existingCita);
    }

    @Override
    public void eliminarCita(Long id) {
        citasRepository.deleteById(id);
    }   

}
