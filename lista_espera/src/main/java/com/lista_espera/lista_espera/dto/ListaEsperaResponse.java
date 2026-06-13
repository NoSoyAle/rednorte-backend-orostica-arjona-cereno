package com.lista_espera.lista_espera.dto;

import java.time.LocalDateTime;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;

public class ListaEsperaResponse {

    private Long id;
    private Long pacienteId;
    private Long especialidadId;
    private Long doctorId;
    private Long bloqueHorarioId;
    private Integer prioridad;
    private EstadoEspera estado;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaAsignacion;
    private String motivo;
    private String observaciones;

    public static ListaEsperaResponse fromEntity(ListaEspera listaEspera) {
        ListaEsperaResponse response = new ListaEsperaResponse();
        response.setId(listaEspera.getId());
        response.setPacienteId(listaEspera.getPacienteId());
        response.setEspecialidadId(listaEspera.getEspecialidadId());
        response.setDoctorId(listaEspera.getDoctorId());
        response.setBloqueHorarioId(listaEspera.getBloqueHorarioId());
        response.setPrioridad(listaEspera.getPrioridad());
        response.setEstado(listaEspera.getEstado());
        response.setFechaIngreso(listaEspera.getFechaIngreso());
        response.setFechaActualizacion(listaEspera.getFechaActualizacion());
        response.setFechaAsignacion(listaEspera.getFechaAsignacion());
        response.setMotivo(listaEspera.getMotivo());
        response.setObservaciones(listaEspera.getObservaciones());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

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

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoEspera getEstado() {
        return estado;
    }

    public void setEstado(EstadoEspera estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
