package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

import java.util.Date;

public class Concierto {
    private int id;
    private String nombre;
    private String artista;
    private Date fecha;
    private String lugar;

    public Concierto() {}

    public Concierto(int id, String nombre, String artista, Date fecha, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}