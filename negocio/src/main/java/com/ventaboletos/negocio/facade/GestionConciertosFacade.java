//package com.ventaboletos.negocio.facade;
//
///**
// *
// * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
// */
//
//import com.ventaboletos.dto.ConciertoDTO;
//import com.ventaboletos.negocio.facade.util.Conversor;
//import com.ventaboletos.persistencia.entidades.Concierto;
//import com.ventaboletos.persistencia.fachada.IPersistencia;
//import com.ventaboletos.persistencia.fachada.PersistenciaFacade;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GestionConciertosFacade implements IGestionConciertos {
//
//    private final IPersistencia persistencia;
//
//    public GestionConciertosFacade() {
//        this.persistencia = new PersistenciaFacade();
//    }
//
//    @Override
//    public void registrarNuevoConcierto(ConciertoDTO dto) throws Exception {
//        // 1. Validaciones de Negocio
//        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
//            throw new Exception("El nombre del concierto es obligatorio.");
//        }
//        if (dto.getArtista() == null || dto.getArtista().isBlank()) {
//            throw new Exception("El artista es obligatorio.");
//        }
//        if (dto.getFecha() == null) {
//            throw new Exception("La fecha es obligatoria.");
//        }
//
//        // 2. Convertir y Guardar
//        Concierto entidad = Conversor.dtoToEntity(dto);
//        persistencia.registrarConcierto(entidad);
//    }
//
//    @Override
//    public List<ConciertoDTO> obtenerTodosLosConciertos() {
//        List<Concierto> entidades = persistencia.consultarConciertos();
//        List<ConciertoDTO> dtos = new ArrayList<>();
//        
//        for (Concierto c : entidades) {
//            dtos.add(Conversor.entityToDTO(c));
//        }
//        return dtos;
//    }
//}