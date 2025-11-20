/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ventaboletos.negocio.facade;

import com.ventaboletos.dto.UsuarioDTO;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
public interface IRegistrarUsuario {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario Datos del usuario a registrar.
     * @return true si el registro fue exitoso.
     * @throws Exception si el usuario ya existe o faltan datos.
     */
    boolean registrarNuevoUsuario(UsuarioDTO usuario) throws Exception;
}
