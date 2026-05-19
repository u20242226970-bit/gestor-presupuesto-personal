package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AbonoMeta> abonos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public Double getPrecioObjetivo() { return precioObjetivo; }
    public void setPrecioObjetivo(Double p) { this.precioObjetivo = p; }

    public Double getMontoAhorrado() { return montoAhorrado; }
    public void setMontoAhorrado(Double m) { this.montoAhorrado = m; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate f) { this.fechaLimite = f; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<AbonoMeta> getAbonos() { return abonos; }
    public void setAbonos(List<AbonoMeta> abonos) { this.abonos = abonos; }
}