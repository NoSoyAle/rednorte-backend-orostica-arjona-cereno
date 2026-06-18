package com.clinica.agenda.controller;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.services.CitaService;

@RestController
@RequestMapping("/api/citas")
=======
import com.clinica.agenda.services.CitaService;
import com.clinica.agenda.entities.Cita;
import com.clinica.agenda.entities.dto.CitaDetDTO;
import com.clinica.agenda.entities.dto.PacienteAtendidoDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173")
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarTodos();
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Cita> obtenerCitasDoctor(@PathVariable Long doctorId) {
        return citaService.obtenerCitasDoctor(doctorId);
    }

    @GetMapping("/{id}")
    public Cita obtenerCita(@PathVariable Long id) {
        return citaService.buscarPorId(id);
    }

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita){
        return citaService.guardar(cita);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Long id) {
        citaService.eliminar(id);
    }

    @PutMapping("/{id}")
    public Cita actualizarCita(
            @PathVariable Long id,
            @RequestBody Cita cita) {

        return citaService.actualizar(id, cita);
    }

<<<<<<< HEAD
    
    @GetMapping("/disponibles/{doctorId}/{fecha}")
    public List<LocalTime> obtenerHorariosDisponibles(
            @PathVariable Long doctorId,
            @PathVariable LocalDate fecha) {

                
        return citaService.obtenerHorariosDisponibles(
                doctorId,
                fecha);
    }
}
  
=======
    @GetMapping("/disponibles/{doctorId}/{fecha}")
    public List<LocalTime> obtenerHorariosDisponibles(
            @PathVariable Long doctorId,
            @PathVariable LocalDate fecha) {return citaService.obtenerHorariosDisponibles(
                doctorId,
                fecha);
    }

    @GetMapping("/doctor/{doctorId}/pacientes")
    public List<PacienteAtendidoDTO> obtenerPacientesAtendidos(@PathVariable Long doctorId) {
            return citaService
                    .obtenerPacientesAtendidos(
                            doctorId);
        }

    @GetMapping("/doctor/{doctorId}/fecha/{fecha}")
    public List<CitaDetDTO> obtenerPorFecha(
            @PathVariable Long doctorId,
            @PathVariable LocalDate fecha) {

        return citaService.obtenerPorFecha(
                doctorId,
                fecha);
    }

}
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
