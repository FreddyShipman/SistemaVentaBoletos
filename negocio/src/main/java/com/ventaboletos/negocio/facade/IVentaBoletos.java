package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.BoletoDTO;
import java.util.List;

public interface IVentaBoletos {
    
    /**
     * Registra la compra de un boleto.
     * Valida disponibilidad (simulada) y guarda.
     */
    void comprarBoleto(BoletoDTO boleto) throws Exception;
    
    /**
     * Busca todos los boletos comprados por un usuario específico.
     * Útil para el historial de compras ("Mis Boletos").
     */
    List<BoletoDTO> consultarHistorialCompras(String idUsuario);
}
