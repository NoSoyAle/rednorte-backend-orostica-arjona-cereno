package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.repository.CitaRpository;
import com.clinica.agenda.entities.Cita;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaServiceImplement implements CitaService {

    //Debo implementar 6 metodos
    @Autowired
    private CitaRpository CitaRepo;

    @Override
    public List <Cita> listarTodos(){
        return CitaRepo.findAll();
    }

    @Override
    public Cita buscarPorId(Long id){
        return CitaRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    public Cita guardar(Cita Cita){
        return CitaRepo.save(Cita);
    }

    @Override
    public Cita actualizar(Long id, Cita Cita){
        Cita existente = buscarPorId(id);

        existente.setEstado(Cita.getEstado());
        existente.setDoctor(Cita.getDoctor());

        return CitaRepo.save(existente);
    }

    @Override
    public void eliminar(Long id){
        CitaRepo.deleteById(id);
    }


    @Override
    public List<Cita> obtenerCitasDoctor(Long doctorId) {
        return CitaRepo.findByDoctorId(doctorId);
    }
}
    