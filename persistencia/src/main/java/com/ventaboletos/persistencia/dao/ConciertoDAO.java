package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Concierto;
import java.util.ArrayList;
import java.util.List;

public class ConciertoDAO {

    private static ConciertoDAO instancia;
    private List<Concierto> baseDeDatosFalsa;

    private ConciertoDAO() {
        this.baseDeDatosFalsa = new ArrayList<>();
        System.out.println("LOG: Base de datos (Mock) inicializada.");
    }
    
    public static ConciertoDAO getInstancia() {
        if (instancia == null) {
            instancia = new ConciertoDAO();
        }
        return instancia;
    }

    public void guardar(Concierto concierto) {
        baseDeDatosFalsa.add(concierto);
        System.out.println("LOG DAO: Se guardó el concierto -> " + concierto.getNombre());
    }

    public List<Concierto> obtenerTodos() {
        return baseDeDatosFalsa;
    }
    
    public java.util.List<Concierto> buscarPorRangoFechas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        java.util.List<Concierto> resultado = new java.util.ArrayList<>();
        for (Concierto c : baseDeDatosFalsa) {
            if ((c.getFecha().after(fechaInicio) && c.getFecha().before(fechaFin)) || 
                c.getFecha().equals(fechaInicio) || c.getFecha().equals(fechaFin)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
    
    public java.util.List<Concierto> buscarActivos() {
        java.util.List<Concierto> activos = new java.util.ArrayList<>();
        java.util.Date hoy = new java.util.Date();
        
        for (Concierto c : baseDeDatosFalsa) {
            if (c.getFecha().after(hoy) || c.getFecha().equals(hoy)) {
                activos.add(c);
            }
        }
        return activos;
    }
}