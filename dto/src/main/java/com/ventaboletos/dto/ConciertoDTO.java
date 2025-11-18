package com.ventaboletos.dto;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import java.util.Date;

// Esta clase es un simple "POJO" (Plain Old Java Object)
// Solo transporta datos.
public class ConciertoDTO {

    private String id;
    private String nombre;
    private String artista;
    private Date fecha;
    private String lugar;
    
    // Constructor vacío
    public ConciertoDTO() {
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
}