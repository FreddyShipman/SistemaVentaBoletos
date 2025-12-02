package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO implements IVentaDAO {
    
    // Simulación de Base de Datos en Memoria (Static para que persista mientras corre la app)
    private static List<Venta> tablaVentas = new ArrayList<>();

    @Override
    public boolean registrarVenta(Venta venta) {
        // Simulamos Auto-Increment ID
        // Si la lista está vacía, el ID será 1, si tiene 1 elemento, será 2, etc.
        venta.setId(tablaVentas.size() + 1);
        
        // Guardamos en la lista (INSERT)
        return tablaVentas.add(venta);
    }

    @Override
    public List<Venta> obtenerVentasPorConcierto(int idConcierto) {
        // Simulamos un SELECT * WHERE id_concierto = ?
        List<Venta> resultado = new ArrayList<>();
        
        for (Venta v : tablaVentas) {
            if (v.getIdConcierto() == idConcierto) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}