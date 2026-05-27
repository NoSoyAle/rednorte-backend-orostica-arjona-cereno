package com.clinica.agenda.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.agenda.entities.EspecialidadDoctor;
import com.clinica.agenda.repository.EspecialidadDoctorRepository;

@Service
public class EspDoServicesImplement  implements EspDocService{

    @Autowired
    private EspecialidadDoctorRepository EspecialidadDoctorRepository;

    @Override
    public List<EspecialidadDoctor> listarEspecialidadesDoctores() {
        return EspecialidadDoctorRepository.findAll();
    }

    @Override
    public EspecialidadDoctor crearVinculacionEspecialidadDoctor(EspecialidadDoctor especialidadDoctor) {
        return EspecialidadDoctorRepository.save(especialidadDoctor);
    }

    @Override
    public Optional<EspecialidadDoctor> buscarEspecialidadDoctor(Long id) {
        return EspecialidadDoctorRepository.findById(id);
    }

    @Override
    public void DesvincularEspecialidadDoctor(Long id) {
        EspecialidadDoctorRepository.deleteById(id);
        
    }

}
