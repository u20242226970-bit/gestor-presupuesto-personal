package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import com.universidad.parcial_practica.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Gasto> listarTodos() {
        return gastoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Gasto> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        return gastoService.listarPorUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Gasto buscarPorId(@PathVariable Long id) {
        return gastoService.buscarPorId(id);
    }

    @PostMapping
    public Gasto guardar(@RequestBody Gasto gasto) {
        return gastoService.guardar(gasto);
    }

    @PutMapping("/{id}")
    public Gasto actualizar(@PathVariable Long id, @RequestBody Gasto gasto) {
        return gastoService.actualizar(id, gasto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        gastoService.eliminar(id);
    }
}