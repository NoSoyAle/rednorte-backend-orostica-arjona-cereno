package com.clinica.agenda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.agenda.services.CitaService;
import com.clinica.agenda.repository.CitaRpository;


@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;
    private final CitaRpository citaRepository;


    public CitaController(CitaService citaService, CitaRpository citaRepository) {
        this.citaService = citaService;
        this.citaRepository = citaRepository;
    }

    

    
}
 