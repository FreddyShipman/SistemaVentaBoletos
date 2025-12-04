package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.dto.ZonaDTO;
import com.ventaboletos.negocio.facade.GestorCompraBoleto;
import com.ventaboletos.negocio.facade.GestorConciertos;
import com.ventaboletos.presentacion.utilerias.Mensaje;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


public class Main {
    public static void main(String[] args) {
        
        System.out.println("--- CARGANDO SISTEMA Y DATOS MOCK ---");
        
        // =========================================================================
        // 1. CARGA INICIAL DE DATOS (SEEDING) - ¡NO COMENTAR ESTO!
        // =========================================================================
        // Necesitamos esto para que los Singletons (DAOs) tengan información
        // independientemente de qué pantalla abras.
        
        Mensaje msg = new Mensaje();
        GestorConciertos gestorAdmin = new GestorConciertos(msg);
        GestorCompraBoleto gestorVentas = new GestorCompraBoleto(msg);

        // --- FECHAS ---
        Calendar cal = Calendar.getInstance();
        Date hoy = new Date();
        
        cal.add(Calendar.DAY_OF_YEAR, 30); // Fecha Futura (en 30 días)
        Date fechaFutura = cal.getTime();

        // --- CONCIERTO 1: PARA COMPRAR (FUTURO) ---
        List<ZonaDTO> zonas1 = new ArrayList<>();
        zonas1.add(new ZonaDTO("General A", 1200.0, 500));
        zonas1.add(new ZonaDTO("VIP", 3500.0, 50));
        
        gestorAdmin.registrarConcierto(new ConciertoDTO(
            "World Tour 2025", "Bad Bunny", fechaFutura, "Estadio Azteca", zonas1
        ));

        // --- CONCIERTO 2: PARA REPORTES (HOY/PASADO) ---
        List<ZonaDTO> zonas2 = new ArrayList<>();
        zonas2.add(new ZonaDTO("Gradas", 500.0, 2000));
        zonas2.add(new ZonaDTO("Platea", 1500.0, 100));
        
        gestorAdmin.registrarConcierto(new ConciertoDTO(
            "Luis Miguel en Vivo", "Luis Miguel", hoy, "Arena CDMX", zonas2
        ));

        // --- VENTAS SIMULADAS (Para que la gráfica de reportes salga bonita) ---
        // Simulamos que alguien ya compró boletos de Luis Miguel
        gestorVentas.procesarCompra("Luis Miguel en Vivo", "Platea", 5, "cliente1@mail.com"); // $7,500
        gestorVentas.procesarCompra("Luis Miguel en Vivo", "Gradas", 10, "cliente2@mail.com"); // $5,000
        // Y unas pocas de Bad Bunny
        gestorVentas.procesarCompra("World Tour 2025", "VIP", 2, "fan@mail.com"); // $7,000

        System.out.println("Datos cargados correctamente en memoria RAM.");
        System.out.println("-------------------------------------------------");


        // =========================================================================
        // 2. SELECCIÓN DE PANTALLA
        // =========================================================================

        /* // --- BLOQUE 1: CASO DE USO "GESTIONAR CONCIERTOS" (ADMIN) ---
        // Abre la pantalla para registrar un nuevo evento desde cero.
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaNuevoConcierto().setVisible(true);
        });
        */

        /*
        // --- BLOQUE 2: CASO DE USO "CONSULTAR CONCIERTOS" (CLIENTE) ---
        // Abre el catálogo. Debe mostrar a "Bad Bunny" y "Luis Miguel".
        // Si comentas la creación de datos de arriba, saldrá vacío o error.
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaCatalogo().setVisible(true);
        });
        */

        /*
        // --- BLOQUE 3: CASO DE USO "COMPRAR BOLETO" (CLIENTE) ---
        // Abre la pantalla de compra. 
        // Prueba seleccionar "Bad Bunny" -> "VIP" y compra boletos.
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaCompraBoleto().setVisible(true);
        });
        */

        
        // --- BLOQUE 4: CASO DE USO "CONSULTAR REPORTES" (ADMIN) ---
        // Abre la pantalla de gráficas.
        // Deja las fechas vacías y dale "Generar" para ver las ventas simuladas arriba.
        java.awt.EventQueue.invokeLater(() -> {
            new PantallaReportes().setVisible(true);
        });
        
    }
}