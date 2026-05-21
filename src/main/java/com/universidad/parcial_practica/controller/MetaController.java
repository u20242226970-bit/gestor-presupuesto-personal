package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import com.universidad.parcial_practica.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar las metas de ahorro de los usuarios.
 * Expone endpoints CRUD bajo la ruta /api/metas.
 */
@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Lista todas las metas de un usuario específico.
     *
     * @param usuarioId identificador del usuario
     * @return lista de metas del usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<Meta> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        return metaService.listarPorUsuario(usuario);
    }

    /**
     * Busca una meta por su identificador.
     *
     * @param id identificador de la meta
     * @return la meta encontrada, o null si no existe
     */
    @GetMapping("/{id}")
    public Meta buscarPorId(@PathVariable Long id) {
        return metaService.buscarPorId(id);
    }

    /**
     * Guarda una nueva meta.
     *
     * @param meta datos de la meta a guardar
     * @return la meta guardada
     */
    @PostMapping
    public Meta guardar(@RequestBody Meta meta) {
        return metaService.guardar(meta);
    }

    /**
     * Actualiza una meta existente.
     *
     * @param id identificador de la meta a actualizar
     * @param meta nuevos datos de la meta
     * @return la meta actualizada, o null si no existe
     */
    @PutMapping("/{id}")
    public Meta actualizar(@PathVariable Long id, @RequestBody Meta meta) {
        return metaService.actualizar(id, meta);
    }

    /**
     * Elimina una meta por su identificador.
     *
     * @param id identificador de la meta a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        metaService.eliminar(id);
    }
}