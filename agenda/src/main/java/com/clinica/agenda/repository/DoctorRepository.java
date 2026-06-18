package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.agenda.entities.Doctor;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByEspecialidad_Id(
        Long especialidadId);
}
