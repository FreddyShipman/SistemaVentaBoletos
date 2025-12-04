package com.ventaboletos.dto;

/**
 *
 * @author alfre
 */

public class ZonaDTO {
    private String nombre;
    private double precio;
    private int capacidad;

    public ZonaDTO(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Zona: " + nombre + " ($" + precio + ")";
    }
}