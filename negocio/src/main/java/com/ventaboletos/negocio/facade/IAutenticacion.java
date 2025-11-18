package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.UsuarioDTO;

/**
 * Interfaz para el subsistema de Autenticación.
 * Define el contrato que la vista usará.
 */
public interface IAutenticacion {
    
    /**
     * Valida las credenciales de un usuario.
     * @param credenciales DTO que contiene el usuario y password.
     * @return UsuarioDTO con los datos completos (nombre, rol) si es exitoso.
     * @throws Exception si la autenticación falla (usuario no existe, pass incorrecto).
     */
    UsuarioDTO autenticar(UsuarioDTO credenciales) throws Exception;
    
}