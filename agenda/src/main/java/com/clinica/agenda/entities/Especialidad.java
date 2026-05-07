package com.clinica.agenda.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;  
import java.util.List;
import com.clinica.agenda.entities.EspecialidadDoctor;
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
public class Especialidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;

    private String nombreEsp;

    @OneToMany(mappedBy = "especialidad")
    @JsonIgnore
    private List<EspecialidadDoctor> doctorEspecialidades;

 

}
