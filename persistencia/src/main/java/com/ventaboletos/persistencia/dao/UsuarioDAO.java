//package com.ventaboletos.persistencia.dao;
//
///**
// *
// * @author alfre
// */
//
//import com.ventaboletos.persistencia.entidades.Usuario;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UsuarioDAO implements IUsuarioDAO {
//    private static List<Usuario> bd = new ArrayList<>();
//
//    public UsuarioDAO() {
//        if(bd.isEmpty()) {
//            bd.add(new Usuario(1, "admin@test.com", "1234", "ADMIN"));
//            bd.add(new Usuario(2, "cliente@test.com", "1234", "CLIENTE"));
//        }
//    }
//
//    @Override
//    public Usuario buscarPorEmail(String email) {
//        for(Usuario u : bd) {
//            if(u.getEmail().equals(email)) return u;
//        }
//        return null;
//    }
//}