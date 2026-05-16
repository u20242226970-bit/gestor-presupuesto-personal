package com.universidad.parcial_practica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.GastoRepository;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    public List<Gasto> listarTodos() {
        return gastoRepository.findAll();
    }

    public List<Gasto> listarPorUsuario(Usuario usuario) {
        return gastoRepository.findByUsuario(usuario);
    }

    public Gasto guardar(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public Gasto buscarPorId(Long id) {
        return gastoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        gastoRepository.deleteById(id);
    }

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