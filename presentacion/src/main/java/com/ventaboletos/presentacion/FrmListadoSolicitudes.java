/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

/**
 *
 * @author jonyco
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmListadoSolicitudes extends JPanel {

    public JTextField txtBusqueda;
    public JTable tablaSolicitudes;
    public JButton btnTabAll, btnTabPending, btnTabApproved;

    public FrmListadoSolicitudes() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(0, 20));
        this.setBackground(Color.decode("#121212"));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel Superior (Título y Buscador) ---
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.setOpaque(false);

        JLabel lblTitulo = new JLabel("Solicitudes de Boletos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Administra todas las solcitudes de boletos en camino, filtra por estado y busca");
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Barra de búsqueda
        txtBusqueda = new JTextField("Busca por el ID de solicitud, nombre de cliente...");
        txtBusqueda.setBackground(Color.decode("#27272a"));
        txtBusqueda.setForeground(Color.LIGHT_GRAY);
        txtBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtBusqueda.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tabs (Simulados con botones)
        JPanel panelTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTabs.setOpaque(false);
        panelTabs.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnTabAll = crearBotonTab("Todas las Solicitudes", true);
        btnTabPending = crearBotonTab("Pendiente", false);
        btnTabApproved = crearBotonTab("Aprobada", false);

        panelTabs.add(btnTabAll);
        panelTabs.add(btnTabPending);
        panelTabs.add(btnTabApproved);

        panelTop.add(lblTitulo);
        panelTop.add(Box.createVerticalStrut(5));
        panelTop.add(lblSubtitulo);
        panelTop.add(Box.createVerticalStrut(15));
        panelTop.add(txtBusqueda);
        panelTop.add(Box.createVerticalStrut(15));
        panelTop.add(panelTabs);

        this.add(panelTop, BorderLayout.NORTH);

        // --- Tabla Central ---
        String[] columnas = {"ID Solicitud", "Cliente", "Evento", "Fecha", "Estado", "Acciones"};
        Object[][] datos = {
            {"#12345", "Sophia Clark", "Festival Musical", "2024-07-20", "Pendiente", "Ver"},
            {"#12346", "Ethan Miller", "Concierto de Rock", "2024-07-21", "Aprobada", "Ver"},
            {"#12347", "Olivia Davis", "Demostracion Indie", "2024-07-22", "Rechazada", "Ver"},
            {"#12348", "Liam Wilson", "Rave", "2024-07-23", "Pendiente", "Ver"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaSolicitudes = new JTable(model);
        estilarTabla(tablaSolicitudes);

        JScrollPane scrollPane = new JScrollPane(tablaSolicitudes);
        scrollPane.getViewport().setBackground(Color.decode("#121212"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JButton crearBotonTab(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setForeground(activo ? Color.WHITE : Color.GRAY);
        btn.setFont(new Font("SansSerif", activo ? Font.BOLD : Font.PLAIN, 14));
        // Un pequeño borde inferior si está activo podría simular el tab, 
        // pero por simplicidad solo cambiamos el color de fuente.
        return btn;
    }

    private void estilarTabla(JTable table) {
        table.setBackground(Color.decode("#121212"));
        table.setForeground(Color.LIGHT_GRAY);
        table.setGridColor(Color.decode("#27272a"));
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.getTableHeader().setBackground(Color.decode("#121212"));
        table.getTableHeader().setForeground(Color.GRAY);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Listado de Solicitudes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmListadoSolicitudes());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
