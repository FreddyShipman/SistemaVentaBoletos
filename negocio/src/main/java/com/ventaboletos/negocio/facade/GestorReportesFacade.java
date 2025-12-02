package com.ventaboletos.negocio.facade;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ReporteDTO;
import com.ventaboletos.persistencia.entidades.Boleto;
import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Venta;
import com.ventaboletos.persistencia.fachada.IPersistencia;
import com.ventaboletos.persistencia.fachada.PersistenciaFacade;
import java.util.ArrayList;
import java.util.List;

public class GestorReportesFacade implements IGestorReportes {

    private final IPersistencia persistencia;

    public GestorReportesFacade() {
        this.persistencia = new PersistenciaFacade();
    }

    @Override
    public List<ReporteDTO> generarReporteGeneral() {
        List<ReporteDTO> reporteFinal = new ArrayList<>();

        // 1. Obtener todos los conciertos
        List<Concierto> conciertos = persistencia.consultarConciertos();

        // 2. Para cada concierto, calcular sus ventas
        for (Concierto c : conciertos) {
            List<Venta> todasLasVentas = persistencia.obtenerVentasPorConcierto(c.getId());
            
            int totalBoletos = 0;
            double totalDinero = 0.0;

            for (Venta v : todasLasVentas) {
                if (v.getBoletos() != null) {
                    totalBoletos += v.getBoletos().size();
                    for (Boleto b : v.getBoletos()) {
                        totalDinero += b.getPrecio();
                    }
                }
            }

            ReporteDTO dto = new ReporteDTO();
            dto.setNombreConcierto(c.getNombre());
            dto.setBoletosVendidos(totalBoletos);
            dto.setIngresoTotal(totalDinero);
            
            reporteFinal.add(dto);
        }

        return reporteFinal;
    }
}