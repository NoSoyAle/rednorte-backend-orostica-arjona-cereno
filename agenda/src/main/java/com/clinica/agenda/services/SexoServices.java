package com.clinica.agenda.services;
import com.clinica.agenda.entities.Sexo;
import java.util.List;

public interface SexoServices {

    List<Sexo> listarSexos();

    Sexo crearSexo(Sexo sexo);

    Sexo buscarSexo(Long id);

    Sexo eliminarSexo(Long id);
}
