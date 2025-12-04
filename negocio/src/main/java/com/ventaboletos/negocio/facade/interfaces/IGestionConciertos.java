package com.ventaboletos.negocio.facade.interfaces;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.ConciertoDTO;
import java.util.List;

public interface IGestionConciertos {
    void registrarNuevoConcierto(ConciertoDTO dto) throws Exception;
    List<ConciertoDTO> obtenerTodosLosConciertos();
}