package com.paciente.auth_service.controller;

import com.paciente.auth_service.Model.Usuario;
import com.paciente.auth_service.security.JwtUtil;
import com.paciente.auth_service.dto.LoginRequest;
import com.paciente.auth_service.dto.LoginResponse;
import com.paciente.auth_service.repository.Usuariorepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Usuariorepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByRut(request.getRut())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtUtil.generateToken(usuario.getRut(), usuario.getRol().name());
        return ResponseEntity.ok(new LoginResponse(token, usuario.getRol().name()));
    }
}