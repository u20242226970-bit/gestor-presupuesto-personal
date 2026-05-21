package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;
import com.universidad.parcial_practica.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar los gastos de los usuarios.
 * Expone endpoints CRUD bajo la ruta /api/gastos.
 */
@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Lista todos los gastos del sistema.
     *
     * @return lista de todos los gastos
     */
    @GetMapping
    public List<Gasto> listarTodos() {
        return gastoService.listarTodos();
    }

    /**
     * Lista todos los gastos de un usuario específico.
     *
     * @param usuarioId identificador del usuario
     * @return lista de gastos del usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<Gasto> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        return gastoService.listarPorUsuario(usuario);
    }

    /**
     * Busca un gasto por su identificador.
     *
     * @param id identificador del gasto
     * @return el gasto encontrado, o null si no existe
     */
    @GetMapping("/{id}")
    public Gasto buscarPorId(@PathVariable Long id) {
        return gastoService.buscarPorId(id);
    }

    /**
     * Guarda un nuevo gasto.
     *
     * @param gasto datos del gasto a guardar
     * @return el gasto guardado
     */
    @PostMapping
    public Gasto guardar(@RequestBody Gasto gasto) {
        return gastoService.guardar(gasto);
    }

    /**
     * Actualiza un gasto existente.
     *
     * @param id identificador del gasto a actualizar
     * @param gasto nuevos datos del gasto
     * @return el gasto actualizado, o null si no existe
     */
    @PutMapping("/{id}")
    public Gasto actualizar(@PathVariable Long id, @RequestBody Gasto gasto) {
        return gastoService.actualizar(id, gasto);
    }

    /**
     * Elimina un gasto por su identificador.
     *
     * @param id identificador del gasto a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        gastoService.eliminar(id);
    }
}