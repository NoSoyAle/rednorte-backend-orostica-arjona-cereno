package com.lista_espera.lista_espera.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.lista_espera.lista_espera.client.AgendaClient;
import com.lista_espera.lista_espera.dto.BloqueHorarioDto;
import com.lista_espera.lista_espera.Model.EstadoEspera;
import com.lista_espera.lista_espera.Model.ListaEspera;
import com.lista_espera.lista_espera.Repository.ListaEsperaRepository;
import com.lista_espera.lista_espera.dto.ActualizarEstadoRequest;
import com.lista_espera.lista_espera.dto.AsignarHoraRequest;
import com.lista_espera.lista_espera.dto.CrearListaEsperaRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListaEsperaService {

    private static final List<EstadoEspera> ESTADOS_ACTIVOS = Arrays.asList(EstadoEspera.ESPERANDO,
            EstadoEspera.CONTACTADO);

    private final ListaEsperaRepository repo;
    private final AgendaClient agendaClient;

    public ListaEspera crear(CrearListaEsperaRequest request) {
        validarCreacion(request);

        boolean yaExiste = repo.existsByPacienteIdAndEspecialidadIdAndEstadoIn(request.getPacienteId(),
                request.getEspecialidadId(), ESTADOS_ACTIVOS);
        if (yaExiste) {
            throw new IllegalArgumentException("El paciente ya tiene una solicitud activa para esta especialidad");
        }

        LocalDateTime ahora = LocalDateTime.now();
        ListaEspera listaEspera = new ListaEspera();
        listaEspera.setPacienteId(request.getPacienteId());
        listaEspera.setEspecialidadId(request.getEspecialidadId());
        listaEspera.setDoctorId(request.getDoctorId());
        listaEspera.setPrioridad(request.getPrioridad());
        listaEspera.setMotivo(request.getMotivo());
        listaEspera.setObservaciones(request.getObservaciones());
        listaEspera.setEstado(EstadoEspera.ESPERANDO);
        listaEspera.setFechaIngreso(ahora);
        listaEspera.setFechaActualizacion(ahora);
        return repo.save(listaEspera);
    }

    public List<ListaEspera> obtenerTodos() {
        return repo.findAll();
    }

    public ListaEspera obtenerPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Solicitud de lista de espera no existe"));
    }

    public List<ListaEspera> porPaciente(Long pacienteId) {
        validarId(pacienteId, "pacienteId");
        return repo.findByPacienteIdOrderByFechaIngresoDesc(pacienteId);
    }

    public List<ListaEspera> porEspecialidad(Long especialidadId) {
        validarId(especialidadId, "especialidadId");
        return repo.findByEspecialidadIdOrderByPrioridadAscFechaIngresoAsc(especialidadId);
    }

    public List<ListaEspera> porEspecialidadYEstado(Long especialidadId, EstadoEspera estado) {
        validarId(especialidadId, "especialidadId");
        validarEstado(estado);
        return repo.findByEspecialidadIdAndEstadoOrderByPrioridadAscFechaIngresoAsc(especialidadId, estado);
    }

    public List<ListaEspera> porEstado(EstadoEspera estado) {
        validarEstado(estado);
        return repo.findByEstadoOrderByPrioridadAscFechaIngresoAsc(estado);
    }

    public ListaEspera siguientePorEspecialidad(Long especialidadId) {
        validarId(especialidadId, "especialidadId");
        return repo.findFirstByEspecialidadIdAndEstadoOrderByPrioridadAscFechaIngresoAsc(especialidadId,
                EstadoEspera.ESPERANDO)
                .orElseThrow(() -> new NoSuchElementException("No hay pacientes esperando para esta especialidad"));
    }

    public ListaEspera actualizarEstado(Long id, ActualizarEstadoRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud es obligatorio");
        }
        validarEstado(request.getEstado());

        ListaEspera listaEspera = obtenerPorId(id);
        listaEspera.setEstado(request.getEstado());
        listaEspera.setObservaciones(request.getObservaciones());
        listaEspera.setFechaActualizacion(LocalDateTime.now());

        if (request.getEstado() == EstadoEspera.ASIGNADO && listaEspera.getFechaAsignacion() == null) {
            listaEspera.setFechaAsignacion(LocalDateTime.now());
        }

        return repo.save(listaEspera);
    }

    public ListaEspera asignarHora(Long id, AsignarHoraRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud es obligatorio");
        }
        validarId(request.getDoctorId(), "doctorId");
        validarId(request.getBloqueHorarioId(), "bloqueHorarioId");

        ListaEspera listaEspera = obtenerPorId(id);
        if (listaEspera.getEstado() == EstadoEspera.CANCELADO || listaEspera.getEstado() == EstadoEspera.EXPIRADO
                || listaEspera.getEstado() == EstadoEspera.RECHAZADO) {
            throw new IllegalArgumentException("No se puede asignar una hora a una solicitud cerrada");
        }

        LocalDateTime ahora = LocalDateTime.now();
        listaEspera.setDoctorId(request.getDoctorId());
        listaEspera.setBloqueHorarioId(request.getBloqueHorarioId());
        listaEspera.setObservaciones(request.getObservaciones());
        listaEspera.setEstado(EstadoEspera.ASIGNADO);
        listaEspera.setFechaAsignacion(ahora);
        listaEspera.setFechaActualizacion(ahora);
        return repo.save(listaEspera);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Solicitud de lista de espera no existe");
        }
        repo.deleteById(id);
    }

    public List<BloqueHorarioDto> obtenerBloquesDoctor(Long doctorId) {
        return agendaClient.obtenerBloquesDoctor(doctorId);
    }

    private void validarCreacion(CrearListaEsperaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud es obligatorio");
        }
        validarId(request.getPacienteId(), "pacienteId");
        validarId(request.getEspecialidadId(), "especialidadId");
        if (request.getPrioridad() == null || request.getPrioridad() < 1 || request.getPrioridad() > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3");
        }
    }

    private void validarId(Long id, String campo) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor a 0");
        }
    }

    private void validarEstado(EstadoEspera estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
    }
}
