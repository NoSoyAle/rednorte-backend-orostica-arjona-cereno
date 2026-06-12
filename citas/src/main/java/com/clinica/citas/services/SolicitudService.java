package com.clinica.citas.services;
import java.util.List;
import com.clinica.citas.entities.SolicitudHora;

public interface SolicitudService {
    List <SolicitudHora> listarSolicitudes();
    SolicitudHora crearSolicitud(SolicitudHora solicitud);
    SolicitudHora buscarSolicitud(Long id);
    void eliminarSolicitud(Long id);

}
