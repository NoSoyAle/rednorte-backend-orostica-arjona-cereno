package com.clinica.agenda.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;  
import java.util.List;
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
<<<<<<< HEAD
    private Long idEspecialidad;
=======
    private Long id;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661

    private String nombreEsp;

    @ManyToMany(mappedBy = "especialidades")
    @JsonIgnore
    private List<Doctor> doctores;
}
