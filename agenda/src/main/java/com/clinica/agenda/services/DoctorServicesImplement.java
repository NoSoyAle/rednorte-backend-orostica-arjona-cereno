package com.clinica.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.agenda.repository.DoctorRepository;
import com.clinica.agenda.entities.Doctor;
import java.util.List;  

@Service
public class DoctorServicesImplement implements DoctorServices {
    
    @Autowired 
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor crearDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor buscarDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

}
