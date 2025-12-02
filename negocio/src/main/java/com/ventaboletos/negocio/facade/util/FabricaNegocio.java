package com.ventaboletos.negocio.facade.util;

/**
 *
 * @author alfre
 */

import com.ventaboletos.negocio.facade.GestionConciertosFacade;
import com.ventaboletos.negocio.facade.GestorReportesFacade;
import com.ventaboletos.negocio.facade.IGestionConciertos;
import com.ventaboletos.negocio.facade.IGestorReportes;
import com.ventaboletos.negocio.facade.IVentaBoletos;
import com.ventaboletos.negocio.facade.VentaBoletosFacade;

public class FabricaNegocio {
    
    public static IGestionConciertos crearGestionConciertos() {
        return new GestionConciertosFacade();
    }
    
    public static IVentaBoletos crearVentaBoletos() {
        return new VentaBoletosFacade();
    }
    
    public static IGestorReportes crearGestorReportes() {
        return new GestorReportesFacade();
    }
}