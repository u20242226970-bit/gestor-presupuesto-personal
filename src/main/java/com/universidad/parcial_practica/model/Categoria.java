package com.universidad.parcial_practica.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una categoría de gasto (por ejemplo: Comida, Transporte, Ocio).
 * Sirve para clasificar los gastos del usuario.
 */
@Entity
public class Categoria {

    /** Identificador único de la categoría, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la categoría. */
    private String nombre;

    /** Descripción opcional de la categoría. */
    private String descripcion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}