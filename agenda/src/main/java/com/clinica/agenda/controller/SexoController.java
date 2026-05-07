package com.clinica.agenda.controller;

import org.springframework.web.bind.annotation.RestController;

import com.clinica.agenda.services.SexoServices;
import com.clinica.agenda.entities.Sexo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sexo ")
public class SexoController {


    @Autowired
    private SexoServices sexoServices;

    @GetMapping()
    public List<Sexo> listarSexos() {
        return sexoServices.listarSexos();
    }
    
    @PostMapping()
    public Sexo crearSexo(@RequestBody Sexo sexo) {
        return sexoServices.crearSexo(sexo);
    }
    
    @GetMapping("/{id}")
    public Sexo buscarSexo(@PathVariable Long id) {
        return sexoServices.buscarSexo(id);
    }
}
