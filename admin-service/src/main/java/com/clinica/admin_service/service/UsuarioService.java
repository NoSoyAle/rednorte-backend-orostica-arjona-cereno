package com.clinica.admin_service.service;

import com.clinica.admin_service.dto.RegistroDoctorRequest;
import com.clinica.admin_service.dto.UsuarioEstadoDTO;
import com.clinica.admin_service.model.Usuario;
import com.clinica.admin_service.dto.DashboardKpiDto;
<<<<<<< HEAD
=======
import com.clinica.admin_service.dto.RegistroAdminDto;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import java.util.List;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    Usuario registrarDoctor(RegistroDoctorRequest request);
    List<Usuario> listarUsuarios();
    Usuario buscarPorRut(String rut);
    void eliminarUsuario(Long id);
    DashboardKpiDto obtenerMetricasDashboard();
    Usuario actualizarEstadoUsuario(Long id, UsuarioEstadoDTO dto);
<<<<<<< HEAD
=======
    RegistroAdminDto obtenerRegistroAdmin();
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
}