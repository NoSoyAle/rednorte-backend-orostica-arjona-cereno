package com.clinica.citas.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.citas.entities.Cita;
import com.clinica.citas.enums.EstadoCita;

public interface CitaRepositorie  extends JpaRepository<Cita, Long> {
    List<Cita> findByEstado(EstadoCita estado);
    List<Cita> findByConfirmadaTrue();
    List<Cita> findByEspecialidadId(Long especialidadId);
}
