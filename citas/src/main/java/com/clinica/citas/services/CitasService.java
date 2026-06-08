package com.clinica.citas.services;
import java.util.List;
import com.clinica.citas.entities.Cita;

public interface CitasService {
    List<Cita> listarCitas();
    Cita crearCita(Cita cita);
    Cita buscarCita(Long id);
    void eliminarCita(Long id);
}
