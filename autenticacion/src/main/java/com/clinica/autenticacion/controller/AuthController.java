package com.clinica.autenticacion.controller;

import com.clinica.autenticacion.dto.AuthRequest;
import com.clinica.autenticacion.dto.AuthResponse;
import com.clinica.autenticacion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
