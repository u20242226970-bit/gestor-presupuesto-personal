package com.universidad.parcial_practica.repository;

import com.universidad.parcial_practica.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Categoria.
 * Hereda las operaciones CRUD básicas de JpaRepository.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}