package com.ventaboletos.persistencia.fachada;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Concierto;
import java.util.ArrayList;
import java.util.List;

public class ConciertoRepositorio {

    private static ConciertoRepositorio instancia;
    
    private List<Concierto> baseDeDatosFalsa;

    private ConciertoRepositorio() {
        this.baseDeDatosFalsa = new ArrayList<>();
        System.out.println("LOG: Base de datos inicializada.");
    }

    public static ConciertoRepositorio getInstancia() {
        if (instancia == null) {
            instancia = new ConciertoRepositorio();
        }
        return instancia;
    }

    public void guardar(Concierto concierto) {
        baseDeDatosFalsa.add(concierto);
        System.out.println("LOG: Concierto guardado en memoria: " + concierto.getNombre());
    }

    public List<Concierto> obtenerTodos() {
        return baseDeDatosFalsa;
    }
    
    public boolean existeConcierto(String nombre) {
        for (Concierto c : baseDeDatosFalsa) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
}