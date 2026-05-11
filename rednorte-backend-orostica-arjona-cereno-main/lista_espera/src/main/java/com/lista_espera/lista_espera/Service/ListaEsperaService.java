package com.lista_espera.lista_espera.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;
import com.lista_espera.lista_espera.Repository.ListaEsperaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListaEsperaService {

private final ListaEsperaRepository repo;

    public ListaEspera agregar(ListaEspera l) {
        l.setFechaIngreso(LocalDateTime.now());
        l.setEstado(EstadoEspera.ESPERANDO);
        return repo.save(l);
    }

    public List<ListaEspera> obtenerTodos() {
        return repo.findAll();
    }

    public List<ListaEspera> porEspecialidad(String especialidad) {
        return repo.findByEspecialidadOrderByPrioridadAsc(especialidad);
    }

    public ListaEspera actualizarEstado(Long id, EstadoEspera estado) {
        ListaEspera l = repo.findById(id).orElseThrow();
        l.setEstado(estado);
        return repo.save(l);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

}
