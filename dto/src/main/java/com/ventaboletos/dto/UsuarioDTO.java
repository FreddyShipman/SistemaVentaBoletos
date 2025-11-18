package com.ventaboletos.dto;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

/**
 * DTO para transportar datos de inicio de sesión y 
 * también para devolver información del usuario autenticado.
 */
public class UsuarioDTO {

    // Para enviar al login
    private String nombreUsuario;
    private String password;
    
    // Para devolver del login
    // (Podemos usarlos después para saber quién se logueó)
    private String nombreCompleto;
    private String rol; // ("Admin" o "Cliente")

    
    // Constructores
    public UsuarioDTO() {
    }

    // Constructor rápido para enviar datos de login
    public UsuarioDTO(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    // Getters y Setters 
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}