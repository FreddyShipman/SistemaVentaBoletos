//package com.ventaboletos.negocio.facade.util;
//
///**
// *
// * @author alfre
// */
//
//import com.ventaboletos.dto.BoletoDTO;
//import com.ventaboletos.dto.ConciertoDTO;
//import com.ventaboletos.dto.VentaDTO;
//import com.ventaboletos.persistencia.entidades.Boleto;
//import com.ventaboletos.persistencia.entidades.Concierto;
//import com.ventaboletos.persistencia.entidades.Venta;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Conversor {
//
//    // --- CONCIERTOS ---
//    public static Concierto dtoToEntity(ConciertoDTO dto) {
//        Concierto c = new Concierto();
//        c.setNombre(dto.getNombre());
//        c.setArtista(dto.getArtista());
//        c.setFecha(dto.getFecha());
//        c.setLugar(dto.getLugar());
//        return c;
//    }
//
//    public static ConciertoDTO entityToDTO(Concierto c) {
//        ConciertoDTO dto = new ConciertoDTO();
//        dto.setId(String.valueOf(c.getId())); // Convertimos int a String para la vista
//        dto.setNombre(c.getNombre());
//        dto.setArtista(c.getArtista());
//        dto.setFecha(c.getFecha());
//        dto.setLugar(c.getLugar());
//        return dto;
//    }
//
//    // --- BOLETOS ---
//    public static Boleto dtoToEntity(BoletoDTO dto) {
//        return new Boleto(dto.getNombreAsistente(), dto.getAsiento(), dto.getPrecio());
//    }
//    
//    public static BoletoDTO entityToDTO(Boleto b) {
//        BoletoDTO dto = new BoletoDTO();
//        dto.setId(String.valueOf(b.getId()));
//        dto.setNombreAsistente(b.getNombreAsistente());
//        dto.setAsiento(b.getAsiento());
//        dto.setPrecio(b.getPrecio());
//        return dto;
//    }
//
//    // --- VENTAS ---
//    public static Venta dtoToEntity(VentaDTO dto) {
//        Venta v = new Venta();
//        if(dto.getIdConcierto() != null) {
//            v.setIdConcierto(Integer.parseInt(dto.getIdConcierto()));
//        }
//        v.setNombreCliente(dto.getNombreCliente());
//        v.setFechaVenta(dto.getFechaVenta());
//        v.setTotal(dto.getTotal());
//        
//        List<Boleto> listaBoletos = new ArrayList<>();
//        if(dto.getListaBoletos() != null) {
//            for(BoletoDTO b : dto.getListaBoletos()) {
//                listaBoletos.add(dtoToEntity(b));
//            }
//        }
//        v.setBoletos(listaBoletos);
//        return v;
//    }
//}