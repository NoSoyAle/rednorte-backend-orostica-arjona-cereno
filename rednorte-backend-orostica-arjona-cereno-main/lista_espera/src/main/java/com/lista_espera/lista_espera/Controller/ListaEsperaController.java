package com.lista_espera.lista_espera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;
import com.lista_espera.lista_espera.Service.ListaEsperaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lista-espera")
@RequiredArgsConstructor
public class ListaEsperaController {

private final ListaEsperaService service;

    @PostMapping
    public ResponseEntity<ListaEspera> agregar(@RequestBody ListaEspera l) {
        return ResponseEntity.ok(service.agregar(l));
    }

    @GetMapping
    public ResponseEntity<List<ListaEspera>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<ListaEspera>> porEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(service.porEspecialidad(especialidad));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ListaEspera> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoEspera estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
