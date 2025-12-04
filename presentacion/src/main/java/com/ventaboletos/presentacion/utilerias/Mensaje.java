package com.ventaboletos.presentacion.utilerias; 

/**
 *
 * @author alfre
 */

import com.ventaboletos.negocio.facade.interfaces.INotificador;
import javax.swing.JOptionPane;

public class Mensaje implements INotificador {
    
    @Override
    public void mostrarConfirmacion(String texto) {
        JOptionPane.showMessageDialog(null, texto, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String texto) {
        JOptionPane.showMessageDialog(null, texto, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
}