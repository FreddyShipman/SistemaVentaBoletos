/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.negocio.facade;

import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.negocio.facade.IGestionarBoletosPropios;
import java.util.Date;
import java.util.List;
/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
public class GestionarBoletosPropiosFacade implements IGestionarBoletosPropios{


    @Override
    public List<BoletoDTO> buscarBoletosPorFecha(String idUsuario, Date fechaCompra) {
        
        //Terminar
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BoletoDTO> obtenerBoletosPorUsuario(String idUsuario) {
        // Terminar
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BoletoDTO obtenerBoleto(String idBoleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cancelarBoleto(String idBoleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizarBoleto(String idBoleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
   
}
