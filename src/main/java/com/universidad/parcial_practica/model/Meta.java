package com.universidad.parcial_practica.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa una meta de ahorro de un usuario (por ejemplo: comprar un carro).
 * Cada meta tiene un objetivo monetario, un monto ahorrado acumulado y una lista de abonos.
 */
@Entity
public class Meta {

    /** Identificador único de la meta, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la meta. */
    private String nombre;

    /** Emoji representativo de la meta. */
    private String emoji;

    /** Monto total que se desea alcanzar. */
    private Double precioObjetivo;

    /** Monto acumulado actualmente mediante abonos. */
    private Double montoAhorrado;

    /** Fecha límite para alcanzar la meta. */
    private LocalDate fechaLimite;

    /**
     * Usuario propietario de la meta. Relación de muchas metas a un usuario.
     * Se marca como WRITE_ONLY para no exponer los datos del usuario al serializar.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    /**
     * Lista de abonos realizados a esta meta. Relación de una meta a muchos abonos.
     * Se ignora en la serialización JSON para evitar referencias circulares.
     */
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