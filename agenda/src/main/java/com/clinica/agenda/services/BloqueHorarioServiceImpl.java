package com.clinica.agenda.services;
import com.clinica.agenda.entities.BloqueHorario;
import com.clinica.agenda.repository.BloqueHorarioRepository;
import com.clinica.agenda.services.BloqueHorarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    
import java.time.LocalDate;
import java.util.List;

@Service
public class BloqueHorarioServiceImpl implements BloqueHorarioServices {

    @Autowired
    private BloqueHorarioRepository BloqueHorariorepository;


    @Override
    public List<BloqueHorario> listarTodos() {
        return BloqueHorariorepository.findAll();
    }

    @Override
    public BloqueHorario buscarPorId(Long id) {
        return BloqueHorariorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));
    }

    @Override
    public BloqueHorario guardar(BloqueHorario bloque) {
        return BloqueHorariorepository.save(bloque);
    }

    @Override
    public BloqueHorario actualizar(Long id, BloqueHorario bloque) {
        BloqueHorario existente = buscarPorId(id);

        existente.setFecha(bloque.getFecha());
        existente.setHoraInicio(bloque.getHoraInicio());
        existente.setHoraFin(bloque.getHoraFin());
        existente.setEstado(bloque.getEstado());
        existente.setDoctorId(bloque.getDoctorId());
        existente.setPacienteId(bloque.getPacienteId());

        return BloqueHorariorepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        BloqueHorariorepository.deleteById(id);
    }

    @Override
    public List<BloqueHorario> porDoctorYFecha(Long doctorId, LocalDate fecha) {
        return BloqueHorariorepository.findByDoctorIdAndFecha(doctorId, fecha);
    }
}

