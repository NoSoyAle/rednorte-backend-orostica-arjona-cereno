package com.clinica.agenda.services;
import java.util.List;
import com.clinica.agenda.entities.Doctor;


public interface DoctorServices {
    List<Doctor> listarDoctores();

    Doctor crearDoctor(Doctor doctor);
    
    Doctor buscarDoctor(Long id);
  

    void eliminarDoctor(Long id);
    //Este metodo no devuelve nada, solo elimina



}
