package com.universidad.parcial_practica.dto;

/**
 * DTO que representa el cuerpo de la petición de login.
 * El cliente envía username y password en formato JSON.
 */
public class LoginRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}