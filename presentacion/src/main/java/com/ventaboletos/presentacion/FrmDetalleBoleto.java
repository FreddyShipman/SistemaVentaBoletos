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
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Pantalla de detalle que recibe un BoletoDTO y muestra su información.
 */
public class FrmDetalleBoleto extends JPanel {

    private INavegacion navegador;
    
    // Referencias a los labels para poder actualizarlos dinámicamente
    public JLabel lblArtista, lblFecha, lblLugar, lblAsiento, lblTitular;

    // Constructor vacío
    public FrmDetalleBoleto() {
        this(null);
    }

    // Constructor principal
    public FrmDetalleBoleto(INavegacion navegador) {
        this.navegador = navegador;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout()); 
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
        
        // Botón "Volver" (Opcional, pero útil para navegación)
        JButton btnVolver = new JButton("← Volver");
        btnVolver.setForeground(Color.GRAY);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVolver.addActionListener(e -> {
            if(navegador != null) navegador.cambiarVista("MIS_BOLETOS");
        });
        leftPanel.add(btnVolver);
        leftPanel.add(Box.createVerticalStrut(10));

        JLabel lblTag = new JLabel("DETALLE DEL BOLETO");
        lblTag.setForeground(Color.decode("#a855f7")); // Morado
        lblTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTag.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblArtista = new JLabel("Cargando..."); // Placeholder
        lblArtista.setForeground(Color.WHITE);
        lblArtista.setFont(new Font("SansSerif", Font.BOLD, 42));
        lblArtista.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(lblTag);
        leftPanel.add(lblArtista);
        leftPanel.add(Box.createVerticalStrut(10));
        
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(1000, 1));
        leftPanel.add(sep); 
        leftPanel.add(Box.createVerticalStrut(30));

        // Grid de detalles (2 columnas)
        JPanel gridDetalles = new JPanel(new GridLayout(2, 2, 20, 30));
        gridDetalles.setOpaque(false);
        gridDetalles.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Inicializamos los labels que vamos a actualizar
        lblFecha = crearLabelValor("---");
        lblLugar = crearLabelValor("Foro Sol (Simulado)");
        lblAsiento = crearLabelValor("---");
        lblTitular = crearLabelValor("---");

        // Añadimos al grid usando helpers
        gridDetalles.add(crearItemDetalle("Fecha de Compra", lblFecha));
        gridDetalles.add(crearItemDetalle("Ubicación", lblLugar));
        gridDetalles.add(crearItemDetalle("Asiento", lblAsiento));
        gridDetalles.add(crearItemDetalle("Titular (ID)", lblTitular));

        leftPanel.add(gridDetalles);

        // --- DERECHA: Código QR ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.decode("#18181b"));
        
        JPanel qrCard = new JPanel(new GridBagLayout());
        qrCard.setBackground(Color.decode("#2f3e3e")); 
        qrCard.setPreferredSize(new Dimension(300, 350));
        qrCard.setBorder(new EmptyBorder(20,20,20,20));

        JPanel qrWhiteBox = new JPanel();
        qrWhiteBox.setBackground(Color.WHITE);
        qrWhiteBox.setPreferredSize(new Dimension(180, 250));
        qrWhiteBox.add(new JLabel("QR CODE")); 

        qrCard.add(qrWhiteBox);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        rightPanel.add(qrCard, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        JLabel lblWarn = new JLabel("<html><center>Presenta este código para entrar.<br>No lo compartas.</center></html>");
        lblWarn.setForeground(Color.LIGHT_GRAY);
        rightPanel.add(lblWarn, gbc);

        mainCard.add(leftPanel, BorderLayout.CENTER);
        mainCard.add(rightPanel, BorderLayout.EAST);

        this.add(mainCard);
    }

    // Método helper para crear el layout de cada item (Titulo + Valor)
    private JPanel crearItemDetalle(String titulo, JLabel labelValor) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        
        JLabel t = new JLabel(titulo);
        t.setForeground(Color.GRAY);
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        p.add(t);
        p.add(labelValor);
        return p;
    }
    
    // Método helper para crear el label del valor con estilo
    private JLabel crearLabelValor(String textoInicial) {
        JLabel v = new JLabel(textoInicial);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return v;
    }

    /**
     * MÉTODO PÚBLICO: Llamado por MainFrame al navegar a esta pantalla.
     * Actualiza la UI con los datos del boleto recibido.
     */
    public void mostrarDatos(BoletoDTO boleto) {
        if (boleto == null) return;

        lblArtista.setText(boleto.getNombreConcierto());
        
        // Formatear fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha = (boleto.getFechaCompra() != null) ? sdf.format(boleto.getFechaCompra()) : "Sin fecha";
        lblFecha.setText(fecha);
        
        lblAsiento.setText("Fila: " + boleto.getFila() + " | Asiento: " + boleto.getAsiento());
        
        // Como el DTO tiene idUsuario pero no nombre completo, mostramos el ID o un placeholder
        lblTitular.setText(boleto.getIdUsuario() != null ? boleto.getIdUsuario() : "Cliente");
        
        // Refrescamos visualmente
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Detalle Boleto (Prueba)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FrmDetalleBoleto(null));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}