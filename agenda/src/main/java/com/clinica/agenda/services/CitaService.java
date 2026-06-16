package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
import java.util.List;

public interface CitaService {
    List<Cita> listarTodos();

    Cita buscarPorId(Long id);

    Cita guardar(Cita cita);

    Cita actualizar(Long id, Cita cita);

    void eliminar(Long id);


    List<Cita> obtenerCitasDoctor(
            Long doctorId);

    List<java.time.LocalTime> obtenerHorariosDisponibles(
            Long doctorId,
            java.time.LocalDate fecha);
}
