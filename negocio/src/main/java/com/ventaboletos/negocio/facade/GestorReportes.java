package com.ventaboletos.negocio.facade;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ReporteDTO;
import com.ventaboletos.negocio.facade.interfaces.INotificador;
import com.ventaboletos.persistencia.dao.ConciertoDAO;
import com.ventaboletos.persistencia.dao.VentaDAO;
import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Venta;
import com.ventaboletos.persistencia.entidades.Zona;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorReportes {
    
    private ConciertoDAO conciertoDAO;
    private VentaDAO ventaDAO;
    private INotificador notificador;

    public GestorReportes(INotificador notificador) {
        this.conciertoDAO = ConciertoDAO.getInstancia();
        this.ventaDAO = VentaDAO.getInstancia();
        this.notificador = notificador;
    }

    public List<ReporteDTO> generarReporte(Date fechaInicio, Date fechaFin) {
        if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
            notificador.mostrarError("La fecha de inicio no puede ser posterior a la fecha fin.");
            return new ArrayList<>();
        }

        List<ReporteDTO> reporteFinal = new ArrayList<>();

        List<Concierto> conciertosEncontrados;
        if (fechaInicio == null || fechaFin == null) {
            conciertosEncontrados = conciertoDAO.obtenerTodos();
        } else {
            conciertosEncontrados = conciertoDAO.buscarPorRangoFechas(fechaInicio, fechaFin);
        }

        if (conciertosEncontrados.isEmpty()) {
            notificador.mostrarError("No se encontraron conciertos en ese periodo.");
            return reporteFinal;
        }

        for (Concierto c : conciertosEncontrados) {
            double totalDinero = 0;
            int totalBoletos = 0;
            int capacidadTotalConcierto = 0;

            for (Zona z : c.getZonas()) {
                capacidadTotalConcierto += z.getCapacidad();
            }

            List<Venta> ventasDelConcierto = ventaDAO.obtenerVentasPorConcierto(c.getNombre());
            
            for (Venta v : ventasDelConcierto) {
                totalDinero += v.getTotal();
                totalBoletos += v.getCantidadBoletos();
            }
            
            int capacidadReal = totalBoletos + capacidadTotalConcierto;

            reporteFinal.add(new ReporteDTO(
                c.getNombre(), 
                totalDinero, 
                totalBoletos, 
                capacidadReal
            ));
        }

        return reporteFinal;
    }
}