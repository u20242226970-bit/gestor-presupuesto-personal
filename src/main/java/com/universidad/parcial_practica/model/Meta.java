package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String emoji;
    private Double precioObjetivo;
    private Double montoAhorrado;
    private LocalDate fechaLimite;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public Double getPrecioObjetivo() { return precioObjetivo; }
    public void setPrecioObjetivo(Double precioObjetivo) { this.precioObjetivo = precioObjetivo; }

    public Double getMontoAhorrado() { return montoAhorrado; }
    public void setMontoAhorrado(Double montoAhorrado) { this.montoAhorrado = montoAhorrado; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}