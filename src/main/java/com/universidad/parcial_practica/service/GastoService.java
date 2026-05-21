package com.universidad.parcial_practica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.GastoRepository;

/**
 * Servicio que contiene la lógica de negocio para los gastos.
 */
@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    /**
     * Lista todos los gastos del sistema.
     *
     * @return lista de todos los gastos
     */
    public List<Gasto> listarTodos() {
        return gastoRepository.findAll();
    }

    /**
     * Lista todos los gastos de un usuario específico.
     *
     * @param usuario el usuario del cual se quieren los gastos
     * @return lista de gastos del usuario
     */
    public List<Gasto> listarPorUsuario(Usuario usuario) {
        return gastoRepository.findByUsuario(usuario);
    }

    /**
     * Guarda un nuevo gasto.
     *
     * @param gasto datos del gasto a guardar
     * @return el gasto guardado
     */
    public Gasto guardar(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    /**
     * Busca un gasto por su identificador.
     *
     * @param id identificador del gasto
     * @return el gasto encontrado, o null si no existe
     */
    public Gasto buscarPorId(Long id) {
        return gastoRepository.findById(id).orElse(null);
    }

    /**
     * Elimina un gasto por su identificador.
     *
     * @param id identificador del gasto a eliminar
     */
    public void eliminar(Long id) {
        gastoRepository.deleteById(id);
    }

    /**
     * Actualiza un gasto existente con nuevos datos.
     *
     * @param id identificador del gasto a actualizar
     * @param nuevo nuevos datos del gasto
     * @return el gasto actualizado, o null si no existe
     */
    public Gasto actualizar(Long id, Gasto nuevo) {
        Gasto existente = gastoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setDescripcion(nuevo.getDescripcion());
            existente.setMonto(nuevo.getMonto());
            existente.setFecha(nuevo.getFecha());
            existente.setCategoria(nuevo.getCategoria());
            return gastoRepository.save(existente);
        }
        return null;
    }
}