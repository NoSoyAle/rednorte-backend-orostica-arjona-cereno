package com.lista_espera.lista_espera.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_espera")
public class ListaEspera {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;       // referencia al microservicio paciente
    private String especialidad;
    private Integer prioridad;     // 1=alta, 2=media, 3=baja

    @Enumerated(EnumType.STRING)
    private EstadoEspera estado;   // ESPERANDO, ASIGNADO, CANCELADO

    private LocalDateTime fechaIngreso;
    private String observaciones;
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
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    public ListaEspera() {
    }

    
    public ListaEspera(Long id, Long pacienteId, String especialidad, Integer prioridad, EstadoEspera estado,
            LocalDateTime fechaIngreso, String observaciones) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.especialidad = especialidad;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.observaciones = observaciones;
    }

    
    
}
