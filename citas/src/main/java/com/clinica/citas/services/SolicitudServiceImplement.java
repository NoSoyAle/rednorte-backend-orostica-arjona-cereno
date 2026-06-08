package com.clinica.citas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.citas.repository.SolicitudRepositorie;
import com.clinica.citas.entities.SolicitudHora;

@Service
public class SolicitudServiceImplement implements SolicitudService {
    @Autowired 
    private SolicitudRepositorie solicitudRepository;

    @Override
    public List<SolicitudHora> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Override
    public SolicitudHora crearSolicitud(SolicitudHora solicitud) {
        return solicitudRepository.save(solicitud);
    }

    @Override
    public SolicitudHora buscarSolicitud(Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }

}
