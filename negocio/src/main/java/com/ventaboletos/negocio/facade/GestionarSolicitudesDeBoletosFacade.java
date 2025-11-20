/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.negocio.facade;

import com.ventaboletos.dto.SolicitudDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
public class GestionarSolicitudesDeBoletosFacade  implements IGestionarSolicitudesDeBoletos{
    @Override
    public boolean registrarSolicitud(SolicitudDTO solicitud) {
        System.out.println(">>> Mock: Registrando nueva solicitud...");
        
        // Validación simulada
        if(solicitud.getDescripcion() == null || solicitud.getDescripcion().isEmpty()){
            System.err.println(">>> Mock Error: La descripcion es obligatoria");
            return false;
        }
        
        System.out.println("   Tipo: " + solicitud.getTipoSolicitud());
        System.out.println("   Usuario: " + solicitud.getIdUsuario());
        System.out.println(">>> Mock: Solicitud guardada con exito.");
        return true;
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudesPorUsuario(String idUsuario) {
        System.out.println(">>> Mock: Buscando solicitudes del usuario: " + idUsuario);
        
        List<SolicitudDTO> listaFalsa = new ArrayList<>();
        
        // Solicitud 1: Una peticion de reembolso
        SolicitudDTO s1 = new SolicitudDTO();
        s1.setIdSolicitud("SOL-100");
        s1.setIdUsuario(idUsuario);
        s1.setTipoSolicitud("REEMBOLSO");
        s1.setIdBoleto("TICKET-002"); // El boleto del festival que cancelamos antes
        s1.setDescripcion("Solicito reembolso por cancelacion del evento");
        s1.setEstado("EN_REVISION");
        s1.setFechaSolicitud(new Date());
        
        // Solicitud 2: Cambio de asiento para el partido
        SolicitudDTO s2 = new SolicitudDTO();
        s2.setIdSolicitud("SOL-101");
        s2.setIdUsuario(idUsuario);
        s2.setTipoSolicitud("CAMBIO_ASIENTO");
        s2.setIdBoleto("TICKET-005"); // El boleto del Clasico
        s2.setDescripcion("Quiero cambiar a zona de sombra");
        s2.setEstado("APROBADO"); // Ya lo aprobaron en el mock00
        s2.setFechaSolicitud(new Date());
        
        listaFalsa.add(s1);
        listaFalsa.add(s2);
        
        return listaFalsa;
    }

    @Override
    public boolean cancelarSolicitud(String idSolicitud) {
        System.out.println(">>> Mock: Cancelando la solicitud ID: " + idSolicitud);
        return true;
    }
}
