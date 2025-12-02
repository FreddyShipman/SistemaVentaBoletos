package com.ventaboletos.persistencia.fachada;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.dao.ConciertoDAO;
import com.ventaboletos.persistencia.dao.IConciertoDAO;
import com.ventaboletos.persistencia.dao.IUsuarioDAO;
import com.ventaboletos.persistencia.dao.IVentaDAO;
import com.ventaboletos.persistencia.dao.UsuarioDAO;
import com.ventaboletos.persistencia.dao.VentaDAO;
import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Usuario;
import com.ventaboletos.persistencia.entidades.Venta;
import java.util.List;

public class PersistenciaFacade implements IPersistencia {
    private final IConciertoDAO conciertoDAO;
    private final IVentaDAO ventaDAO;
    private final IUsuarioDAO usuarioDAO;

    public PersistenciaFacade() {
        this.conciertoDAO = new ConciertoDAO();
        this.ventaDAO = new VentaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public boolean registrarConcierto(Concierto c) { 
        return conciertoDAO.agregar(c); 
    }
    
    @Override
    public List<Concierto> consultarConciertos() { 
        return conciertoDAO.buscarTodos(); 
    }
    
    @Override
    public boolean registrarVenta(Venta venta) { 
        return ventaDAO.registrarVenta(venta);
    }
    
    @Override
    public Usuario buscarUsuario(String email) { 
        return usuarioDAO.buscarPorEmail(email); 
    }
    
    @Override
    public List<Venta> obtenerVentasPorConcierto(int idConcierto) {
        return ventaDAO.obtenerVentasPorConcierto(idConcierto);
}
}