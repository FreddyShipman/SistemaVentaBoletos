package com.ventaboletos.negocio.facade;

/**
 *
 * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */

import com.ventaboletos.dto.ConciertoDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Esta es la implementación "Mock" (Simulada)
// Fíjate que implementa la interfaz
public class GestionConciertosFacade implements IGestionConciertos {

    @Override
    public void registrarNuevoConcierto(ConciertoDTO dto) throws Exception {
        // 1. Simular validación
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new Exception("El nombre es obligatorio (simulacion de error)");
        }
        
        // 2. Simular que lo guarda
        System.out.println("--- FACADE MOCK ---");
        System.out.println("Guardando concierto (simulado): " + dto.getNombre());
        System.out.println("Artista: " + dto.getArtista());
        System.out.println("Fecha: " + dto.getFecha());
        System.out.println("Lugar: " + dto.getLugar());
        System.out.println("--- FIN SIMULACION ---");
        // No se guarda nada realmente, solo simula un guardado, como se imprime en consola
    }

    @Override
    public List<ConciertoDTO> obtenerTodosLosConciertos() {
        // Devolvemos una lista falsa   
        System.out.println("--- FACADE MOCK ---");
        System.out.println("Devolviendo lista de conciertos (simulada)");
        System.out.println("--- FIN SIMULACION ---");
        
        List<ConciertoDTO> listaFalsa = new ArrayList<>();
        
        ConciertoDTO c1 = new ConciertoDTO();
        c1.setId("1");
        c1.setNombre("Concierto 1");
        c1.setArtista("Artista Mock");
        c1.setFecha(new Date()); //Da la fecha de hoy
        c1.setLugar("Estadio 1");
        
        ConciertoDTO c2 = new ConciertoDTO();
        c2.setId("2");
        c2.setNombre("Concierto 2");
        c2.setArtista("Banda Mock");
        c2.setFecha(new Date());
        c2.setLugar("Arena ITSON");
        
        listaFalsa.add(c1);
        listaFalsa.add(c2);
        
        return listaFalsa;
    }
}