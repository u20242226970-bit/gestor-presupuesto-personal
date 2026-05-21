package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.Meta;
import com.universidad.parcial_practica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Meta.
 * Hereda las operaciones CRUD de JpaRepository y define consultas personalizadas.
 */
@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {

    /**
     * Busca todas las metas pertenecientes a un usuario específico.
     *
     * @param usuario el usuario del cual se quieren obtener las metas
     * @return lista de metas del usuario
     */
    List<Meta> findByUsuario(Usuario usuario);
}