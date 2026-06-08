package com.lista_espera.lista_espera.dto;

public class AsignarHoraRequest {

    private Long doctorId;
    private Long bloqueHorarioId;
    private String observaciones;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getBloqueHorarioId() {
        return bloqueHorarioId;
    }

    public void setBloqueHorarioId(Long bloqueHorarioId) {
        this.bloqueHorarioId = bloqueHorarioId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
