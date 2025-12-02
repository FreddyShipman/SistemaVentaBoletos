package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.dto.VentaDTO;
import com.ventaboletos.negocio.facade.util.Conversor;
import com.ventaboletos.persistencia.entidades.Venta;
import com.ventaboletos.persistencia.fachada.IPersistencia;
import com.ventaboletos.persistencia.fachada.PersistenciaFacade;
import java.util.Date;

public class VentaBoletosFacade implements IVentaBoletos {

    private final IPersistencia persistencia;

    public VentaBoletosFacade() {
        this.persistencia = new PersistenciaFacade();
    }

    @Override
    public void registrarVenta(VentaDTO dto) throws Exception {
        // 1. VALIDACIONES
        if (dto.getNombreCliente() == null || dto.getNombreCliente().isBlank()) {
            throw new Exception("El nombre del cliente es obligatorio.");
        }
        if (dto.getListaBoletos() == null || dto.getListaBoletos().isEmpty()) {
            throw new Exception("Debes agregar al menos un boleto a la venta.");
        }

        // 2. LÓGICA DE NEGOCIO: CALCULAR TOTAL
        // No confiamos en el total que viene de la pantalla, lo recalculamos aquí por seguridad.
        double totalCalculado = 0.0;
        for (BoletoDTO b : dto.getListaBoletos()) {
            totalCalculado += b.getPrecio();
        }
        dto.setTotal(totalCalculado);
        dto.setFechaVenta(new Date()); // Asignamos la fecha exacta del servidor

        // 3. CONVERSIÓN
        Venta entidadVenta = Conversor.dtoToEntity(dto);

        // 4. PERSISTENCIA
        boolean exito = persistencia.registrarVenta(entidadVenta);
        
        if (!exito) {
            throw new Exception("Error al registrar la venta en el sistema.");
        }
    }
}