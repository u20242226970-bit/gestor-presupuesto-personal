package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import com.universidad.parcial_practica.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Meta> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        return metaService.listarPorUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Meta buscarPorId(@PathVariable Long id) {
        return metaService.buscarPorId(id);
    }

    @PostMapping
    public Meta guardar(@RequestBody Meta meta) {
        return metaService.guardar(meta);
    }

    @PutMapping("/{id}")
    public Meta actualizar(@PathVariable Long id, @RequestBody Meta meta) {
        return metaService.actualizar(id, meta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        metaService.eliminar(id);
    }
}