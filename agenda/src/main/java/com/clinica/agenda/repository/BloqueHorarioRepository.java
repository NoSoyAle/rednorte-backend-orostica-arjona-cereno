package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.agenda.entities.BloqueHorario;

import java.time.LocalDate;
import java.util.List;

public interface BloqueHorarioRepository extends JpaRepository<BloqueHorario, Long> {

    List<BloqueHorario> findByDoctorIdAndFecha(Long doctorId, LocalDate fecha);

}