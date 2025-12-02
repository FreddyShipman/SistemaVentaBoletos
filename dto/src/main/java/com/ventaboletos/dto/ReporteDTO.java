package com.ventaboletos.dto;

/**
 *
 * @author alfre
 */

public class ReporteDTO {
    private String nombreConcierto;
    private int boletosVendidos;
    private double ingresoTotal;

    public ReporteDTO() {
    }

    public ReporteDTO(String nombreConcierto, int boletosVendidos, double ingresoTotal) {
        this.nombreConcierto = nombreConcierto;
        this.boletosVendidos = boletosVendidos;
        this.ingresoTotal = ingresoTotal;
    }

    // Getters y Setters
    public String getNombreConcierto() {
        return nombreConcierto;
    }

    public void setNombreConcierto(String nombreConcierto) {
        this.nombreConcierto = nombreConcierto;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(int boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }
    
}