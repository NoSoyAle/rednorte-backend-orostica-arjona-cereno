package com.clinica.agenda.services;

import com.clinica.agenda.entities.BloqueHorario;

import java.time.LocalDate;
import java.util.List;
public interface BloqueHorarioServices {
    
    List<BloqueHorario> listarTodos();

    BloqueHorario buscarPorId(Long id);

    BloqueHorario guardar(BloqueHorario bloque);

    BloqueHorario actualizar(Long id, BloqueHorario bloque);

    void eliminar(Long id);

    List<BloqueHorario> porDoctorYFecha(Long doctorId, LocalDate fecha);

}
