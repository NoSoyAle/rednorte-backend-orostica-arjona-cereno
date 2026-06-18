package com.clinica.agenda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.agenda.dto.NotificacionCitaDTO;
import com.clinica.agenda.services.RabbitMQProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RabbitMQProducer producer;
    private final ObjectMapper objectMapper;

    public TestController(RabbitMQProducer producer, ObjectMapper objectMapper) {
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String test() {
        try {
            NotificacionCitaDTO notificacion = new NotificacionCitaDTO();
            notificacion.setTipo("CITA_CREADA");
            notificacion.setPacienteEmail("paciente@correo.com");
            notificacion.setPacienteNombre("Juan");
            notificacion.setPacienteApellido("Perez");
            notificacion.setDoctorNombre("Carlos");
            notificacion.setDoctorApellido("Lopez");
            notificacion.setDoctorCorreo("doctor@correo.com");
            notificacion.setFecha("2026-06-20");
            notificacion.setHoraInicio("10:00");
            notificacion.setHoraFin("10:30");
            notificacion.setEstado("CONFIRMADA");

            
            String mensajeJson = objectMapper.writeValueAsString(notificacion);
            producer.enviarMensaje(mensajeJson);
            return "Mensaje JSON enviado: " + mensajeJson;
        } catch (Exception e) {
            return "Error al enviar mensaje: " + e.getMessage();
        }
    }
}