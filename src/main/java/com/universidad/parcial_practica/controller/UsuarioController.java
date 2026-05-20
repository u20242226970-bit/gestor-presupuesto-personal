package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/me")
    public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioRepository.findByUsername(username);
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            if (nuevo.getNombre() != null) existente.setNombre(nuevo.getNombre());
            if (nuevo.getIngresoMensual() != null) existente.setIngresoMensual(nuevo.getIngresoMensual());
            if (nuevo.getAhorroMensual() != null) existente.setAhorroMensual(nuevo.getAhorroMensual());
            return usuarioRepository.save(existente);
        }
        return null;
    }
}