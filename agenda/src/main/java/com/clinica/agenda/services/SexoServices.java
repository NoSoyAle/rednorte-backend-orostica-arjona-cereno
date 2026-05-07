package com.clinica.agenda.services;
import com.clinica.agenda.entities.Sexo;
import java.util.List;

public interface SexoServices {

    List<Sexo> listarSexos();

    Sexo crearSexo(Sexo sexo);

    Sexo buscarSexo(Long id);
    //No vamos a eliminar un sexo, probablemente solo tengamos q agregar
}
