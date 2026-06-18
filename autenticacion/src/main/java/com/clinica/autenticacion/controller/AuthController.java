package com.clinica.autenticacion.controller;

import com.clinica.autenticacion.dto.AuthRequest;
import com.clinica.autenticacion.dto.AuthResponse;
<<<<<<< HEAD
=======
import com.clinica.autenticacion.model.Usuario;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import com.clinica.autenticacion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.util.Map;

>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = service.login(request.getNombre(), request.getPassword());
        return ResponseEntity.ok(response);
    }
<<<<<<< HEAD
=======

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> data) {
        Usuario usuario = service.register(data);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado", "nombre", usuario.getNombre(), "rol", usuario.getRol()));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Map<String, String> data) {
        data.put("rol", "ADMIN");
        data.putIfAbsent("estado", "activo");
        Usuario usuario = service.register(data);
        return ResponseEntity.ok(Map.of("message", "Admin registrado", "nombre", usuario.getNombre(), "rol", usuario.getRol()));
    }
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
}
