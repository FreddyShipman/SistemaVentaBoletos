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

public class FrmRegistro extends JPanel {

    public JTextField txtNombre;
    public JTextField txtEmail;
    public JPasswordField txtPassword;
    public JPasswordField txtConfirmPassword;
    public JLabel lblErrorEmail; // Para mostrar/ocultar el error
    public JButton btnCrearCuenta;
    public JLabel lblLoginLink;

    public FrmRegistro() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout()); // Centrado en pantalla
        this.setBackground(Color.decode("#0a0a0a")); // Fondo casi negro

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(450, 600));

        // --- Header ---
        JLabel lblTitulo = new JLabel("Crea tu cuenta para registrarte");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Regístrate para el Concierto y no te pierdas nada.");
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Campos ---
        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();

        // Error Label (Inicialmente invisible o visible según lógica)
        lblErrorEmail = new JLabel("Este correo electrónico ya está registrado. ¿Quieres iniciar sesión?");
        lblErrorEmail.setForeground(Color.decode("#ef4444")); // Rojo error
        lblErrorEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblErrorEmail.setVisible(false); // Oculto por defecto

        // --- Botón ---
        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.setBackground(Color.decode("#d946ef")); // Magenta Fuerte
        btnCrearCuenta.setForeground(Color.WHITE);
        btnCrearCuenta.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCrearCuenta.setFocusPainted(false);
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnCrearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- Link Login ---
        lblLoginLink = new JLabel("<html>¿Ya tienes cuenta? <font color='#d946ef'>Inicia sesión</font></html>");
        lblLoginLink.setForeground(Color.GRAY);
        lblLoginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLoginLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Armado del Formulario ---
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblSub);
        card.add(Box.createVerticalStrut(40));

        agregarCampo(card, "Nombre Completo", txtNombre);
        card.add(Box.createVerticalStrut(15));

        agregarCampo(card, "Correo Electrónico", txtEmail);
        card.add(lblErrorEmail); // El error va justo debajo del email
        card.add(Box.createVerticalStrut(15));

        agregarCampo(card, "Crear Contraseña", txtPassword);
        card.add(Box.createVerticalStrut(15));

        agregarCampo(card, "Confirmar Contraseña", txtConfirmPassword);
        card.add(Box.createVerticalStrut(30));

        card.add(btnCrearCuenta);
        card.add(Box.createVerticalStrut(20));
        card.add(lblLoginLink);

        this.add(card);
    }

    private void agregarCampo(JPanel panel, String titulo, JComponent campo) {
        JLabel lbl = new JLabel(titulo);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        estilarInput(campo);

        JPanel pLabel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        pLabel.setOpaque(false);
        pLabel.add(lbl);
        pLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(pLabel);
        panel.add(campo);
    }

    private void estilarInput(JComponent campo) {
        campo.setBackground(Color.decode("#1f1f23")); // Gris oscuro fondo input
        campo.setForeground(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#3f3f46")), // Borde sutil
                new EmptyBorder(10, 10, 10, 10)
        ));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        if (campo instanceof JTextField) {
            ((JTextField) campo).setCaretColor(Color.WHITE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Registro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmRegistro());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
