package com.clinica.agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.agenda.entities.EspecialidadDoctor;
import com.clinica.agenda.repository.EspecialidadDoctorRepository;

@Service
public class EspDoServicesImplement implements EspDocService {

    @Autowired
    private EspecialidadDoctorRepository especialidadDoctorRepository;

    @Override
    public List<EspecialidadDoctor> listarEspecialidadesDoctores() {
        return especialidadDoctorRepository.findAll();
    }

    @Override
    public EspecialidadDoctor crearVinculacionEspecialidadDoctor(EspecialidadDoctor especialidadDoctor) {
        return especialidadDoctorRepository.save(especialidadDoctor);
    }

    @Override
    public Optional<EspecialidadDoctor> buscarEspecialidadDoctor(Long id) {
        return especialidadDoctorRepository.findById(id);
    }

    @Override
    public void DesvincularEspecialidadDoctor(Long id) {
        especialidadDoctorRepository.deleteById(id);
    }
}