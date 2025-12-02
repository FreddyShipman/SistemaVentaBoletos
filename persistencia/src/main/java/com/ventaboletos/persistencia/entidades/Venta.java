package com.ventaboletos.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author alfre
 */

public class Venta {
    // Atributos privados
    private int id;
    private int idConcierto;
    private String nombreCliente;
    private Date fechaVenta;
    private double total;
    private List<Boleto> boletos;

    // Constructor vacío
    public Venta() {
    }
    
    // Constructor con datos
    public Venta(int id, int idConcierto, String nombreCliente, Date fechaVenta, double total, List<Boleto> boletos) {
        this.id = id;
        this.idConcierto = idConcierto;
        this.nombreCliente = nombreCliente;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.boletos = boletos;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(int idConcierto) {
        this.idConcierto = idConcierto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }
    
}