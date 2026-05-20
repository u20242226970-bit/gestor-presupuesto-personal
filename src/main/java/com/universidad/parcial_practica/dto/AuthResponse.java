package com.universidad.parcial_practica.dto;

/**
 * DTO que se devuelve al cliente después de un login o registro exitoso.
 * Contiene el token JWT y datos básicos del usuario (sin la contraseña).
 */
public class AuthResponse {

    private String token;
    private Long idUsuario;
    private String username;
    private String nombre;
    private String rol;

    public AuthResponse(String token, Long idUsuario, String username, String nombre, String rol) {
        this.token = token;
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}