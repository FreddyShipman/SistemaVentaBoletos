package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.ConciertoDTO;
import java.util.List;

// Esta es la Interfaz que la Vista (Presentacion) va a conocer
public interface IGestionConciertos {
    
    // La vista llamará a este método para guardar
    void registrarNuevoConcierto(ConciertoDTO dto) throws Exception;
    
    // La vista llamará a este para obtener la lista
    List<ConciertoDTO> obtenerTodosLosConciertos();
}