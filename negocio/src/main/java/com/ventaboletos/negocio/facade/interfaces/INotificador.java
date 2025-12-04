package com.ventaboletos.negocio.facade.interfaces;

/**
 *
 * @author alfre
 */

public interface INotificador {
    void mostrarError(String mensaje);
    void mostrarConfirmacion(String mensaje);
}