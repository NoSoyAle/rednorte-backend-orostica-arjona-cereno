package com.lista_espera.lista_espera.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Service.ListaEsperaService;
import com.lista_espera.lista_espera.dto.ActualizarEstadoRequest;
import com.lista_espera.lista_espera.dto.AsignarHoraRequest;
import com.lista_espera.lista_espera.dto.CrearListaEsperaRequest;
import com.lista_espera.lista_espera.dto.ListaEsperaResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lista-espera")
@RequiredArgsConstructor
public class ListaEsperaController {

    private final ListaEsperaService service;

    @PostMapping
    public ResponseEntity<ListaEsperaResponse> crear(@RequestBody CrearListaEsperaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ListaEsperaResponse.fromEntity(service.crear(request)));
    }

    @GetMapping
    public ResponseEntity<List<ListaEsperaResponse>> obtenerTodos(
            @RequestParam(required = false) EstadoEspera estado) {
        if (estado != null) {
            return ResponseEntity.ok(toResponse(service.porEstado(estado)));
        }
        return ResponseEntity.ok(toResponse(service.obtenerTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaEsperaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ListaEsperaResponse.fromEntity(service.obtenerPorId(id)));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ListaEsperaResponse>> porPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(toResponse(service.porPaciente(pacienteId)));
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<List<ListaEsperaResponse>> porEspecialidad(
            @PathVariable Long especialidadId,
            @RequestParam(required = false) EstadoEspera estado) {
        if (estado != null) {
            return ResponseEntity.ok(toResponse(service.porEspecialidadYEstado(especialidadId, estado)));
        }
        return ResponseEntity.ok(toResponse(service.porEspecialidad(especialidadId)));
    }

    @GetMapping("/especialidad/{especialidadId}/siguiente")
    public ResponseEntity<ListaEsperaResponse> siguientePorEspecialidad(@PathVariable Long especialidadId) {
        return ResponseEntity.ok(ListaEsperaResponse.fromEntity(service.siguientePorEspecialidad(especialidadId)));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ListaEsperaResponse> actualizarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest request) {
        return ResponseEntity.ok(ListaEsperaResponse.fromEntity(service.actualizarEstado(id, request)));
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<ListaEsperaResponse> asignarHora(
            @PathVariable Long id,
            @RequestBody AsignarHoraRequest request) {
        return ResponseEntity.ok(ListaEsperaResponse.fromEntity(service.asignarHora(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarSolicitudInvalida(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private List<ListaEsperaResponse> toResponse(List<com.lista_espera.lista_espera.Model.ListaEspera> registros) {
        return registros.stream().map(ListaEsperaResponse::fromEntity).toList();
    }
}
