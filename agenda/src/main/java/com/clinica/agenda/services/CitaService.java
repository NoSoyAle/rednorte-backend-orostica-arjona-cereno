package com.clinica.agenda.services;

import com.clinica.agenda.entities.Cita;
<<<<<<< HEAD
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
=======
import com.clinica.agenda.entities.dto.CitaDetDTO;
import com.clinica.agenda.entities.dto.PacienteAtendidoDTO;

import java.time.LocalDate;
import java.util.List;

public interface CitaService {
        List<Cita> listarTodos();

        Cita buscarPorId(Long id);

        Cita guardar(Cita cita);

        Cita actualizar(Long id, Cita cita);

        void eliminar(Long id);

        List<Cita> obtenerCitasDoctor(
        Long doctorId);

        List<java.time.LocalTime> obtenerHorariosDisponibles
        (Long doctorId,
                java.time.LocalDate fecha);

        List<CitaDetDTO> obtenerPorFecha(
                Long doctorId,
                LocalDate fecha);        

        List<PacienteAtendidoDTO> obtenerPacientesAtendidos(
        Long doctorId);
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
}
