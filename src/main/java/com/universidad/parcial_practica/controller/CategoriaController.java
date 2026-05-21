package com.universidad.parcial_practica.controller;

import com.universidad.parcial_practica.model.Categoria;
import com.universidad.parcial_practica.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar las categorías de gasto.
 * Expone endpoints CRUD bajo la ruta /api/categorias.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Lista todas las categorías existentes.
     *
     * @return lista de todas las categorías
     */
    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaService.listarTodas();
    }

    /**
     * Busca una categoría por su identificador.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada, o null si no existe
     */
    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    /**
     * Guarda una nueva categoría.
     *
     * @param categoria datos de la categoría a guardar
     * @return la categoría guardada
     */
    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id identificador de la categoría a actualizar
     * @param categoria nuevos datos de la categoría
     * @return la categoría actualizada, o null si no existe
     */
    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.actualizar(id, categoria);
    }

    /**
     * Elimina una categoría por su identificador.
     *
     * @param id identificador de la categoría a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}