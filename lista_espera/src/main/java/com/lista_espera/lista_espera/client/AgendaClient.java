package com.lista_espera.lista_espera.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.lista_espera.lista_espera.dto.BloqueHorarioDto;

@Service
public class AgendaClient {

    private final WebClient webClient;

    public AgendaClient(WebClient.Builder builder) {

        this.webClient = builder
                .baseUrl("http://localhost:8081") //cambiar x puerto de agend
                .build();
    }

    public List<BloqueHorarioDto> obtenerBloquesDoctor(
            Long doctorId) {

        return webClient
                .get()
                .uri("/api/bloques/doctor/{doctorId}",
                        doctorId)
                .retrieve()
                .bodyToFlux(BloqueHorarioDto.class)
                .collectList()
                .block();
    }
}