package com.clinica.agenda.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.agenda.entities.Doctor;
import com.clinica.agenda.services.DoctorServices;

import java.util.List;  

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
        private final DoctorServices doctorServices;

    public DoctorController(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    // Obtener todos en forma lista
    @GetMapping
    public List<Doctor> listarDoctores() {
        return doctorServices.listarDoctores();
    }

    // Obtener por id
    @GetMapping("/{id}")
    public Doctor obtenerDoctor(@PathVariable Long id) {
        return doctorServices.buscarDoctor(id);
    }

    // Crear doctor o modificar doctor existente
    @PostMapping
    public Doctor guardarDoctor(@RequestBody Doctor doctor) {
        return doctorServices.crearDoctor(doctor);
    }

    // Eliminar doctor
    @DeleteMapping("/{id}")
    public void eliminarDoctor(@PathVariable Long id) {
        doctorServices.eliminarDoctor(id);
    }

}
