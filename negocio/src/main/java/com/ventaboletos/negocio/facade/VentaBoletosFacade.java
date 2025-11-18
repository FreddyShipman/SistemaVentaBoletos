package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.BoletoDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaBoletosFacade implements IVentaBoletos {

    @Override
    public void comprarBoleto(BoletoDTO boleto) throws Exception {
        System.out.println("----- FACADE MOCK (Ventas) -----");
        System.out.println("Procesando compra para: " + boleto.getIdUsuario());
        System.out.println("Asiento solicitado: " + boleto.getFila() + "-" + boleto.getAsiento());

        // SIMULACIÓN DE VALIDACIÓN
        // Regla Mock: Si piden el asiento "A-1", decimos que está ocupado.
        if ("A".equalsIgnoreCase(boleto.getFila()) && "1".equals(boleto.getAsiento())) {
            throw new Exception("El asiento A-1 ya esta ocupado. Por favor seleccione otro.");
        }

        // Regla Mock: Si el costo es 0 o negativo, error.
        if (boleto.getCosto() <= 0) {
            throw new Exception("El costo del boleto es invalido.");
        }

        // Si pasa, simulamos éxito
        boleto.setEstado("VENDIDO");
        boleto.setFechaCompra(new Date());
        boleto.setIdBoleto("TICKET-" + (int)(Math.random() * 10000)); // ID generado al azar
        
        System.out.println(">>> Compra EXITOSA. ID Boleto: " + boleto.getIdBoleto());
    }

    @Override
    public List<BoletoDTO> consultarHistorialCompras(String idUsuario) {
        System.out.println("----- FACADE MOCK (Ventas) -----");
        System.out.println("Buscando historial para usuario: " + idUsuario);
        
        List<BoletoDTO> historial = new ArrayList<>();
        
        // Simulamos que este usuario ya compró 2 boletos antes
        BoletoDTO b1 = new BoletoDTO();
        b1.setIdBoleto("TICKET-101");
        b1.setNombreConcierto("Concierto 1");
        b1.setFila("B");
        b1.setAsiento("20");
        b1.setCosto(500.0);
        b1.setEstado("VENDIDO");
        
        BoletoDTO b2 = new BoletoDTO();
        b2.setIdBoleto("TICKET-102");
        b2.setNombreConcierto("Concierto 1");
        b2.setFila("B");
        b2.setAsiento("21");
        b2.setCosto(500.0);
        b2.setEstado("VENDIDO");
        
        historial.add(b1);
        historial.add(b2);
        
        return historial;
    }
}