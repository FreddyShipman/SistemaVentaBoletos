package com.ventaboletos.negocio.facade;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.dto.ZonaDTO;
import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Zona;
import com.ventaboletos.negocio.facade.interfaces.INotificador;
import com.ventaboletos.persistencia.dao.ConciertoDAO;
import java.util.ArrayList;
import java.util.List;

public class GestorConciertos {
    
    private ConciertoDAO repositorio;
    private INotificador notificador; 

    public GestorConciertos(INotificador notificador) {
        this.repositorio = ConciertoDAO.getInstancia();
        this.notificador = notificador; 
    }

    public void registrarConcierto(ConciertoDTO dto) {
        if (!validarDatos(dto)) {
            return;
        }
        Concierto nuevoConcierto = new Concierto(
            dto.getNombre(), dto.getArtista(), dto.getFecha(), dto.getLugar()
        );

        List<ZonaDTO> listaZonasDTO = dto.getZonas();
        if (listaZonasDTO != null) {
            for (ZonaDTO zDto : listaZonasDTO) {
                Zona zonaEntidad = new Zona(zDto.getNombre(), zDto.getPrecio(), zDto.getCapacidad());
                nuevoConcierto.agregarZona(zonaEntidad);
            }
        }
        repositorio.guardar(nuevoConcierto);
        
        notificador.mostrarConfirmacion("Concierto guardado: " + nuevoConcierto.getNombre());
    }

    private boolean validarDatos(ConciertoDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isEmpty() || 
            dto.getFecha() == null) {
            
            notificador.mostrarError("Datos obligatorios faltantes.");
            return false;
        }
        return true;
    }
    
    public List<ConciertoDTO> obtenerConciertosDisponibles() {
        List<Concierto> activos = repositorio.buscarActivos();
        
        if (activos.isEmpty()) {
            notificador.mostrarError("No hay conciertos programados por el momento.");
            return new ArrayList<>(); 
        }

        List<ConciertoDTO> dtos = new ArrayList<>();
        for (Concierto c : activos) {
            List<ZonaDTO> zonasDto = new ArrayList<>();
            for (Zona z : c.getZonas()) {
                zonasDto.add(new ZonaDTO(z.getNombre(), z.getPrecio(), z.getCapacidad()));
            }
            dtos.add(new ConciertoDTO(c.getNombre(), c.getArtista(), c.getFecha(), c.getLugar(), zonasDto));
        }
        
        return dtos;
    }
}