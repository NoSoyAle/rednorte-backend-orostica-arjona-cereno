package com.clinica.agenda.services;

import com.clinica.agenda.entities.EspecialidadDoctor;
import java.util.List;
import java.util.Optional;  

public interface EspDocService {
    List<EspecialidadDoctor> listarEspecialidadesDoctores();

    EspecialidadDoctor crearVinculacionEspecialidadDoctor(EspecialidadDoctor especialidadDoctor);
    
    Optional<EspecialidadDoctor> buscarEspecialidadDoctor(Long id);
    //Porque la especialidad doctor puede no existir, entonces se devuelve un Optional para manejar ese caso.

    void DesvincularEspecialidadDoctor(Long id);
    //Este metodo no devuelve nada, solo elimina

}
 