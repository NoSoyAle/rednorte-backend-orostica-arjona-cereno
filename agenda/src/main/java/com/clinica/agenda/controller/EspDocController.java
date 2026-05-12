package com.clinica.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clinica.agenda.entities.EspecialidadDoctor;
import com.clinica.agenda.services.EspDocService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  



@RestController
@RequestMapping("/api/doctorEspecialidad")
public class EspDocController {
    
    @Autowired
    private EspDocService EspDocrepository;


    @GetMapping
    public List<EspecialidadDoctor> listar() {
        return EspDocrepository.listarEspecialidadesDoctores();
    }

    @PostMapping
    public EspecialidadDoctor guardar(
            @RequestBody EspecialidadDoctor relacion) {

        return EspDocrepository.crearVinculacionEspecialidadDoctor(relacion);
    }

    //Elimina vinculación especifica, por ej si juanito perez es cardiolog y pediatra, tneemos que eliminar 1 por una
    @DeleteMapping("/{id}")
    public void eliminarDoctor(@PathVariable Long id) {
        EspDocrepository.DesvincularEspecialidadDoctor(id);
    }
}
