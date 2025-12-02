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
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmDetalleBoleto extends JPanel {

    // Componentes públicos
    public JLabel lblArtista, lblFecha, lblLugar, lblSeccion, lblTitular;
    public JPanel panelQR;

    public FrmDetalleBoleto() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout()); // Para centrar el contenido general
        this.setBackground(Color.decode("#121212"));
        
        // Contenedor principal estilo tarjeta grande
        JPanel mainCard = new JPanel(new BorderLayout(0, 0));
        mainCard.setBackground(Color.decode("#18181b"));
        mainCard.setPreferredSize(new Dimension(1000, 500));
        mainCard.setBorder(BorderFactory.createLineBorder(Color.decode("#333333")));

        // --- IZQUIERDA: Información ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.decode("#18181b"));
        leftPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        leftPanel.setPreferredSize(new Dimension(600, 500));

        JLabel lblTag = new JLabel("CONCIERTO");
        lblTag.setForeground(Color.decode("#a855f7")); // Morado
        lblTag.setFont(new Font("SansSerif", Font.BOLD, 12));

        lblArtista = new JLabel("Arctic Monkeys");
        lblArtista.setForeground(Color.WHITE);
        lblArtista.setFont(new Font("SansSerif", Font.BOLD, 42));

        leftPanel.add(lblTag);
        leftPanel.add(lblArtista);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(new JSeparator()); // Línea divisoria
        leftPanel.add(Box.createVerticalStrut(30));

        // Grid de detalles (2 columnas)
        JPanel gridDetalles = new JPanel(new GridLayout(2, 2, 20, 30));
        gridDetalles.setOpaque(false);

        gridDetalles.add(crearItemDetalle("Fecha y Hora", "25 de Octubre, 2024 - 20:00h"));
        gridDetalles.add(crearItemDetalle("Ubicación", "Foro Sol, Ciudad de México"));
        gridDetalles.add(crearItemDetalle("Asiento", "Sección: 101, Fila: G, Asiento: 14"));
        gridDetalles.add(crearItemDetalle("Titular del Boleto", "Juan Pérez"));

        leftPanel.add(gridDetalles);

        // --- DERECHA: Código QR ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.decode("#18181b"));
        
        // Tarjeta interna del QR (Fondo verdoso oscuro como la imagen)
        JPanel qrCard = new JPanel(new GridBagLayout());
        qrCard.setBackground(Color.decode("#2f3e3e")); 
        qrCard.setPreferredSize(new Dimension(300, 350));
        qrCard.setBorder(new EmptyBorder(20,20,20,20));

        // El QR Blanco
        JPanel qrWhiteBox = new JPanel();
        qrWhiteBox.setBackground(Color.WHITE);
        qrWhiteBox.setPreferredSize(new Dimension(180, 250)); // Simula el teléfono/ticket
        qrWhiteBox.add(new JLabel(new ImageIcon())); // Aquí iría tu imagen real del QR
        // Simulación visual del QR
        JLabel lblFakeQR = new JLabel("QR CODE");
        qrWhiteBox.add(lblFakeQR);

        qrCard.add(qrWhiteBox);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        rightPanel.add(qrCard, gbc);

        // Texto advertencia abajo
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        JLabel lblWarn = new JLabel("<html><center>Presenta este código para entrar.<br>No lo compartas.</center></html>");
        lblWarn.setForeground(Color.LIGHT_GRAY);
        rightPanel.add(lblWarn, gbc);

        // Agregar paneles al contenedor principal
        mainCard.add(leftPanel, BorderLayout.CENTER);
        mainCard.add(rightPanel, BorderLayout.EAST);

        this.add(mainCard);
    }

    private JPanel crearItemDetalle(String titulo, String valor) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        
        JLabel t = new JLabel(titulo);
        t.setForeground(Color.GRAY);
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        JLabel v = new JLabel(valor);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("SansSerif", Font.PLAIN, 16));
        
        p.add(t);
        p.add(v);
        return p;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Detalle Boleto");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmDetalleBoleto());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
}
