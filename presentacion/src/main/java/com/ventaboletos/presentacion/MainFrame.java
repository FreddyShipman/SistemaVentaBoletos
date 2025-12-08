/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.dto.SolicitudDTO;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */

/**
 * Contenedor principal de la aplicación. Implementa INavegacion para gestionar
 * el cambio entre pantallas.
 */
public class MainFrame extends JFrame implements INavegacion {

    private JPanel panelContenedor;
    private CardLayout cardLayout;

    // --- Referencias a TODAS las vistas ---
    private FrmMisBoletos vistaMisBoletos;
    private FrmHistorial vistaHistorial;
    private FrmDetalleBoleto vistaDetalleBoleto;

    // Módulo de Solicitudes
    private FrmListadoSolicitudes vistaSolicitudes;
    private FrmCrearSolicitud vistaCrearSolicitud;
    private FrmDetalleSolicitud vistaDetalleSolicitud;

    // Módulo de Registro/Auth
    private FrmRegistro vistaRegistro;

    public MainFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("VentaBoletos App - Sistema Integral");
        setSize(1280, 800); // Un poco más ancho para acomodar todo bien
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout());

        // Icono de la ventana (opcional, si tienes uno)
        // setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
    }

    private void inicializarComponentes() {
        // 1. Configurar el CardLayout (El gestor de las "tarjetas" o pantallas)
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        panelContenedor.setBackground(Color.decode("#121212")); // Fondo oscuro base

        // 2. Instanciar todas las vistas pasando 'this' (el navegador)
        vistaMisBoletos = new FrmMisBoletos(this);
        vistaHistorial = new FrmHistorial(this);
        vistaDetalleBoleto = new FrmDetalleBoleto(this);

        vistaSolicitudes = new FrmListadoSolicitudes(this);
        vistaCrearSolicitud = new FrmCrearSolicitud(this);
        vistaDetalleSolicitud = new FrmDetalleSolicitud(this);

        vistaRegistro = new FrmRegistro(this);

        // 3. Agregar las vistas al contenedor con una CLAVE única (String)
        panelContenedor.add(vistaMisBoletos, "MIS_BOLETOS");
        panelContenedor.add(vistaHistorial, "HISTORIAL");
        panelContenedor.add(vistaDetalleBoleto, "DETALLE_BOLETO");

        panelContenedor.add(vistaSolicitudes, "SOLICITUDES");
        panelContenedor.add(vistaCrearSolicitud, "CREAR_SOLICITUD");
        panelContenedor.add(vistaDetalleSolicitud, "DETALLE_SOLICITUD");

        panelContenedor.add(vistaRegistro, "REGISTRO");

        // 4. Crear el Menú Lateral (Sidebar)
        JPanel menuLateral = crearMenuLateral();

        // 5. Agregar todo al Frame Principal
        this.add(menuLateral, BorderLayout.WEST);
        this.add(panelContenedor, BorderLayout.CENTER);

        // 6. Mostrar la vista inicial (Por ejemplo, Registro para simular flujo real)
        // O puedes empezar en "MIS_BOLETOS" si prefieres saltarte el login/registro.
        cardLayout.show(panelContenedor, "REGISTRO");
    }

    private JPanel crearMenuLateral() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Color.decode("#09090b")); // Casi negro
        menu.setPreferredSize(new Dimension(220, 0));
        menu.setBorder(new EmptyBorder(30, 20, 30, 20));

        // Título del Menú (Logo simulado)
        JLabel lblLogo = new JLabel("TICKET APP");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        menu.add(lblLogo);
        menu.add(Box.createVerticalStrut(40));

        // Botones de Navegación
        menu.add(crearBotonMenu("Mis Boletos", "MIS_BOLETOS"));
        menu.add(Box.createVerticalStrut(15));
        menu.add(crearBotonMenu("Historial", "HISTORIAL"));
        menu.add(Box.createVerticalStrut(15));
        menu.add(crearBotonMenu("Atención / Ayuda", "SOLICITUDES"));
        menu.add(Box.createVerticalStrut(15));

        // Separador visual
        menu.add(Box.createVerticalGlue()); // Empuja lo siguiente hacia abajo

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(180, 1));
        sep.setForeground(Color.DARK_GRAY);
        menu.add(sep);
        menu.add(Box.createVerticalStrut(15));

        // Botón de Salir / Registro (Demo)
        JButton btnRegistro = crearBotonMenu("Cerrar Sesión", "REGISTRO");
        btnRegistro.setBackground(Color.decode("#27272a")); // Un poco más claro
        menu.add(btnRegistro);

        return menu;
    }

    private JButton crearBotonMenu(String texto, String vistaDestino) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setBackground(Color.decode("#18181b"));
        btn.setForeground(Color.LIGHT_GRAY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect simple (Opcional)
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.decode("#27272a"));
                btn.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.decode("#18181b"));
                btn.setForeground(Color.LIGHT_GRAY);
            }
        });

        // Acción de Navegación
        btn.addActionListener(e -> cambiarVista(vistaDestino));

        return btn;
    }

    // --- Implementación de INavegacion ---
    @Override
    public void cambiarVista(String nombreVista) {
        // Simplemente cambiamos la tarjeta visible
        cardLayout.show(panelContenedor, nombreVista);
    }

    @Override
    public void cambiarVista(String nombreVista, Object datos) {
        // Lógica para pasar datos antes de cambiar la vista

        // Caso 1: Vamos al detalle de un boleto
        if ("DETALLE_BOLETO".equals(nombreVista) && datos instanceof BoletoDTO) {
            vistaDetalleBoleto.mostrarDatos((BoletoDTO) datos);
        } // Caso 2: Vamos al detalle de una solicitud
        else if ("DETALLE_SOLICITUD".equals(nombreVista) && datos instanceof SolicitudDTO) {
            vistaDetalleSolicitud.mostrarDatos((SolicitudDTO) datos);
        }

        // Caso 3: Podrías pasar datos al registro si quisieras editar perfil, etc.
        // else if ("PERFIL".equals(nombreVista) ...
        // Finalmente cambiamos la vista
        cardLayout.show(panelContenedor, nombreVista);
    }

    /**
     * Punto de entrada principal de la aplicación.
     */
    public static void main(String[] args) {
        // Configuración para que se vea mejor en algunos sistemas (Anti-aliasing)
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
