package com.ventaboletos.persistencia.entidades;

/**
 *
 * @author alfre
 */

public class Boleto {
    // Atributos privados
    private int id;
    private String nombreAsistente;
    private String asiento;
    private double precio;

    // Constructor vacío
    public Boleto() {
    }
    
    // Constructor con datos
    public Boleto(String nombre, String asiento, double precio) {
        this.nombreAsistente = nombre;
        this.asiento = asiento;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAsistente() {
        return nombreAsistente;
    }

    public void setNombreAsistente(String nombreAsistente) {
        this.nombreAsistente = nombreAsistente;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    } 
}