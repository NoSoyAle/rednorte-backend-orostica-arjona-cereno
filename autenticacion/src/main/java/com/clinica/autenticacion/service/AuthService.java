package com.clinica.autenticacion.service;
/* import com.clinica.autenticacion.AutenticacionApplication;
import java.util.List; */
import com.clinica.autenticacion.model.Usuario;
import com.clinica.autenticacion.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String nombre, String password) {

        if (usuarioRepository.findByNombre(nombre).isPresent()) {
            return "Usuario ya existe";
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(passwordEncoder.encode(password));

        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }

    public String login(String nombre, String password) {

        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return "Login exitoso";
    }
}


