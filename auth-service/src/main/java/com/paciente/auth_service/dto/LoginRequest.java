package com.paciente.auth_service.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String rut;
    private String password;
}
