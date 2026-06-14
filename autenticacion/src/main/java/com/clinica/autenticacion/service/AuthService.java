package com.clinica.autenticacion.service;

import com.clinica.autenticacion.dto.AuthResponse;
import com.clinica.autenticacion.model.Usuario;
import com.clinica.autenticacion.repository.UsuarioRepository;
import com.clinica.autenticacion.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(String nombre, String password) {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + nombre));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contrasena incorrecta para usuario: " + nombre);
        }

        String token = jwtUtil.generateToken(usuario.getNombre(), usuario.getRol());
        
        return new AuthResponse("Login exitoso", token, usuario.getRol(), usuario.getNombre());
    }
}
