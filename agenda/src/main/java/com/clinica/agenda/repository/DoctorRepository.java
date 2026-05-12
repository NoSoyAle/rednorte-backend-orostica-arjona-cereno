package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.agenda.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
