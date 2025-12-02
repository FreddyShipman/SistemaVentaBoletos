package com.ventaboletos.persistencia.dao;

import com.ventaboletos.persistencia.entidades.Venta;
import java.util.List;

/**
 *
 * @author alfre
 */

public interface IVentaDAO {
    boolean registrarVenta(Venta venta); 
    List<Venta> obtenerVentasPorConcierto(int idConcierto);
}