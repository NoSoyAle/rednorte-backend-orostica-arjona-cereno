package com.clinica.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.citas.services.CitasService;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import com.clinica.citas.entities.Cita;
import com.clinica.citas.enums.EstadoCita;

@RestController
@RequestMapping("/api/cita")
public class CitaController {
    @Autowired
    private CitasService citaService;

    @GetMapping
    public List<Cita> getCitas() {
        return citaService.listarCitas();
    }

    @GetMapping("/{id}")
    public Cita getCita(@PathVariable Long id) {
        return citaService.buscarCita(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Cita> getCitasByEstado(@PathVariable EstadoCita estado) {
        return citaService.listarCitasPorEstado(estado);
    }

    @GetMapping("/confirmadas")
    public List<Cita> getCitasConfirmadas() {
        return citaService.listarCitasConfirmadas();
    }

    @PostMapping
    public Cita createCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    @PutMapping("/{id}/estado")
    public Cita actualizarEstadoCita(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.actualizarEstadoCita(id, cita);
    }

    @DeleteMapping("/{id}")
    public void deleteCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
    }

}
