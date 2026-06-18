package com.clinica.autenticacion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
=======
    @Column(unique = true)
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String telefono;
    private String estado;
}
