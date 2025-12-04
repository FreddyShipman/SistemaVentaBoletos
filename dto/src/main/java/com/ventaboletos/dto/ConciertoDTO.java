package com.ventaboletos.dto;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import java.util.Date;
import java.util.List;

public class ConciertoDTO {
    private String nombre;
    private String artista;
    private Date fecha;
    private String lugar;
    private List<ZonaDTO> zonas; 

    public ConciertoDTO(String nombre, String artista, Date fecha, String lugar, List<ZonaDTO> zonas) {
        this.nombre = nombre;
        this.artista = artista;
        this.fecha = fecha;
        this.lugar = lugar;
        this.zonas = zonas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public List<ZonaDTO> getZonas() {
        return zonas;
    }
}