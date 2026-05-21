package com.universidad.parcial_practica.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema FinanceRank.
 * Almacena las credenciales de acceso y la información financiera base
 * (ingreso y ahorro mensual) usada para calcular el rango del usuario.
 */
@Entity
public class Usuario {

    /** Identificador único del usuario, generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre completo del usuario. */
    private String nombre;

    /** Nombre de usuario único usado para iniciar sesión. */
    private String username;

    /** Contraseña encriptada con BCrypt. */
    private String password;

    /** Rol del usuario (por ejemplo: USER o ADMIN). */
    private String rol;

    /** Ingreso mensual del usuario. */
    private Double ingresoMensual;

    /** Ahorro mensual del usuario. */
    private Double ahorroMensual;

    /** Porcentaje de ahorro inicial indicado durante el registro. */
    private Integer porcentajeAhorroInicial;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Double getIngresoMensual() { return ingresoMensual; }
    public void setIngresoMensual(Double ingresoMensual) { this.ingresoMensual = ingresoMensual; }

    public Double getAhorroMensual() { return ahorroMensual; }
    public void setAhorroMensual(Double ahorroMensual) { this.ahorroMensual = ahorroMensual; }

    public Integer getPorcentajeAhorroInicial() { return porcentajeAhorroInicial; }
    public void setPorcentajeAhorroInicial(Integer p) { this.porcentajeAhorroInicial = p; }
}