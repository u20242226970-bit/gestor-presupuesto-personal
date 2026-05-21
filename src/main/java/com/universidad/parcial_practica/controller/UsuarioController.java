package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar los usuarios.
 * Expone endpoints bajo la ruta /api/usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Devuelve los datos del usuario actualmente autenticado.
     * Obtiene el username desde el contexto de seguridad (token JWT validado).
     *
     * @return el usuario autenticado
     */
    @GetMapping("/me")
    public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Lista todos los usuarios registrados. Utilizado por el ranking (leaderboard).
     *
     * @return lista de todos los usuarios
     */
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Actualiza los datos de un usuario existente.
     * Solo modifica los campos que llegan con valor (actualización parcial),
     * por lo que no afecta la contraseña ni el username.
     *
     * @param id identificador del usuario a actualizar
     * @param nuevo objeto con los nuevos datos del usuario
     * @return el usuario actualizado, o null si no existe
     */
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