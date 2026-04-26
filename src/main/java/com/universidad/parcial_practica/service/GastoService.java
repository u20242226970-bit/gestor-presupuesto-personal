package com.universidad.parcial_practica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.repository.GastoRepository;

@Service

public class GastoService {
	
	@Autowired
	
	private GastoRepository gastoRepository;
	
	//listar todos los gastos
	
	public List<Gasto> listarTodos() {
		return gastoRepository.findAll();
		
	}
	//buscar gasto por id
	public Gasto buscarPorId(Long id) {
		return gastoRepository.findById(id).orElse(null);
			}
	
	//guardar un gasto nuevo
	
	public Gasto guardar(Gasto gasto) {
		return gastoRepository.save(gasto);
	}
	
	//Eliminar un gasto
	
	public void eliminar(Long id) {
		gastoRepository.deleteById(id);
		
	}
	
	//Actualizar un gasto
	
	public Gasto actualizar(Long id, Gasto gastoNuevo) {
		
		Gasto gastoExistente = gastoRepository.findById(id).orElse(null);
		if (gastoExistente != null) {
			gastoExistente.setDescripcion(gastoNuevo.getDescripcion());
			gastoExistente.setMonto(gastoNuevo.getMonto());
			gastoExistente.setCategoria(gastoNuevo.getCategoria());
			return gastoRepository.save(gastoExistente);
			
			
		}
		return null;
	}

}
