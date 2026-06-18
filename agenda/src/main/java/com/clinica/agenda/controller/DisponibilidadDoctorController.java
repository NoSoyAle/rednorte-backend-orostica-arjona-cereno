package com.clinica.agenda.controller;

import com.clinica.agenda.entities.DisponibilidadDoctor;
import com.clinica.agenda.services.DisponibilidadDoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
<<<<<<< HEAD
=======
@CrossOrigin(origins = "http://localhost:5173")
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
public class DisponibilidadDoctorController {

    private final DisponibilidadDoctorService service;

    public DisponibilidadDoctorController(
            DisponibilidadDoctorService service) {

        this.service = service;
    }
 
    @GetMapping
    public List<DisponibilidadDoctor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public DisponibilidadDoctor buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<DisponibilidadDoctor> buscarPorDoctor(
            @PathVariable Long doctorId) {

        return service.buscarPorDoctor(doctorId);
    }

    @PostMapping
    public DisponibilidadDoctor guardar(
            @RequestBody DisponibilidadDoctor disponibilidadDoctor) {

        return service.guardar(disponibilidadDoctor);
    }

    @PutMapping("/{id}")
    public DisponibilidadDoctor actualizar(
            @PathVariable Long id,
            @RequestBody DisponibilidadDoctor disponibilidadDoctor) {

        return service.actualizar(id, disponibilidadDoctor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }
}