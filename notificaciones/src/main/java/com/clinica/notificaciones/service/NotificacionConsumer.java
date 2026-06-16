package com.clinica.notificaciones.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacionConsumer {
    @RabbitListener(queues = "notificaciones")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
        // Aquí puedes agregar la lógica para procesar el mensaje recibido
    }
}
