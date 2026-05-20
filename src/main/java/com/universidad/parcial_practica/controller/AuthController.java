package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.JwtUtil;
import com.universidad.parcial_practica.dto.AuthResponse;
import com.universidad.parcial_practica.dto.LoginRequest;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador de autenticación.
 * Expone los endpoints para registro y login.
 * El login devuelve un JWT que el cliente debe enviar en los siguientes requests.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Registra un nuevo usuario.
     * Encripta la contraseña con BCrypt y asigna el rol "USER" por defecto.
     * Devuelve un JWT para que el usuario quede logueado automáticamente.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) {
        // Verificar si el username ya existe
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.status(400)
                    .body(Map.of("error", "El nombre de usuario ya existe"));
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol por defecto
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USER");
        }

        // Guardar usuario
        Usuario guardado = usuarioRepository.save(usuario);

        // Generar token JWT y devolverlo
        String token = jwtUtil.generateToken(guardado.getUsername());

        AuthResponse response = new AuthResponse(
                token,
                guardado.getId(),
                guardado.getUsername(),
                guardado.getNombre(),
                guardado.getRol()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Autentica un usuario con username y password.
     * Si las credenciales son correctas, devuelve un JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Spring Security valida las credenciales contra CustomUserDetailsService
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Si llegamos aquí, las credenciales son válidas: buscamos el usuario completo
            Usuario usuario = usuarioRepository.findByUsername(request.getUsername());

            // Generar el token
            String token = jwtUtil.generateToken(usuario.getUsername());

            AuthResponse response = new AuthResponse(
                    token,
                    usuario.getId(),
                    usuario.getUsername(),
                    usuario.getNombre(),
                    usuario.getRol()
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Error al autenticar: " + e.getMessage()));
        }
    }

    /**
     * Devuelve los datos de un usuario por su username.
     */
    @GetMapping("/usuario/{username}")
    public Usuario obtenerUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username);
    }
}