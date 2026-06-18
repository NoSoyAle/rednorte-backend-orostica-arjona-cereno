package com.clinica.agenda.services;

import com.clinica.agenda.entities.DisponibilidadDoctor;
import java.util.List;

public interface DisponibilidadDoctorService {

<<<<<<< HEAD
    List<DisponibilidadDoctor> listarTodos();

    DisponibilidadDoctor buscarPorId(Long id);

    DisponibilidadDoctor guardar(
            DisponibilidadDoctor disponibilidadDoctor);

    DisponibilidadDoctor actualizar(
            Long id,
            DisponibilidadDoctor disponibilidadDoctor);

    void eliminar(Long id);

    List<DisponibilidadDoctor> buscarPorDoctor(
            Long doctorId);
}
=======
        List<DisponibilidadDoctor> listarTodos();

        DisponibilidadDoctor buscarPorId(Long id);

        DisponibilidadDoctor guardar(
                DisponibilidadDoctor disponibilidadDoctor);

        DisponibilidadDoctor actualizar(
                Long id,
                DisponibilidadDoctor disponibilidadDoctor);

        void eliminar(Long id);

        List<DisponibilidadDoctor> buscarPorDoctor(
                Long doctorId);
        }
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
