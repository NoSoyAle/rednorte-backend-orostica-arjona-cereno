package com.clinica.agenda.services;
import java.util.List;
import com.clinica.agenda.entities.Doctor;
<<<<<<< HEAD
=======
import com.clinica.agenda.entities.Especialidad;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661


public interface DoctorServices {
    List<Doctor> listarDoctores();

    Doctor crearDoctor(Doctor doctor);
    Doctor actualizarDoctor(Long id, Doctor doctor);
    Doctor buscarDoctor(Long id);
    void eliminarDoctor(Long id);
    //Este metodo no devuelve nada, solo elimina
<<<<<<< HEAD
=======
    List<Doctor> buscarPorEspecialidad(
        Long especialidadId);
    Doctor buscarPorRut(String rut);

    List<Especialidad> obtenerEspecialidadesDoctor(
        Long doctorId);
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661



}
