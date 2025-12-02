package com.ventaboletos.dto;

/**
 *
 * @author alfre
 */

import java.util.Date;
import java.util.List;

public class VentaDTO {
    private String id;
    private String idConcierto; // Para saber de qué concierto es
    private String nombreCliente;
    private Date fechaVenta;
    private double total;
    private List<BoletoDTO> listaBoletos; // Una venta tiene muchos boletos

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(String idConcierto) {
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

    public List<BoletoDTO> getListaBoletos() {
        return listaBoletos;
    }

    public void setListaBoletos(List<BoletoDTO> listaBoletos) {
        this.listaBoletos = listaBoletos;
    }       
}