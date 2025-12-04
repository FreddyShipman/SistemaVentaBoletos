package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

import java.util.UUID;

public class Boleto {
    private String idBoleto;
    private String codigoQR;
    private String nombreAsiento;
    private double precio;

    public Boleto(String nombreAsiento, double precio) {
        this.idBoleto = UUID.randomUUID().toString();
        this.nombreAsiento = nombreAsiento;
        this.precio = precio;
        this.codigoQR = "QR-" + idBoleto;
    }

    public String getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(String idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public String getNombreAsiento() {
        return nombreAsiento;
    }

    public void setNombreAsiento(String nombreAsiento) {
        this.nombreAsiento = nombreAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}