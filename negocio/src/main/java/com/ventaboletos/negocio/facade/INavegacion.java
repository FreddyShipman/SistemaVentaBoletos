/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ventaboletos.negocio.facade;

/**
 *
 * @author jonyco
 */

public interface INavegacion {
    // Método para cambiar de vista simplemente por nombre
    void cambiarVista(String nombreVista);
    
    // Método para cambiar de vista y pasar objetos (ej. pasar un BoletoDTO al detalle)
    void cambiarVista(String nombreVista, Object datos);
}