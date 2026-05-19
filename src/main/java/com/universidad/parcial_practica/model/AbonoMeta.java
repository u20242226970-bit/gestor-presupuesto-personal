package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@Entity
public class AbonoMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;
    private LocalDate fecha;
    private String descripcion;

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