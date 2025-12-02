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

public class FrmMisBoletos extends JPanel {

    // Componentes públicos para que agregues lógica
    public JButton btnTabUpcoming, btnTabPast, btnTabCancelled;
    public JPanel panelListaBoletos; // Aquí agregarás los boletos

    public FrmMisBoletos() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode("#121212"));

        // --- Header (Título y Tabs) ---
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBackground(Color.decode("#121212"));
        panelHeader.setBorder(new EmptyBorder(30, 40, 20, 40));

        JLabel lblTitulo = new JLabel("Mis Boletos");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Aquí encontrarás todos los boletos que has comprado.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);

        // Panel de Tabs (Botones redondeados simulados)
        JPanel panelTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTabs.setOpaque(false);
        panelTabs.setBorder(new EmptyBorder(20, 0, 0, 0));

        btnTabUpcoming = crearBotonTab("En camino", true);
        btnTabPast = crearBotonTab("Vencido", false);
        btnTabCancelled = crearBotonTab("Cancelado", false);

        panelTabs.add(btnTabUpcoming);
        panelTabs.add(btnTabPast);
        panelTabs.add(btnTabCancelled);

        panelHeader.add(lblTitulo);
        panelHeader.add(Box.createVerticalStrut(5));
        panelHeader.add(lblSub);
        panelHeader.add(panelTabs);

        this.add(panelHeader, BorderLayout.NORTH);

        // --- Cuerpo (Lista con Scroll) ---
        panelListaBoletos = new JPanel();
        panelListaBoletos.setLayout(new BoxLayout(panelListaBoletos, BoxLayout.Y_AXIS));
        panelListaBoletos.setBackground(Color.decode("#121212"));
        panelListaBoletos.setBorder(new EmptyBorder(0, 40, 40, 40));

        // AGREGANDO DATOS DE EJEMPLO (Simulando la imagen)
        panelListaBoletos.add(crearTarjetaBoleto("Arctic Monkeys Concert", "Sábado, 28 de Octubre, 2023", "Foro Sol, Mexico City", "En camino"));
        panelListaBoletos.add(Box.createVerticalStrut(20));
        panelListaBoletos.add(crearTarjetaBoleto("The Killers World Tour", "Viernes, 15 de Septiembre, 2023", "Palacio de los Deportes", "Completado"));
        panelListaBoletos.add(Box.createVerticalStrut(20));
        panelListaBoletos.add(crearTarjetaBoleto("Lana del Rey", "Martes, 15 de Agosto, 2023", "Foro Sol, Mexico City", "Cancelado"));

        JScrollPane scroll = new JScrollPane(panelListaBoletos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearTarjetaBoleto(String artista, String fecha, String lugar, String estado) {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setBackground(Color.decode("#161618")); // Fondo tarjeta
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#333333"), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        // Placeholder Imagen (Izquierda)
        JPanel panelImg = new JPanel();
        panelImg.setPreferredSize(new Dimension(140, 140));
        panelImg.setBackground(Color.DARK_GRAY);
        card.add(panelImg, BorderLayout.WEST);

        // Info (Centro)
        JPanel panelInfo = new JPanel(new GridLayout(4, 1));
        panelInfo.setOpaque(false);

        JLabel lblEstado = new JLabel(" " + estado + " ");
        lblEstado.setOpaque(true);
        lblEstado.setBackground(estado.equals("En camino") ? Color.decode("#064e3b") : Color.decode("#3f3f46")); // Verde oscuro o gris
        lblEstado.setForeground(estado.equals("En camino") ? Color.decode("#34d399") : Color.LIGHT_GRAY);
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 10));
        // Truco para que el label no ocupe todo el ancho en GridLayout
        JPanel pEstado = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pEstado.setOpaque(false);
        pEstado.add(lblEstado);

        JLabel lblArtista = new JLabel(artista);
        lblArtista.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblArtista.setForeground(Color.WHITE);

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setForeground(Color.GRAY);

        JLabel lblLugar = new JLabel(lugar);
        lblLugar.setForeground(Color.GRAY);

        panelInfo.add(pEstado);
        panelInfo.add(lblArtista);
        panelInfo.add(lblFecha);
        panelInfo.add(lblLugar);
        card.add(panelInfo, BorderLayout.CENTER);

        // Botones (Sur dentro de la tarjeta o Este)
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBtn.setOpaque(false);

        JButton btnVerQR = new JButton("Ver Boleto QR");
        btnVerQR.setBackground(Color.decode("#a855f7")); // Morado
        btnVerQR.setForeground(Color.WHITE);

        JButton btnDetalles = new JButton("Ver Detalles");
        btnDetalles.setBackground(Color.decode("#27272a"));
        btnDetalles.setForeground(Color.WHITE);

        panelBtn.add(btnVerQR);
        panelBtn.add(btnDetalles);

        // Usamos un panel contenedor para alinear los botones abajo a la derecha
        JPanel rightCol = new JPanel(new BorderLayout());
        rightCol.setOpaque(false);
        rightCol.add(panelBtn, BorderLayout.SOUTH);

        card.add(rightCol, BorderLayout.EAST);

        return card;
    }

    private JButton crearBotonTab(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setBackground(activo ? Color.decode("#a855f7") : Color.decode("#27272a"));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mis Boletos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmMisBoletos());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
