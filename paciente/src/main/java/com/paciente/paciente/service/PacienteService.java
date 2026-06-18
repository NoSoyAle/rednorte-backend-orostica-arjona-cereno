package com.paciente.paciente.service;

import java.util.List;

import com.paciente.paciente.Model.Paciente;

public interface PacienteService {

    List<Paciente> findAll();

    Paciente findById(Long id);

    Paciente save(Paciente paciente);

    void deletebyId(Long id);
<<<<<<< HEAD
=======

    Paciente buscarPorRut(String rut);

    Paciente actualizar(
        Long id,
        Paciente paciente);
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
}
