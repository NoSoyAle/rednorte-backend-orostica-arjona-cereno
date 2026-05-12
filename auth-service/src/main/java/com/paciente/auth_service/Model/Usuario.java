package com.paciente.auth_service.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "usuarios")
@Data @NoArgsConstructor 
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rut;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Rol rol; // ADMIN, DOCTOR
}