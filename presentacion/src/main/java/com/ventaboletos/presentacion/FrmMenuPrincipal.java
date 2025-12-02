package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class FrmMenuPrincipal extends JFrame {

    private JPanel pnlContenido; 
    private JPanel pnlMenuInicial; 

    public FrmMenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null); 
    }

    // --- NAVEGACIÓN ---
    public void mostrarPanel(JPanel p) {
        pnlContenido.removeAll();
        pnlContenido.add(p, BorderLayout.CENTER);
        pnlContenido.revalidate();
        pnlContenido.repaint();
    }

    public void mostrarMenuInicial() {
        mostrarPanel(pnlMenuInicial);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Boletos - Inicio");
        setMinimumSize(new java.awt.Dimension(1000, 700));

        pnlContenido = new JPanel(new BorderLayout());
        
        // --- MENÚ INICIAL ---
        pnlMenuInicial = new JPanel(new GridBagLayout());
        pnlMenuInicial.setBackground(new Color(245, 245, 245)); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0; 
        
        JLabel lblTitulo = new JLabel("Sistema de Boletos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(30, 30, 30));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        pnlMenuInicial.add(lblTitulo, gbc);

        // Sección Cliente
        JButton btnVenta = new JButton("Comprar Boletos");
        estilarBotonPrincipal(btnVenta);
        btnVenta.addActionListener(e -> mostrarPanel(new PnlCatalogo(this)));
        gbc.gridy = 1;
        pnlMenuInicial.add(btnVenta, gbc);

//        // Sección Admin
//        JButton btnGestion = new JButton("Gestionar Conciertos");
//        estilarBotonSecundario(btnGestion);
//        btnGestion.addActionListener(e -> mostrarPanel(new PnlGestionConciertos(this)));
//        gbc.gridy = 2;
//        pnlMenuInicial.add(btnGestion, gbc);

        // Sección Reportes
        JButton btnReportes = new JButton("Ver Reportes");
        estilarBotonSecundario(btnReportes);
        btnReportes.addActionListener(e -> mostrarPanel(new PnlReportes(this)));
        gbc.gridy = 3;
        pnlMenuInicial.add(btnReportes, gbc);

        pnlContenido.add(pnlMenuInicial, BorderLayout.CENTER);
        getContentPane().add(pnlContenido);
        pack(); 
    }

    private void estilarBotonPrincipal(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new java.awt.Dimension(300, 60));
        btn.setFocusPainted(false);
    }

    private void estilarBotonSecundario(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(51, 51, 51));
        btn.setPreferredSize(new java.awt.Dimension(300, 60));
        btn.setFocusPainted(false);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new FrmMenuPrincipal().setVisible(true));
    }
}