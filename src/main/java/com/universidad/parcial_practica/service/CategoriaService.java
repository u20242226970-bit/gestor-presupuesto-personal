package com.universidad.parcial_practica.service;

import com.universidad.parcial_practica.model.Categoria;
import com.universidad.parcial_practica.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para las categorías de gasto.
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Lista todas las categorías existentes.
     *
     * @return lista de todas las categorías
     */
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    /**
     * Guarda una nueva categoría.
     *
     * @param categoria datos de la categoría a guardar
     * @return la categoría guardada
     */
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Busca una categoría por su identificador.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada, o null si no existe
     */
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una categoría por su identificador.
     *
     * @param id identificador de la categoría a eliminar
     */
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }

    /**
     * Actualiza una categoría existente con nuevos datos.
     *
     * @param id identificador de la categoría a actualizar
     * @param nueva nuevos datos de la categoría
     * @return la categoría actualizada, o null si no existe
     */
    public Categoria actualizar(Long id, Categoria nueva) {
        Categoria existente = categoriaRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(nueva.getNombre());
            existente.setDescripcion(nueva.getDescripcion());
            return categoriaRepository.save(existente);
        }
        return null;
    }
}