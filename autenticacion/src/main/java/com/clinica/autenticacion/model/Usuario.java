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

    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String telefono;
    private String estado;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getNombre() {
      return nombre;
    }
    
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

    public String getEmail() {
      return email;
    }
    
    public void setEmail(String email) {
      this.email = email;
    }

    public String getTelefono(){
      return telefono;
    }

    public void setTelefono (String telefono){
      this.telefono = telefono;
    }

    public Usuario(String nombre, String email, String password, String rol, String estado, String telefono) {
      this.nombre = nombre;
      this.email = email;
      this.password = password;
      this.rol = rol;
      this.estado = estado;
      this.telefono = telefono;
    }

}
