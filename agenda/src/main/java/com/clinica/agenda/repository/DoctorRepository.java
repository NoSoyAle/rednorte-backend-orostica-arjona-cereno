package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clinica.agenda.entities.Doctor;
<<<<<<< HEAD

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

=======
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByEspecialidades_Id(
        Long especialidadId);
        
        Optional<Doctor> findByRut(String rut);
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
}
