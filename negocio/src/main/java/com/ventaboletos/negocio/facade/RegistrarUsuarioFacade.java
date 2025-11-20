/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.negocio.facade;

import com.ventaboletos.dto.UsuarioDTO;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
public class RegistrarUsuarioFacade implements IRegistrarUsuario {

    @Override
    public boolean registrarNuevoUsuario(UsuarioDTO usuario) throws Exception {
        System.out.println(">>> Mock: Intentando registrar nuevo usuario: " + usuario.getNombreUsuario());

        // 1. Validacion de datos obligatorios
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()
                || usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new Exception("El usuario y la contraseña son obligatorios");
        }

        // 2. Validacion de duplicados(simulada)
        // Simulamos que 'admin' y 'cliente' ya existen en la BD
        if ("admin".equalsIgnoreCase(usuario.getNombreUsuario())
                || "cliente".equalsIgnoreCase(usuario.getNombreUsuario())) {
            System.err.println(">>> Mock: Error,el usuario ya existe en la BD");
            throw new Exception("El nombre de usuario ya esta ocupado");
        }

        // 3. Validacion de contraseña segura_ejemplo simple
        if (usuario.getPassword().length() < 4) {
            throw new Exception("La contraseña es muy corta (minimo 4 caracteres).");
        }

        // 4. Simulacion de guardado
        System.out.println(">>> Mock: Guardando usuario en la base de datos...");
        System.out.println("   -> Nombre Completo: " + usuario.getNombreCompleto());
        System.out.println("   -> Rol asignado: " + usuario.getRol());

        System.out.println(">>> Mock: Usuario registrado con EXITO");
        return true;
    }
}
