/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class FrmHistorial extends JPanel {

    // Referencia para navegar
    private INavegacion navegador;

    // Componentes públicos
    public JTextField txtFechaDesde, txtFechaHasta;
    public JButton btnFiltrar;
    public JPanel panelItemsHistorial;

    // Constructor vacío para compatibilidad (pasa null)
    public FrmHistorial() {
        this(null);
    }

    // Constructor Principal con Navegación
    public FrmHistorial(INavegacion navegador) {
        this.navegador = navegador;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#121212"));
        this.setBorder(new EmptyBorder(20, 40, 20, 40));

        // --- Header (Título y Filtros) ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        JLabel lblTitulo = new JLabel("Mi Historial de Actividad");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));

        // Filtros (Pills)
        JPanel pFiltrosCat = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pFiltrosCat.setOpaque(false);
        pFiltrosCat.add(crearBadge("Todas", true));
        pFiltrosCat.add(crearBadge("Compras", false));
        pFiltrosCat.add(crearBadge("Transferencias", false));
        pFiltrosCat.add(crearBadge("Ventas", false));

        // Filtros Fecha
        JPanel pFechas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pFechas.setOpaque(false);

        txtFechaDesde = new JTextField("05/01/2024");
        estilarInput(txtFechaDesde);

        txtFechaHasta = new JTextField("05/31/2024");
        estilarInput(txtFechaHasta);

        btnFiltrar = new JButton("Aplicar Filtros");
        btnFiltrar.setBackground(Color.decode("#a855f7"));
        btnFiltrar.setForeground(Color.WHITE);

        pFechas.add(new JLabel("Desde "));
        pFechas.getComponent(0).setForeground(Color.GRAY);
        pFechas.add(txtFechaDesde);
        pFechas.add(Box.createHorizontalStrut(10));
        pFechas.add(new JLabel("Hasta "));
        pFechas.getComponent(3).setForeground(Color.GRAY);
        pFechas.add(txtFechaHasta);
        pFechas.add(Box.createHorizontalStrut(10));
        pFechas.add(btnFiltrar);

        topPanel.add(lblTitulo);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(pFiltrosCat);
        topPanel.add(pFechas);
        topPanel.add(Box.createVerticalStrut(20));

        this.add(topPanel, BorderLayout.NORTH);

        // --- Centro (Timeline Items) ---
        panelItemsHistorial = new JPanel();
        panelItemsHistorial.setLayout(new BoxLayout(panelItemsHistorial, BoxLayout.Y_AXIS));
        panelItemsHistorial.setOpaque(false);

        // ITEMS DE EJEMPLO (Con lógica de navegación añadida en el método)
        panelItemsHistorial.add(crearItemHistorial("Compra Realizada", "28 Mayo, 09:30 AM", "The Weeknd", "$3,500.00 MXN", Color.GREEN));
        panelItemsHistorial.add(Box.createVerticalStrut(15));
        panelItemsHistorial.add(crearItemHistorial("Transferencia Enviada", "25 Mayo, 02:15 PM", "Dua Lipa", "1 Boleto", Color.BLUE));
        panelItemsHistorial.add(Box.createVerticalStrut(15));
        panelItemsHistorial.add(crearItemHistorial("Venta Completada", "10 Mayo, 08:45 PM", "Bad Bunny", "$2,800.00 MXN", Color.RED));

        JScrollPane scroll = new JScrollPane(panelItemsHistorial);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);

        this.add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearItemHistorial(String tipo, String fecha, String evento, String detalle, Color colorIcono) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));
        panel.setBackground(Color.decode("#18181b"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#333333")),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Icono (Simulado)
        JPanel iconPanel = new JPanel();
        iconPanel.setPreferredSize(new Dimension(40, 40));
        iconPanel.setBackground(colorIcono.darker().darker());
        panel.add(iconPanel, BorderLayout.WEST);

        // Centro
        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setOpaque(false);

        JLabel lblTop = new JLabel(tipo + " - " + fecha);
        lblTop.setForeground(Color.WHITE);
        lblTop.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel lblBottom = new JLabel(evento + " | " + detalle);
        lblBottom.setForeground(Color.GRAY);

        center.add(lblTop);
        center.add(lblBottom);
        panel.add(center, BorderLayout.CENTER);

        // --- Derecha (Botón de Ver Detalles) ---
        // Cambiamos el JLabel por un JButton estilizado como link
        JButton btnVer = new JButton("Ver Detalles");
        btnVer.setForeground(Color.decode("#a855f7"));
        btnVer.setBackground(Color.decode("#18181b")); // Mismo fondo que el panel
        btnVer.setBorderPainted(false);
        btnVer.setFocusPainted(false);
        btnVer.setContentAreaFilled(false); // Para que parezca solo texto
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Acción de Navegación
        btnVer.addActionListener(e -> {
            if(navegador != null) {
                // Creamos un DTO "al vuelo" con la info disponible para ver algo en el detalle
                BoletoDTO dtoSimulado = new BoletoDTO();
                dtoSimulado.setNombreConcierto(evento); // Pasamos el nombre del evento
                dtoSimulado.setFila("Historial");       // Dato dummy
                dtoSimulado.setAsiento("-");
                
                // Navegamos
                navegador.cambiarVista("DETALLE_BOLETO", dtoSimulado);
            }
        });
        
        panel.add(btnVer, BorderLayout.EAST);

        return panel;
    }

    private JButton crearBadge(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setBackground(active ? Color.decode("#a855f7") : Color.decode("#27272a"));
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        return btn;
    }

    private void estilarInput(JTextField txt) {
        txt.setBackground(Color.decode("#27272a"));
        txt.setForeground(Color.WHITE);
        txt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txt.setPreferredSize(new Dimension(100, 30));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Historial");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Pasamos null para probar solo la vista
            frame.setContentPane(new FrmHistorial(null));
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}