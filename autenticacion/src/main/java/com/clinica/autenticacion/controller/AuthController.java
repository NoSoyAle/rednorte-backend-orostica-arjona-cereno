package com.clinica.autenticacion.controller;

import com.clinica.autenticacion.dto.AuthRequest;
import com.clinica.autenticacion.dto.AuthResponse;
import com.clinica.autenticacion.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        String response = service.register(request.getNombre(), request.getPassword());
        return new AuthResponse(response);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String response = service.login(request.getNombre(), request.getPassword());
        return new AuthResponse(response);
    }
}