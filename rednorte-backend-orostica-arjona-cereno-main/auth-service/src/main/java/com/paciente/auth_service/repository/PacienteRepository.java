package com.paciente.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paciente.auth_service.Model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    
} 