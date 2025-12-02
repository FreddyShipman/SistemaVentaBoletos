/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author jonyco
 */
public class FrmDetalleSolicitud extends JPanel {

    public JButton btnAprobar;
    public JButton btnRechazar;
    public JTable tablaBoletos;

    public FrmDetalleSolicitud() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(20, 20)); // Espaciado
        this.setBackground(Color.decode("#121212"));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- Cabecera ---
        JPanel panelHeader = new JPanel(new GridLayout(2, 1));
        panelHeader.setOpaque(false);

        JLabel lblBreadcrumb = new JLabel("Solicitudes / Solicitud #12345");
        lblBreadcrumb.setForeground(Color.GRAY);

        JLabel lblTitulo = new JLabel("Solicitud #12345");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));

        panelHeader.add(lblBreadcrumb);
        panelHeader.add(lblTitulo);

        this.add(panelHeader, BorderLayout.NORTH);

        // --- Contenido Central ---
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        // Sección 1: Info Usuario
        panelCentro.add(crearSubtitulo("Informacion del Usuario"));
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(crearFilaInfo("Nombre", "Carlos Mendoza"));
        panelCentro.add(crearFilaInfo("Correo Electrónico", "carlos.mendoza@email.com"));
        panelCentro.add(crearFilaInfo("Teléfono", "555-123-4567"));

        panelCentro.add(Box.createVerticalStrut(30));

        // Sección 2: Tabla Boletos
        panelCentro.add(crearSubtitulo("Boletos Solicitados"));
        panelCentro.add(Box.createVerticalStrut(10));

        String[] columnas = {"Concierto", "Fecha", "Cantidad", "Tipo de Boleto"};
        Object[][] datos = {
            {"Festival de Música Electrónica", "2024-07-20", "2", "General"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaBoletos = new JTable(model);
        estilarTabla(tablaBoletos);

        JScrollPane scrollPane = new JScrollPane(tablaBoletos);
        scrollPane.getViewport().setBackground(Color.decode("#1f1f23"));
        scrollPane.setPreferredSize(new Dimension(800, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#333333")));

        panelCentro.add(scrollPane);

        this.add(panelCentro, BorderLayout.CENTER);

        // --- Botones de Acción (Sur) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setOpaque(false);

        btnAprobar = new JButton("Aprobar");
        estilarBoton(btnAprobar, Color.decode("#8b5cf6")); // Morado

        btnRechazar = new JButton("Rechazar");
        estilarBoton(btnRechazar, Color.decode("#3f3f46")); // Gris oscuro

        panelBotones.add(btnAprobar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnRechazar);

        this.add(panelBotones, BorderLayout.SOUTH);
    }

    // Auxiliares
    private JLabel crearSubtitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel crearFilaInfo(String label, String valor) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel l = new JLabel(label);
        l.setForeground(Color.GRAY);
        l.setPreferredSize(new Dimension(200, 20));

        JLabel v = new JLabel(valor);
        v.setForeground(Color.LIGHT_GRAY);

        p.add(l, BorderLayout.WEST);
        p.add(v, BorderLayout.CENTER);
        p.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH);

        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        return p;
    }

    private void estilarTabla(JTable table) {
        table.setBackground(Color.decode("#1f1f23"));
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.decode("#333333"));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(Color.decode("#18181b"));
        table.getTableHeader().setForeground(Color.LIGHT_GRAY);
    }

    private void estilarBoton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100, 35));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Detalle Solicitud");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmDetalleSolicitud());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
