package com.clinica.agenda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.agenda.entities.Sexo;
import com.clinica.agenda.repository.SexoRepositorie;
import java.util.List;

@Service
public class SexoServicesImplement implements SexoServices{
    @Autowired
    private SexoRepositorie sexoRepositorie;

    @Override
    public List<Sexo> listarSexos(){
        return sexoRepositorie.findAll();
    };

    @Override
    public Sexo crearSexo(Sexo sexo){
        return sexoRepositorie.save(sexo);
    };

    @Override
    public Sexo buscarSexo(Long id){
        return sexoRepositorie.findById(id).orElse(null);
        };

}
