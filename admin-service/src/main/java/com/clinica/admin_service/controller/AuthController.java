package com.clinica.admin_service.controller;

import com.clinica.admin_service.model.Rol;
import com.clinica.admin_service.model.Usuario;
import com.clinica.admin_service.repository.UsuarioRepository;
import com.clinica.admin_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), "ROLE_" + usuario.getRol().name());
        return ResponseEntity.ok(Map.of("token", token, "role", usuario.getRol().name()));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String password = data.get("password");
        String nombre = data.getOrDefault("nombre", "Admin");
        String rut = data.getOrDefault("rut", "11111111-1");

        if (usuarioRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email ya registrado"));
        }

        Usuario admin = new Usuario();
        admin.setNombre(nombre);
        admin.setRut(rut);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setTelefono("911111111");
        admin.setRol(Rol.ADMIN);
        admin.setEstado("activo");

        usuarioRepository.save(admin);
        String token = jwtUtil.generateToken(admin.getEmail(), "ROLE_ADMIN");
        return ResponseEntity.ok(Map.of("token", token, "role", "ADMIN"));
    }
}
