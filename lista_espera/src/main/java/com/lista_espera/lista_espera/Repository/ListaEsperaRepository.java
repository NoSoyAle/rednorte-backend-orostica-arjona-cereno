package com.lista_espera.lista_espera.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long> {

    List<ListaEspera> findByEstadoOrderByPrioridadAscFechaIngresoAsc(EstadoEspera estado);

    List<ListaEspera> findByEspecialidadIdOrderByPrioridadAscFechaIngresoAsc(Long especialidadId);

    List<ListaEspera> findByEspecialidadIdAndEstadoOrderByPrioridadAscFechaIngresoAsc(Long especialidadId,
            EstadoEspera estado);

    List<ListaEspera> findByPacienteIdOrderByFechaIngresoDesc(Long pacienteId);

    boolean existsByPacienteIdAndEspecialidadIdAndEstadoIn(Long pacienteId, Long especialidadId,
            Collection<EstadoEspera> estados);

    Optional<ListaEspera> findFirstByEspecialidadIdAndEstadoOrderByPrioridadAscFechaIngresoAsc(Long especialidadId,
            EstadoEspera estado);
}
