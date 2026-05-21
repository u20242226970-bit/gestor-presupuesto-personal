package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.Gasto;
import com.universidad.parcial_practica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Gasto.
 * Hereda las operaciones CRUD de JpaRepository y define consultas personalizadas.
 */
@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    /**
     * Busca todos los gastos pertenecientes a un usuario específico.
     *
     * @param usuario el usuario del cual se quieren obtener los gastos
     * @return lista de gastos del usuario
     */
    List<Gasto> findByUsuario(Usuario usuario);
}