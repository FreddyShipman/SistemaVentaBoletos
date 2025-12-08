/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
import com.ventaboletos.dto.UsuarioDTO;
import com.ventaboletos.negocio.facade.AutenticacionFacade;
import com.ventaboletos.negocio.facade.IAutenticacion;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRegistro extends JPanel {

    private INavegacion navegador;
    private IAutenticacion authFacade;

    public JTextField txtNombre;
    public JTextField txtEmail;
    public JPasswordField txtPassword;
    public JPasswordField txtConfirmPassword;
    public JLabel lblErrorEmail;
    public JButton btnCrearCuenta;
    public JLabel lblLoginLink;

    // Constructor para pruebas
    public FrmRegistro() {
        this(null);
    }

    // Constructor Conectado
    public FrmRegistro(INavegacion navegador) {
        this.navegador = navegador;
        this.authFacade = new AutenticacionFacade(); // Instanciamos la Facade
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#0a0a0a"));

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

        // Error Label
        lblErrorEmail = new JLabel("Este correo electrónico ya está registrado. ¿Quieres iniciar sesión?");
        lblErrorEmail.setForeground(Color.decode("#ef4444"));
        lblErrorEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblErrorEmail.setVisible(false);
        lblErrorEmail.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Botón ---
        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.setBackground(Color.decode("#d946ef"));
        btnCrearCuenta.setForeground(Color.WHITE);
        btnCrearCuenta.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCrearCuenta.setFocusPainted(false);
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnCrearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ACCION DEL BOTON
        btnCrearCuenta.addActionListener(e -> accionRegistrar());

        // --- Link Login ---
        lblLoginLink = new JLabel("<html>¿Ya tienes cuenta? <font color='#d946ef'>Inicia sesión</font></html>");
        lblLoginLink.setForeground(Color.GRAY);
        lblLoginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblLoginLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ACCION DEL LINK (Navegar al Login)
        lblLoginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (navegador != null) {
                    // Asumimos que existirá una vista llamada "LOGIN"
                    // navegador.cambiarVista("LOGIN"); 
                    JOptionPane.showMessageDialog(null, "Navegando al Login... (Implementar vista Login)");
                }
            }
        });

        // --- Armado del Formulario ---
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblSub);
        card.add(Box.createVerticalStrut(40));

        agregarCampo(card, "Nombre Completo", txtNombre);
        card.add(Box.createVerticalStrut(15));

        agregarCampo(card, "Correo Electrónico", txtEmail);
        card.add(lblErrorEmail);
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

    private void accionRegistrar() {
        // 1. Obtener datos
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String pass = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());

        // 2. Validaciones básicas
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Crear DTO
        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setNombreCompleto(nombre);
        nuevoUsuario.setNombreUsuario(email); // Usamos el email como username
        nuevoUsuario.setPassword(pass);
        nuevoUsuario.setRol("Cliente"); // Por defecto

        // 4. Llamar a Facade
        boolean exito = authFacade.registrarUsuario(nuevoUsuario);

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Cuenta creada con éxito! Ahora puedes iniciar sesión.");
            // Si tuvieras la pantalla de Login lista:
            // navegador.cambiarVista("LOGIN");

            // Por ahora, redirigimos a Mis Boletos como ejemplo de "Auto Login"
            if (navegador != null) {
                navegador.cambiarVista("MIS_BOLETOS");
            }

        } else {
            // Mostrar error visual (el label rojo debajo del email)
            lblErrorEmail.setVisible(true);
            this.revalidate();
        }
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
        campo.setBackground(Color.decode("#1f1f23"));
        campo.setForeground(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#3f3f46")),
                new EmptyBorder(10, 10, 10, 10)
        ));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        if (campo instanceof JTextField) {
            ((JTextField) campo).setCaretColor(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Registro Prueba");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Pasamos null para prueba visual
            frame.setContentPane(new FrmRegistro(null));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
