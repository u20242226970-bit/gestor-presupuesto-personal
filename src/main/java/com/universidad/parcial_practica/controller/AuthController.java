package com.universidad.parcial_practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.parcial_practica.model.Usuario;
import com.universidad.parcial_practica.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")

public class AuthController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registro")
	public String registro(@RequestBody Usuario usuario) {
		
		// esto encripta la contrasena antes de que se guarde :)
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return "Usuario registrado existosamente";
				
		
	}
	

}
