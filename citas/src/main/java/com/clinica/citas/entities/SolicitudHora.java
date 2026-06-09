package com.clinica.citas.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.clinica.citas.enums.Prioridad;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class SolicitudHora {
 

    @Id
    private Long id;

    private LocalDate fechaDeseada;

    private LocalTime horaDeseada;

    private Boolean activa;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
}
