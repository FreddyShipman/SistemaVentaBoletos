package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

import java.util.Date;
import java.util.List;

public class Venta {
    private String idVenta;
    private Date fecha;
    private double total;
    private Usuario cliente;
    private Concierto concierto;
    private List<Boleto> boletosVendidos;

    public Venta(Date fecha, double total, Usuario cliente, Concierto concierto, List<Boleto> boletosVendidos) {
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.concierto = concierto;
        this.boletosVendidos = boletosVendidos;
        this.idVenta = "VEN-" + System.currentTimeMillis();
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public List<Boleto> getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(List<Boleto> boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }
    
    public int getCantidadBoletos() {
        if (boletosVendidos == null) {
            return 0;
        }
        return boletosVendidos.size();
    }
}