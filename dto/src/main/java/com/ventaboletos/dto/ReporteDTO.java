package com.ventaboletos.dto;

/**
 *
 * @author alfre
 */


public class ReporteDTO {
    private String nombreConcierto;
    private double totalIngresos;
    private int boletosVendidos;
    private int capacidadTotal;

    public ReporteDTO() {
    }

    public ReporteDTO(String nombreConcierto, double totalIngresos, int boletosVendidos, int capacidadTotal) {
        this.nombreConcierto = nombreConcierto;
        this.totalIngresos = totalIngresos;
        this.boletosVendidos = boletosVendidos;
        this.capacidadTotal = capacidadTotal;
    }

    public String getNombreConcierto() {
        return nombreConcierto;
    }

    public void setNombreConcierto(String nombreConcierto) {
        this.nombreConcierto = nombreConcierto;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(int boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }
    
    public int getPorcentajeOcupacion() {
        if (capacidadTotal == 0) return 0;
        return (boletosVendidos * 100) / capacidadTotal;
    }
}