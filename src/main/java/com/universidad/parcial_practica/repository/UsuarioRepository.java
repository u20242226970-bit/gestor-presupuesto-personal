package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Usuario.
 * Hereda las operaciones CRUD de JpaRepository y define consultas personalizadas.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     *
     * @param username el nombre de usuario a buscar
     * @return el usuario encontrado, o null si no existe
     */
    Usuario findByUsername(String username);
}