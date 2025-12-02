package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Concierto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConciertoDAO implements IConciertoDAO {

    private static List<Concierto> bd = new ArrayList<>();

    static {
        bd.add(new Concierto(1, "Luis Miguel Tour 2024", "Luis Miguel", new Date(), "Arena CDMX"));
        bd.add(new Concierto(2, "The Eras Tour", "Taylor Swift", new Date(), "Foro Sol"));
        bd.add(new Concierto(3, "Mañana Será Bonito", "Karol G", new Date(), "Estadio Azteca"));
        bd.add(new Concierto(4, "Rock en tu Idioma", "Caifanes", new Date(), "Palacio de los Deportes"));
        bd.add(new Concierto(5, "Prófugos del Anexo", "Julión y Alfredo", new Date(), "Estadio Banorte"));
    }

    @Override
    public boolean agregar(Concierto c) {
        int maxId = 0;
        for (Concierto existente : bd) {
            if (existente.getId() > maxId) {
                maxId = existente.getId();
            }
        }
        c.setId(maxId + 1);
        
        return bd.add(c);
    }

    @Override
    public List<Concierto> buscarTodos() {
        return new ArrayList<>(bd);
    }
}