package com.universidad.parcial_practica.controller;

import java.util.List;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// este ultimo import hace que se importen todas las erramientas como @RequestMapping etc
// para el parcial Ctrl + Shift + O, hace que se importe todo lo que hace falta
@RestController
@RequestMapping ("/gastos")
public class GastoController {
	
	
	@Autowired
	private GastoService gastoService;
	
	//GET /gastos - Listar Todos
	
	@GetMapping 
	public List<Gasto> ListarTodos() {
		return gastoService.listarTodos();
		}
	
	//GET /gastos/{id} - Buscar por ID
	
	@GetMapping("/{id}")
	public Gasto buscarPorId(@PathVariable Long id) {
		return gastoService.buscarPorId(id);
		}
	
	//POST /gastos - Crear nuevo
	
	@PostMapping
	public Gasto guardar(@RequestBody Gasto gasto) {
		return gastoService.guardar(gasto);
	}
	
	//DELETE /gastos/{id} - eliminar
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		gastoService.eliminar(id);
		
	}
	
	//PUT /gastos/{id} - actualizar
	
	@PutMapping("/{id}")
	public Gasto actualizar (@PathVariable Long id, @RequestBody Gasto gasto) {
		return gastoService.actualizar(id, gasto);
	
		
	}

}
