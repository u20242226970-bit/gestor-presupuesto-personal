package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public String registro(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (usuario.getRol() == null) {
            usuario.setRol("USER");
        }
        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

    @GetMapping("/usuario/{username}")
    public Usuario obtenerUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username);
    }
}