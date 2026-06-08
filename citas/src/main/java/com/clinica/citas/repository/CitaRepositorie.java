package com.clinica.citas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.citas.entities.Cita;

public interface CitaRepositorie  extends JpaRepository<Cita, Long> {

}
