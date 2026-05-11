package com.paciente.auth_service.service;

import java.util.List;

import com.paciente.auth_service.Model.Paciente;

public interface PacienteService {

    List<Paciente> findAll();

    Paciente findById(Long id);

    Paciente save(Paciente paciente);

    void deletebyId(Long id);
}
