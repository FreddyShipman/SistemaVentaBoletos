package com.ventaboletos.negocio.facade.interfaces;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import java.util.List;

public interface IGestorCompra {
    List<ConciertoDTO> obtenerConciertosDisponibles(); 
    boolean procesarCompra(String nombreConcierto, String nombreZona, int cantidadBoletos, String emailUsuario);
}