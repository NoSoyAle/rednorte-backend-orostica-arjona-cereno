package com.clinica.citas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.clinica.citas.repository.CitaRepositorie;
import com.clinica.citas.entities.Cita;
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
    public Cita crearCita(Cita cita) {
        return citasRepository.save(cita);
    }

    @Override
    public Cita buscarCita(Long id) {
        return citasRepository.findById(id).orElse(null);
    }   

    @Override
    public void eliminarCita(Long id) {
        citasRepository.deleteById(id);
    }   

}
