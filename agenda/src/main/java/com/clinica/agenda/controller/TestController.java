package com.clinica.agenda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.agenda.services.RabbitMQProducer;
@RestController
@RequestMapping("/test")
public class TestController {

    private final RabbitMQProducer producer;

    public TestController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping
    public String test() {

        producer.enviarMensaje(
            "Cita creada para Juan Perez"
        );
        return "Mensaje enviado";
    }
}