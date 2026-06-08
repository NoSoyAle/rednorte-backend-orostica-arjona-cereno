package com.clinica.agenda.controller;

import com.clinica.agenda.entities.BloqueHorario;
import com.clinica.agenda.services.BloqueHorarioServices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.clinica.agenda.repository.BloqueHorarioRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bloques")
public class BloqueHorarioController {

    private final BloqueHorarioServices bloqueHorarioServices;
    private final BloqueHorarioRepository bloqueHorarioRepository;

    public BloqueHorarioController(
            BloqueHorarioServices bloqueHorarioServices,
            BloqueHorarioRepository bloqueHorarioRepository) {

        this.bloqueHorarioServices = bloqueHorarioServices;
        this.bloqueHorarioRepository = bloqueHorarioRepository;
    }

    @GetMapping
    public List<BloqueHorario> listar() {
        return bloqueHorarioServices.listarTodos();
    }

    @GetMapping("/{id}")
    public BloqueHorario buscar(
            @PathVariable Long id) {

        return bloqueHorarioRepository.findById(id)
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Bloque no encontrado"));
    }

    @PostMapping
    public BloqueHorario guardar(
            @RequestBody BloqueHorario bloque) {

        return bloqueHorarioServices.guardar(bloque);
    }

    @PutMapping("/{id}")
    public BloqueHorario actualizar(
            @PathVariable Long id,
            @RequestBody BloqueHorario bloque) {

        return bloqueHorarioServices.actualizar(
                id,
                bloque);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        bloqueHorarioServices.eliminar(id);
    }

    @GetMapping("/doctor/{doctorId}/fecha/{fecha}")
    public List<BloqueHorario> porDoctorYFecha(
            @PathVariable Long doctorId,
            @PathVariable String fecha) {

        return bloqueHorarioServices
                .porDoctorYFecha(
                        doctorId,
                        LocalDate.parse(fecha));
    }

    @GetMapping("/doctor/{doctorId}")
    public List<BloqueHorario> obtenerBloquesDoctor(
            @PathVariable Long doctorId) {

        return bloqueHorarioServices
                .obtenerBloquesDoctor(doctorId);
    }
}