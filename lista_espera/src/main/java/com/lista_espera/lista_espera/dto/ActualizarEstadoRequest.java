package com.lista_espera.lista_espera.dto;

import com.lista_espera.lista_espera.Model.EstadoEspera;

public class ActualizarEstadoRequest {

    private EstadoEspera estado;
    private String observaciones;

    public EstadoEspera getEstado() {
        return estado;
    }

    public void setEstado(EstadoEspera estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
