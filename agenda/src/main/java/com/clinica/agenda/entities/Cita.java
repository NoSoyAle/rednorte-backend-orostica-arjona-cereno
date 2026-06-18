package com.clinica.agenda.entities;

<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.clinica.agenda.enums.EstadoCita;
import lombok.Getter;
import lombok.NoArgsConstructor;    
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.EnumType;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private Long pacienteId;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

<<<<<<< HEAD
}
=======
}
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
