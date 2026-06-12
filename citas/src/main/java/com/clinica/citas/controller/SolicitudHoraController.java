package com.clinica.citas.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.clinica.citas.services.SolicitudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.clinica.citas.entities.SolicitudHora;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("/api/solicitud")
public class SolicitudHoraController {
    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public List<SolicitudHora> getSolicitudes() {
        return solicitudService.listarSolicitudes();
    }
    
    @GetMapping("/{id}")
    public SolicitudHora getSolicitud(@PathVariable Long id) {
        return solicitudService.buscarSolicitud(id);
    }

    @PostMapping
    public SolicitudHora createSolicitud(@RequestBody SolicitudHora solicitud) {
        return solicitudService.crearSolicitud(solicitud);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
    }
}
