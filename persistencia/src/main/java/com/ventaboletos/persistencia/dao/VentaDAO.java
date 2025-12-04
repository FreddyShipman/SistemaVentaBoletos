package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Venta;
import java.util.ArrayList;
import java.util.List;


public class VentaDAO {
    private static VentaDAO instancia;
    private List<Venta> baseDeDatosVentas;

    private VentaDAO() {
        this.baseDeDatosVentas = new ArrayList<>();
    }

    public static VentaDAO getInstancia() {
        if (instancia == null) {
            instancia = new VentaDAO();
        }
        return instancia;
    }

    public void guardar(Venta venta) {
        baseDeDatosVentas.add(venta);
        System.out.println("LOG DAO: Venta guardada con ID " + venta.getIdVenta());
    }

    public List<Venta> obtenerTodas() {
        return baseDeDatosVentas;
    }
    
    public java.util.List<Venta> obtenerVentasPorConcierto(String nombreConcierto) {
        java.util.List<Venta> resultado = new java.util.ArrayList<>();
        if (baseDeDatosVentas != null) {
            for (Venta v : baseDeDatosVentas) {
                if (v.getConcierto().getNombre().equalsIgnoreCase(nombreConcierto)) {
                    resultado.add(v);
                }
            }
        }
        return resultado;
    }
}