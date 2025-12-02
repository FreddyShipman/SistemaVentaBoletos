package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

public class Usuario {
    private int id;
    private String email;
    private String password;
    private String rol;

    public Usuario(int id, String email, String password, String rol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }   
}