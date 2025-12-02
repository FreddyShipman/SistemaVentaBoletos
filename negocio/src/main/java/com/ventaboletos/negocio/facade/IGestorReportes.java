package com.ventaboletos.negocio.facade;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ReporteDTO;
import java.util.List;

public interface IGestorReportes {
    // Devuelve una lista con el resumen de cada concierto
    List<ReporteDTO> generarReporteGeneral();
}