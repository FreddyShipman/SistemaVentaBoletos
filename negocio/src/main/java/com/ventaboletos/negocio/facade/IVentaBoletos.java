package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.VentaDTO;

public interface IVentaBoletos {
    
    // La pantalla nos manda una venta tentativa y nosotros la procesamos
    void registrarVenta(VentaDTO ventaDTO) throws Exception;
}
