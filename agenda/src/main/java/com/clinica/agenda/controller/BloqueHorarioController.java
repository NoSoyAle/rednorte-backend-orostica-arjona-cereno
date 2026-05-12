package com.clinica.agenda.controller;


import com.clinica.agenda.entities.BloqueHorario;
import com.clinica.agenda.services.BloqueHorarioServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bloques")

public class BloqueHorarioController {

    private final BloqueHorarioServices BloqueHorarioServices;

    public BloqueHorarioController(BloqueHorarioServices service) {
        this.BloqueHorarioServices = service;
    }

    @GetMapping
    public List<BloqueHorario> listar() {
        return BloqueHorarioServices.listarTodos();
    }

    @GetMapping("/{id}")
    public BloqueHorario buscar(@PathVariable Long id) {
        return BloqueHorarioServices.buscarPorId(id);
    }

    @PostMapping
    public BloqueHorario guardar(@RequestBody BloqueHorario bloque) {
        return BloqueHorarioServices.guardar(bloque);
    }

    @PutMapping("/{id}")
    public BloqueHorario actualizar(@PathVariable Long id,
                                    @RequestBody BloqueHorario bloque) {
        return BloqueHorarioServices.actualizar(id, bloque);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        BloqueHorarioServices.eliminar(id);
    }

    @GetMapping("/doctor/{doctorId}/fecha/{fecha}")
    public List<BloqueHorario> porDoctorYFecha(
            @PathVariable Long doctorId,
            @PathVariable String fecha) {

        return BloqueHorarioServices.porDoctorYFecha(doctorId, LocalDate.parse(fecha));
    }
}

