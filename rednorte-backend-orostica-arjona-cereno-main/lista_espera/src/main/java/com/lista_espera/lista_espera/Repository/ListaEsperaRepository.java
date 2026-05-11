package com.lista_espera.lista_espera.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {
    List<ListaEspera> findByEstado(EstadoEspera estado);
    List<ListaEspera> findByEspecialidadOrderByPrioridadAsc(String especialidad);
    List<ListaEspera> findByPacienteId(Long pacienteId);


}
