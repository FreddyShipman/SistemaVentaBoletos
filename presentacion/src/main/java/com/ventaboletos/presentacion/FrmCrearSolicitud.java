/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author jonyco
 */
public class FrmCrearSolicitud extends JPanel {

    // Componentes de la UI
    public JComboBox<String> comboConcierto;
    public JComboBox<String> comboTipoBoleto;
    public JTextField txtCantidad;
    public JTextField txtNombreContacto;
    public JTextField txtEmailContacto;
    public JButton btnSubmit;

    public FrmCrearSolicitud() {
        initComponents();
    }

    private void initComponents() {
        // Configuración general del Panel (Tema Oscuro)
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#121212")); // Fondo negro principal

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado vertical
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- Título ---
        JLabel lblTitulo = new JLabel("Crear Solicitud de Boleto");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridy = 0;
        this.add(lblTitulo, gbc);

        // --- Campo: Concierto ---
        gbc.gridy++;
        this.add(crearLabel("Concierto"), gbc);

        gbc.gridy++;
        comboConcierto = new JComboBox<>(new String[]{"Seleccionar Concierto", "Festival Musical 2025", "Concierto de Rock"});
        estilarInput(comboConcierto);
        this.add(comboConcierto, gbc);

        // --- Campo: Tipo de Boleto ---
        gbc.gridy++;
        this.add(crearLabel("Tipo de Boleto"), gbc);

        gbc.gridy++;
        comboTipoBoleto = new JComboBox<>(new String[]{"Selecciona el Tipo de Boleto", "General", "VIP", "Platino"});
        estilarInput(comboTipoBoleto);
        this.add(comboTipoBoleto, gbc);

        // --- Campo: Cantidad ---
        gbc.gridy++;
        this.add(crearLabel("Cantidad"), gbc);

        gbc.gridy++;
        txtCantidad = new JTextField("Ingresa Cantidad");
        estilarInput(txtCantidad);
        this.add(txtCantidad, gbc);

        // --- Campo: Nombre Contacto ---
        gbc.gridy++;
        this.add(crearLabel("Contact Name"), gbc);

        gbc.gridy++;
        txtNombreContacto = new JTextField("Ingresa tu Nombre de Contacto");
        estilarInput(txtNombreContacto);
        this.add(txtNombreContacto, gbc);

        // --- Campo: Email ---
        gbc.gridy++;
        this.add(crearLabel("Correo de Contacto"), gbc);

        gbc.gridy++;
        txtEmailContacto = new JTextField("Ingresa tu Correo de Contacto");
        estilarInput(txtEmailContacto);
        this.add(txtEmailContacto, gbc);

        // --- Botón Submit ---
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 10, 0); // Más espacio antes del botón
        btnSubmit = new JButton("Crear Solicitud");
        btnSubmit.setBackground(Color.decode("#8b5cf6")); // Color morado del diseño
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSubmit.setPreferredSize(new Dimension(200, 40));

        // Alineación del botón a la derecha
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(btnSubmit);

        this.add(btnPanel, gbc);
    }

    // Métodos auxiliares para estilo
    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        return lbl;
    }

    private void estilarInput(JComponent comp) {
        comp.setBackground(Color.decode("#1f1f23")); // Fondo gris oscuro input
        comp.setForeground(Color.WHITE);
        comp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comp.setPreferredSize(new Dimension(400, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Crear Solicitud");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmCrearSolicitud());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
