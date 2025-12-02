package com.ventaboletos.persistencia.dao;

/**
 *
 * @author alfre
 */

import com.ventaboletos.persistencia.entidades.Usuario;

public interface IUsuarioDAO {
    Usuario buscarPorEmail(String email);
}