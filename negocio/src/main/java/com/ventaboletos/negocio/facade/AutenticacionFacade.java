package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.UsuarioDTO;

/**
 * Implementación MOCK (Simulada) de la fachada de autenticación.
 * Valida contra datos "quemados" en el código.
 */
public class AutenticacionFacade implements IAutenticacion {

    @Override
    public UsuarioDTO autenticar(UsuarioDTO credenciales) throws Exception {
        
        System.out.println("----- FACADE MOCK (Autenticacion) -----");
        System.out.println("Intentando loguear a: " + credenciales.getNombreUsuario());

        // LÓGICA MOCK (Como si se usara una BD)
        
        // Simulación de un ADMIN
        if ("admin".equalsIgnoreCase(credenciales.getNombreUsuario()) && 
            "12345".equals(credenciales.getPassword())) {
            
            System.out.println(">>> Usuario 'admin' autenticado (Mock).");
            
            // Creamos el DTO de respuesta con datos completos
            UsuarioDTO usuarioRespuesta = new UsuarioDTO();
            usuarioRespuesta.setNombreUsuario("admin");
            usuarioRespuesta.setNombreCompleto("Administrador del Sistema");
            usuarioRespuesta.setRol("Admin");
            return usuarioRespuesta;
        }
        
        // Simulación de un CLIENTE
        if ("cliente".equalsIgnoreCase(credenciales.getNombreUsuario()) && 
            "cliente01".equals(credenciales.getPassword())) {
            
            System.out.println(">>> Usuario 'cliente' autenticado (Mock).");
            
            UsuarioDTO usuarioRespuesta = new UsuarioDTO();
            usuarioRespuesta.setNombreUsuario("cliente");
            usuarioRespuesta.setNombreCompleto("Juan Cliente Frecuente");
            usuarioRespuesta.setRol("Cliente");
            return usuarioRespuesta;
        }

        // Si no fue ninguno de los dos, lanzamos error
        System.err.println(">>> Credenciales incorrectas (Mock).");
        throw new Exception("Usuario o contrasenia incorrectos");
    }
}