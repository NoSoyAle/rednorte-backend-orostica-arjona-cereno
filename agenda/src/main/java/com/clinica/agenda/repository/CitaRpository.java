package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.agenda.entities.Cita;

<<<<<<< HEAD
=======

>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import java.time.LocalDate;
import java.util.List;

public interface CitaRpository extends JpaRepository <Cita, Long> {
<<<<<<< HEAD
    List<Cita> findByDoctorId(Long doctorId);    
    List<Cita> findByDoctorIdAndFecha(
=======
    List<Cita> findByDoctor_Id(Long doctorId);   

    List<Cita> findByDoctor_IdAndFecha(
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
        Long doctorId,
        LocalDate fecha);
}
