package com.clinica.agenda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.clinica.agenda.enums.Sexo;
import com.clinica.agenda.enums.EstadoCita;
import lombok.Getter;
import lombok.NoArgsConstructor;    
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.EnumType;

@Entity
public class Cita {
    @Id
    private Long id;

   @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "bloque_horario_id")
    private BloqueHorario bloqueHorario;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Long pacienteId;
    private String nombrePaciente;

}
