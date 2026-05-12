package com.clinica.agenda.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import com.clinica.agenda.entities.Doctor;
import com.clinica.agenda.entities.Especialidad;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;    
import lombok.Setter;   
import lombok.AllArgsConstructor;   

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EspecialidadDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private Long especialidadId;
    private LocalDate fechaEgreso;

    
}
