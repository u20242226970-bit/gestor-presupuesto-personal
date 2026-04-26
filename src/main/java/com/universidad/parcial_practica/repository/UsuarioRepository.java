package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este método busca un usuario por su username
    Usuario findByUsername(String username);
}



