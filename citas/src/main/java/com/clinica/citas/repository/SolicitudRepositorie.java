package com.clinica.citas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.citas.entities.SolicitudHora;

public interface SolicitudRepositorie extends JpaRepository<SolicitudHora, Long> {

}
