package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Concierto;
import java.util.List;

public interface IConciertoDAO {
    boolean agregar(Concierto concierto);
    List<Concierto> buscarTodos();
}