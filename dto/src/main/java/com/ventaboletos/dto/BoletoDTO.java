package com.ventaboletos.dto;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import java.util.Date;

public class BoletoDTO {
    
    private String idBoleto;
    private String idConcierto; // Referencia al concierto
    private String nombreConcierto; // Para mostrarlo fácil en la lista
    private String idUsuario;   // Quién lo compró
    private String fila;
    private String asiento;
    private double costo;
    private Date fechaCompra;
    private String estado; // "VENDIDO", "CANCELADO", "DISPONIBLE"

    // Constructor Vacío
    public BoletoDTO() {
    }

    // Getters y Setters
    public String getIdBoleto() { return idBoleto; }
    public void setIdBoleto(String idBoleto) { this.idBoleto = idBoleto; }
    
    public String getIdConcierto() { return idConcierto; }
    public void setIdConcierto(String idConcierto) { this.idConcierto = idConcierto; }

    public String getNombreConcierto() { return nombreConcierto; }
    public void setNombreConcierto(String nombreConcierto) { this.nombreConcierto = nombreConcierto; }
    
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getFila() { return fila; }
    public void setFila(String fila) { this.fila = fila; }
    
    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }
    
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    
    public Date getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}