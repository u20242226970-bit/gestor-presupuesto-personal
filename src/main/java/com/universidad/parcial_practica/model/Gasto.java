package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Entidad que representa un gasto registrado por un usuario.
 * Cada gasto está asociado a una categoría y a un usuario.
 */
@Entity
public class Gasto {

    /** Identificador único del gasto, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descripción del gasto. */
    private String descripcion;

    /** Monto del gasto. */
    private Double monto;

    /** Fecha en la que se realizó el gasto. */
    private LocalDate fecha;

    /** Categoría a la que pertenece el gasto. Relación de muchos gastos a una categoría. */
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    /**
     * Usuario propietario del gasto. Relación de muchos gastos a un usuario.
     * Se marca como WRITE_ONLY para no exponer los datos del usuario al serializar.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}