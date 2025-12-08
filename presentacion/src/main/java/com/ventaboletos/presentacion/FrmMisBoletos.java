/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.negocio.facade.GestionarBoletosPropiosFacade;
import com.ventaboletos.negocio.facade.IGestionarBoletosPropios;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Pantalla que lista los boletos del usuario.
 * Integra navegación y datos simulados (Mock).
 */
public class FrmMisBoletos extends JPanel {

    private INavegacion navegador;
    private IGestionarBoletosPropios boletosFacade; // Facade para obtener datos

    // Componentes públicos
    public JButton btnTabUpcoming, btnTabPast, btnTabCancelled;
    public JPanel panelListaBoletos; 

    // Constructor vacío para pruebas o compatibilidad
    public FrmMisBoletos() {
        this(null);
    }

    // Constructor principal con Navegación
    public FrmMisBoletos(INavegacion navegador) {
        this.navegador = navegador;
        // Inicializamos la Facade (Simulada)
        this.boletosFacade = new GestionarBoletosPropiosFacade();
        
        initComponents();
        
        // Cargamos los datos reales al iniciar la pantalla
        cargarBoletosUsuario();
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

        // Panel de Tabs
        JPanel panelTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTabs.setOpaque(false);
        panelTabs.setBorder(new EmptyBorder(20, 0, 0, 0));

        btnTabUpcoming = crearBotonTab("En camino", true);
        btnTabPast = crearBotonTab("Vencido", false);
        btnTabCancelled = crearBotonTab("Cancelado", false);
        
        // Listener simple para recargar (opcional, por si quieres refrescar)
        btnTabUpcoming.addActionListener(e -> cargarBoletosUsuario());

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

        JScrollPane scroll = new JScrollPane(panelListaBoletos);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    /**
     * MÉTODO CLAVE: Conecta con la capa de negocio Mock
     */
    private void cargarBoletosUsuario() {
        // Limpiamos lo que hubiera antes
        panelListaBoletos.removeAll();
        
        // Simulamos obtener el ID del usuario actual (ej. "cliente")
        String idUsuarioActual = "cliente"; 
        
        // 1. Llamamos a la Facade para obtener la lista
        List<BoletoDTO> misBoletos = boletosFacade.obtenerBoletosPorUsuario(idUsuarioActual);
        
        if (misBoletos.isEmpty()) {
            JLabel lblVacio = new JLabel("No tienes boletos registrados.");
            lblVacio.setForeground(Color.GRAY);
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelListaBoletos.add(lblVacio);
        }

        // Formateador de fechas para que se vea bonito
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM, yyyy");

        // 2. Iteramos y creamos las tarjetas dinámicamente
        for (BoletoDTO b : misBoletos) {
            String fechaStr = (b.getFechaCompra() != null) ? sdf.format(b.getFechaCompra()) : "Fecha pendiente";
            
            // Creamos la tarjeta visual pasando el objeto DTO completo
            panelListaBoletos.add(crearTarjetaBoleto(b, fechaStr));
            panelListaBoletos.add(Box.createVerticalStrut(20)); // Espacio entre tarjetas
        }

        // Refrescamos la UI para que muestre los nuevos elementos
        panelListaBoletos.revalidate();
        panelListaBoletos.repaint();
    }

    private JPanel crearTarjetaBoleto(BoletoDTO boleto, String fechaTexto) {
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setBackground(Color.decode("#161618"));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#333333"), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        // Placeholder Imagen
        JPanel panelImg = new JPanel();
        panelImg.setPreferredSize(new Dimension(140, 140));
        panelImg.setBackground(Color.DARK_GRAY);
        card.add(panelImg, BorderLayout.WEST);

        // Info Central
        JPanel panelInfo = new JPanel(new GridLayout(4, 1));
        panelInfo.setOpaque(false);

        // Lógica de color según estado
        String estado = boleto.getEstado() != null ? boleto.getEstado() : "DESCONOCIDO";
        boolean esValido = "VENDIDO".equalsIgnoreCase(estado);
        Color bgEstado = esValido ? Color.decode("#064e3b") : Color.decode("#3f3f46");
        Color fgEstado = esValido ? Color.decode("#34d399") : Color.LIGHT_GRAY;
        
        JLabel lblEstado = new JLabel(" " + estado + " ");
        lblEstado.setOpaque(true);
        lblEstado.setBackground(bgEstado);
        lblEstado.setForeground(fgEstado);
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 10));
        
        JPanel pEstado = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pEstado.setOpaque(false);
        pEstado.add(lblEstado);

        JLabel lblArtista = new JLabel(boleto.getNombreConcierto());
        lblArtista.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblArtista.setForeground(Color.WHITE);

        JLabel lblFecha = new JLabel(fechaTexto);
        lblFecha.setForeground(Color.GRAY);
        
        // Muestra fila/asiento si existen, o ubicación genérica
        String ubicacion = "Fila: " + boleto.getFila() + " - Asiento: " + boleto.getAsiento();
        JLabel lblLugar = new JLabel(ubicacion);
        lblLugar.setForeground(Color.GRAY);

        panelInfo.add(pEstado);
        panelInfo.add(lblArtista);
        panelInfo.add(lblFecha);
        panelInfo.add(lblLugar);
        card.add(panelInfo, BorderLayout.CENTER);

        // Botones (Derecha)
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBtn.setOpaque(false);

        JButton btnVerQR = new JButton("Ver Boleto QR");
        btnVerQR.setBackground(Color.decode("#a855f7"));
        btnVerQR.setForeground(Color.WHITE);

        JButton btnDetalles = new JButton("Ver Detalles");
        btnDetalles.setBackground(Color.decode("#27272a"));
        btnDetalles.setForeground(Color.WHITE);

        // --- LÓGICA DE NAVEGACIÓN ---
        btnDetalles.addActionListener(e -> {
            if(navegador != null) {
                // Navegamos y pasamos el objeto 'boleto' completo
                navegador.cambiarVista("DETALLE_BOLETO", boleto);
            } else {
                System.out.println("Navegador no configurado (Prueba unitaria)");
            }
        });

        panelBtn.add(btnVerQR);
        panelBtn.add(btnDetalles);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mis Boletos (Prueba)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Pasamos null al navegador para probar solo la vista
            frame.setContentPane(new FrmMisBoletos(null));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}