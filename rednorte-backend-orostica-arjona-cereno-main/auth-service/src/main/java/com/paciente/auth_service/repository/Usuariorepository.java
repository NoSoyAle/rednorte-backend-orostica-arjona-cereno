package com.paciente.auth_service.repository;

import com.paciente.auth_service.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Usuariorepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByRut(String rut);
}