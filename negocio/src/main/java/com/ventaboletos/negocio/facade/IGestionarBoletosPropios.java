/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.negocio.facade;

import com.ventaboletos.dto.BoletoDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */

// Esta es la interfaz que la vista va a conocer 
public interface IGestionarBoletosPropios {
    
    // La vista llamara a este metodo para obtener la lista 
    List<BoletoDTO> obtenerBoletosPorUsuario(String idUsuario);
    
    // La vista llamara a este metodo para buscar los boletos por fecha
    List<BoletoDTO> buscarBoletosPorFecha(String idUsuario, Date fechaCompra);
    
    // La vista llamara a este metodo para obtener los detalles de un boleto en especifico
    BoletoDTO obtenerBoleto(String idBoleto);
    
    boolean cancelarBoleto(String idBoleto);
    
    boolean actualizarBoleto(String idBoleto);
    
}
