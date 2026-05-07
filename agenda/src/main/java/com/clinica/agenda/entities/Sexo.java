package com.clinica.agenda.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;  
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.clinica.agenda.entities.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sexo")
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSexo;

    private String nombreSexo;

    @OneToMany(mappedBy = "sexo")
    @JsonIgnore
    private List<Doctor> doctores;
}
