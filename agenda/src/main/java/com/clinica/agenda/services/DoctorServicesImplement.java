package com.clinica.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.agenda.repository.DoctorRepository;
import com.clinica.agenda.repository.EspecialidadRepository;
import com.clinica.agenda.entities.Doctor;
import com.clinica.agenda.entities.Especialidad;

import java.util.List;  

@Service
public class DoctorServicesImplement implements DoctorServices {
     
    @Autowired 
    private DoctorRepository doctorRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor crearDoctor(Doctor doctor) {

        if (doctor.getEspecialidades() != null &&
            !doctor.getEspecialidades().isEmpty()) {

            List<Especialidad> especialidadesExistentes =
                    doctor.getEspecialidades()
                            .stream()
                            .map(especialidad ->
                                    especialidadRepository.findById(
                                            especialidad.getIdEspecialidad())
                                            .orElseThrow(() ->
                                                    new RuntimeException(
                                                            "Especialidad no encontrada: "
                                                            + especialidad.getIdEspecialidad())))
                            .toList();

                doctor.setEspecialidades(especialidadesExistentes);
            }

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

    @Override
    public Doctor actualizarDoctor(Long id, Doctor doctorActualizado) {

        Doctor doctorExistente = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor no encontrado"));

        doctorExistente.setNombre(doctorActualizado.getNombre());
        doctorExistente.setApellido(doctorActualizado.getApellido());
        doctorExistente.setRut(doctorActualizado.getRut());
        doctorExistente.setFechaNac(doctorActualizado.getFechaNac());
        doctorExistente.setCorreo(doctorActualizado.getCorreo());
        doctorExistente.setTelefono(doctorActualizado.getTelefono());
        doctorExistente.setDireccion(doctorActualizado.getDireccion());
        doctorExistente.setSexo(doctorActualizado.getSexo());

        if (doctorActualizado.getEspecialidades() != null) {

            List<Especialidad> especialidadesExistentes =
                    doctorActualizado.getEspecialidades()
                            .stream()
                            .map(e -> especialidadRepository
                                    .findById(e.getIdEspecialidad())
                                    .orElseThrow(() ->
                                            new RuntimeException(
                                                    "Especialidad no encontrada: "
                                                    + e.getIdEspecialidad())))
                            .collect(java.util.stream.Collectors.toList());

            doctorExistente.getEspecialidades().clear();
            doctorExistente.getEspecialidades().addAll(especialidadesExistentes);
        }

        return doctorRepository.save(doctorExistente);
    }

}
