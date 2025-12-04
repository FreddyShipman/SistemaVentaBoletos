package com.ventaboletos.negocio.facade.util;

import com.ventaboletos.negocio.facade.GestorCompraBoleto;
import com.ventaboletos.negocio.facade.interfaces.IGestorCompra;
import com.ventaboletos.negocio.facade.interfaces.INotificador;

/**
 *
 * @author alfre
 */

public class FabricaNegocio {
    
    public static IGestorCompra crearGestorCompra(INotificador notificador) {
        return new GestorCompraBoleto(notificador);
    }
}