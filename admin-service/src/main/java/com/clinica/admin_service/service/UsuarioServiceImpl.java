package com.clinica.admin_service.service;

import com.clinica.admin_service.dto.DashboardKpiDto;
import com.clinica.admin_service.dto.RegistroDoctorRequest;
import com.clinica.admin_service.dto.UsuarioEstadoDTO;
import com.clinica.admin_service.model.Rol;
import com.clinica.admin_service.model.Usuario;
import com.clinica.admin_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario registrarDoctor(RegistroDoctorRequest request) {
        if (usuarioRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el nombre: " + request.getNombre());
        }
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el email: " + request.getEmail());
        }
        if (usuarioRepository.findByRut(request.getRut()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el RUT: " + request.getRut());
        }

        Usuario doctor = new Usuario();
        doctor.setNombre(request.getNombre());
        doctor.setRut(request.getRut());
        doctor.setEmail(request.getEmail());
        doctor.setPassword(passwordEncoder.encode(request.getPassword()));
        doctor.setTelefono(request.getTelefono());
        doctor.setRol(Rol.DOCTOR);
        doctor.setEstado("ACTIVO");

        return usuarioRepository.save(doctor);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorRut(String rut) {
        return usuarioRepository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con RUT: " + rut));
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("El usuario con ID " + id + " no existe.");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public DashboardKpiDto obtenerMetricasDashboard() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        long total = usuarios.size();
        long admins = usuarios.stream().filter(u -> u.getRol() == Rol.ADMIN).count();
        long doc = usuarios.stream().filter(u -> u.getRol() == Rol.DOCTOR).count();
        
        Map<String, Long> distribucion = new HashMap<>();
        distribucion.put("ADMINISTRADORES", admins);
        distribucion.put("DOCTORES", doc);
        
        return new DashboardKpiDto(total, admins, doc, distribucion);
    }

    @Override
    public Usuario actualizarEstadoUsuario(Long id, UsuarioEstadoDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        usuario.setEstado(dto.getEstado());
        return usuarioRepository.save(usuario);
    }
}