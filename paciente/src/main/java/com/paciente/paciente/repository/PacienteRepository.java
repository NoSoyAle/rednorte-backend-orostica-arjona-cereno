package com.paciente.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paciente.paciente.Model.Paciente;
<<<<<<< HEAD

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    
=======
import java.util.Optional;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByRut(String rut);
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
} 
