package com.clinica.agenda.entities;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import com.clinica.agenda.entities.Sexo;
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
@Table(name="Doctor")

public class Doctor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; 
    private String apellido;
    private String rut;
    private LocalDate fechaNac;
    private String correo;
    private String telefono;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "idSexo")
    private Sexo sexo;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<EspecialidadDoctor> doctorEspecialidades;
 

}
