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
import java.awt.*;

public class FrmBienvenida extends JPanel {

    public JButton btnRegistrarse;
    public JButton btnIniciarSesion;

    public FrmBienvenida() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#0f0f0f")); // Fondo oscuro base

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        // --- Títulos ---
        JLabel lblTitulo1 = new JLabel("Tu Próximo Concierto Te");
        lblTitulo1.setFont(new Font("SansSerif", Font.BOLD, 48));
        lblTitulo1.setForeground(Color.WHITE);
        lblTitulo1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo2 = new JLabel("Espera");
        lblTitulo2.setFont(new Font("SansSerif", Font.BOLD, 48));
        lblTitulo2.setForeground(Color.WHITE);
        lblTitulo2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Descubre, reserva y vive la mejor música en vivo.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSub.setForeground(Color.LIGHT_GRAY);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.setOpaque(false);
        
        btnRegistrarse = new JButton("Registrarse");
        estilarBoton(btnRegistrarse, Color.decode("#db2777"), Color.WHITE); // Magenta

        btnIniciarSesion = new JButton("Iniciar Sesión");
        estilarBoton(btnIniciarSesion, new Color(255, 255, 255, 30), Color.WHITE); // Translucido

        panelBotones.add(btnRegistrarse);
        panelBotones.add(btnIniciarSesion);

        // --- Armado ---
        contenido.add(lblTitulo1);
        contenido.add(lblTitulo2);
        contenido.add(Box.createVerticalStrut(20));
        contenido.add(lblSub);
        contenido.add(Box.createVerticalStrut(40));
        contenido.add(panelBotones);

        this.add(contenido);
    }
    
    // Método para simular fondo de imagen (Opcional)
    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Descomenta y ajusta la ruta para usar la imagen de fondo de concierto
        // Image img = new ImageIcon(getClass().getResource("/images/fondo_concierto.jpg")).getImage();
        // g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        
        // Simular oscurecimiento sobre la imagen
        // g.setColor(new Color(0, 0, 0, 150));
        // g.fillRect(0, 0, getWidth(), getHeight());
    }
    */

    private void estilarBoton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(160, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bienvenida");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmBienvenida());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
}
