package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Concierto {
    private String nombre;
    private String artista;
    private Date fecha;
    private String lugar;
    private List<Zona> zonas; 

    public Concierto(String nombre, String artista, Date fecha, String lugar) {
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.zonas = new ArrayList<>();
    }

    public void agregarZona(Zona zona) {
        this.zonas.add(zona);
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

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }
}