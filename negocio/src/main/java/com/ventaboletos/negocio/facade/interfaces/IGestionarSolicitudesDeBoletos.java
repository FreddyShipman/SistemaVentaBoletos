/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.negocio.facade.interfaces;

import com.ventaboletos.dto.SolicitudDTO;
import java.util.List;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
public interface IGestionarSolicitudesDeBoletos {
    // Crear una nueva queja o peticion
    boolean registrarSolicitud(SolicitudDTO solicitud);
    
    // Ver el estado de mis solicitudes
    List<SolicitudDTO> obtenerSolicitudesPorUsuario(String idUsuario);
    
    // Cancelar una solicitud que hice por error
    boolean cancelarSolicitud(String idSolicitud);
}
