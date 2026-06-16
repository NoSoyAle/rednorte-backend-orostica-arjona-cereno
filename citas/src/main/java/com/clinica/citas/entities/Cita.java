package com.clinica.citas.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import com.clinica.citas.enums.EstadoCita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Long doctorId;
    private Long pacienteId;
    private Long especialidadId;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivoCancelacion;
    private Boolean confirmada;
}
