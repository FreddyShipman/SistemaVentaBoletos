package com.ventaboletos.persistencia.fachada;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Usuario;
import com.ventaboletos.persistencia.entidades.Venta;
import java.util.List;

public interface IPersistencia {
    boolean registrarConcierto(Concierto c);
    List<Concierto> consultarConciertos();
    boolean registrarVenta(Venta v);
    List<Venta> obtenerVentasPorConcierto(int idConcierto);
    Usuario buscarUsuario(String email);
}