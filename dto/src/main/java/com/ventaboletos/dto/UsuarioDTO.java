package com.ventaboletos.dto;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

public class UsuarioDTO {
    private String id;
    private String email;
    private String password;
    private String rol; // "ADMIN" o "CLIENTE"

    public UsuarioDTO() {
    
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }  
}