package com.clinica.citas.services;
import java.util.List;
import com.clinica.citas.entities.Cita;
import com.clinica.citas.enums.EstadoCita;

public interface CitasService {
    List<Cita> listarCitas();
    List<Cita> listarCitasPorEstado(EstadoCita estado);
    List<Cita> listarCitasConfirmadas();
    Cita crearCita(Cita cita);
    Cita buscarCita(Long id);
    Cita actualizarEstadoCita(Long id, Cita cita);
    void eliminarCita(Long id);
}
