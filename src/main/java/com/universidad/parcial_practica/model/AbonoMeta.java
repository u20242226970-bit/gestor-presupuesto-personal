package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Entidad que representa un abono (aporte de dinero) realizado a una meta de ahorro.
 * Cada abono pertenece a una única meta y registra el monto aportado y la fecha.
 */
@Entity
public class AbonoMeta {

    /** Identificador único del abono, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Monto de dinero aportado en este abono. */
    private Double monto;

    /** Fecha en la que se realizó el abono. */
    private LocalDate fecha;

    /** Descripción opcional del abono. */
    private String descripcion;

    /**
     * Meta a la que pertenece este abono. Relación de muchos abonos a una meta.
     * Se marca como WRITE_ONLY para no exponerla al serializar y evitar bucles.
     */
    @ManyToOne
    @JoinColumn(name = "meta_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Meta meta;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }
}