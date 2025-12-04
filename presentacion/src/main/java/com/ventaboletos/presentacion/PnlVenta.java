//package com.ventaboletos.presentacion;
//
///**
// *
// * @author alfre
// */
//
//import com.ventaboletos.negocio.facade.IVentaBoletos;
//import com.ventaboletos.negocio.facade.util.FabricaNegocio;
//import javax.swing.JOptionPane;
//import com.ventaboletos.dto.BoletoDTO;
//import com.ventaboletos.dto.ConciertoDTO;
//import com.ventaboletos.dto.VentaDTO;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PnlVenta extends JPanel {
//
//    private FrmMenuPrincipal frame;
//    private ConciertoDTO concierto;
//    
//    // Estado del carrito local
//    private List<BoletoDTO> carritoBoletos;
//    private double totalVenta = 0.0;
//
//    // Componentes UI
//    private JTextField txtAsistente;
//    private JComboBox<String> cmbAsiento;
//    private DefaultTableModel modeloCarrito;
//    private JLabel lblTotal;
//
//    public PnlVenta(FrmMenuPrincipal frame, ConciertoDTO concierto) {
//        this.frame = frame;
//        this.concierto = concierto;
//        this.carritoBoletos = new ArrayList<>();
//        
//        initComponents();
//    }
//
//    private void initComponents() {
//        setLayout(new BorderLayout(20, 20));
//        setBackground(Color.WHITE);
//        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // --- 1. ENCABEZADO ---
//        JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
//        pnlHeader.setBackground(Color.WHITE);
//        
//        JLabel lblTitulo = new JLabel("Proceso de Compra");
//        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
//        
//        JLabel lblSubtitulo = new JLabel("Concierto: " + concierto.getNombre() + " - Artista: " + concierto.getArtista());
//        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        lblSubtitulo.setForeground(Color.GRAY);
//        
//        pnlHeader.add(lblTitulo);
//        pnlHeader.add(lblSubtitulo);
//        add(pnlHeader, BorderLayout.NORTH);
//
//        // --- 2. CENTRO (Formulario + Carrito) ---
//        JPanel pnlCentro = new JPanel(new GridBagLayout());
//        pnlCentro.setBackground(Color.WHITE);
//        GridBagConstraints gbc = new GridBagConstraints();
//        
//        // -- Sección Formulario (Izquierda/Arriba) --
//        JPanel pnlForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
//        pnlForm.setBackground(new Color(248, 249, 250)); // Gris muy claro
//        pnlForm.setBorder(BorderFactory.createTitledBorder("Nuevo Boleto"));
//        
//        txtAsistente = new JTextField(15);
//        cmbAsiento = new JComboBox<>(new String[]{"General - $500", "VIP - $1500"});
//        JButton btnAgregar = new JButton("Agregar Ticket");
//        estilizarBoton(btnAgregar, new Color(23, 162, 184)); // Cyan oscuro
//
//        pnlForm.add(new JLabel("Asistente:"));
//        pnlForm.add(txtAsistente);
//        pnlForm.add(new JLabel("Tipo:"));
//        pnlForm.add(cmbAsiento);
//        pnlForm.add(btnAgregar);
//
//        gbc.gridx = 0; gbc.gridy = 0;
//        gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
//        pnlCentro.add(pnlForm, gbc);
//
//        // -- Sección Tabla Carrito (Abajo) --
//        String[] colCarrito = {"Asistente", "Asiento", "Precio"};
//        modeloCarrito = new DefaultTableModel(colCarrito, 0);
//        JTable tablaCarrito = new JTable(modeloCarrito);
//        tablaCarrito.setFillsViewportHeight(true);
//        
//        JScrollPane scrollCarrito = new JScrollPane(tablaCarrito);
//        scrollCarrito.setPreferredSize(new Dimension(600, 200));
//        
//        gbc.gridy = 1;
//        gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
//        gbc.insets = new Insets(10, 0, 0, 0);
//        pnlCentro.add(scrollCarrito, gbc);
//
//        add(pnlCentro, BorderLayout.CENTER);
//
//        // --- 3. FOOTER (Total y Acciones) ---
//        JPanel pnlFooter = new JPanel(new BorderLayout());
//        pnlFooter.setBackground(Color.WHITE);
//        pnlFooter.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
//
//        lblTotal = new JLabel("Total a Pagar: $0.00");
//        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        lblTotal.setForeground(new Color(220, 53, 69)); // Rojo
//        pnlFooter.add(lblTotal, BorderLayout.WEST);
//
//        JPanel pnlBotonesAccion = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        pnlBotonesAccion.setBackground(Color.WHITE);
//        
//        JButton btnVolver = new JButton("Volver al Catálogo");
//        estilizarBoton(btnVolver, new Color(108, 117, 125));
//        
//        JButton btnFinalizar = new JButton("Finalizar Compra");
//        estilizarBoton(btnFinalizar, new Color(40, 167, 69));
//
//        pnlBotonesAccion.add(btnVolver);
//        pnlBotonesAccion.add(btnFinalizar);
//        pnlFooter.add(pnlBotonesAccion, BorderLayout.EAST);
//
//        add(pnlFooter, BorderLayout.SOUTH);
//
//        // --- LÓGICA DE EVENTOS ---
//
//        // 1. Agregar Ticket al Carrito
//        btnAgregar.addActionListener(e -> agregarAlCarrito());
//
//        // 2. Volver al Catálogo (NO al menú)
//        btnVolver.addActionListener(e -> {
//            frame.mostrarPanel(new PnlCatalogo(frame));
//        });
//
//        // 3. Finalizar Compra (Llamada al Negocio)
//        btnFinalizar.addActionListener(e -> finalizarCompra());
//    }
//
//    private void agregarAlCarrito() {
//        String nombre = txtAsistente.getText().trim();
//        if (nombre.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Ingrese el nombre del asistente.");
//            return;
//        }
//
//        String seleccion = (String) cmbAsiento.getSelectedItem();
//        double precio = seleccion.contains("VIP") ? 1500.00 : 500.00;
//        String tipoAsiento = seleccion.split(" - ")[0];
//
//        // Crear DTO (Asumiendo constructor o setters disponibles)
//        BoletoDTO boleto = new BoletoDTO(); 
//        boleto.setNombreAsistente(nombre);
//        boleto.setAsiento(tipoAsiento);
//        boleto.setPrecio(precio);
//
//        // Agregar a lista y tabla visual
//        carritoBoletos.add(boleto);
//        modeloCarrito.addRow(new Object[]{nombre, tipoAsiento, "$" + precio});
//
//        // Actualizar total
//        totalVenta += precio;
//        lblTotal.setText("Total a Pagar: $" + totalVenta);
//        
//        txtAsistente.setText("");
//        txtAsistente.requestFocus();
//    }
//
//    private void finalizarCompra() {
//        if (carritoBoletos.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
//            return;
//        }
//
//        try {
//            // Construir VentaDTO
//            VentaDTO venta = new VentaDTO();
//            venta.setIdConcierto(concierto.getId());
//            venta.setNombreCliente("Cliente Ventanilla"); // Podría pedirse en otro campo
//            venta.setListaBoletos(carritoBoletos);
//            venta.setTotal(totalVenta);
//
//            // Llamar a Negocio
//            IVentaBoletos servicioVenta = FabricaNegocio.crearVentaBoletos();
//            servicioVenta.registrarVenta(venta);
//
//            // Éxito
//            JOptionPane.showMessageDialog(this, "¡Venta realizada con éxito!");
//            // Regresar al catálogo
//            frame.mostrarPanel(new PnlCatalogo(frame));
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error al procesar venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void estilizarBoton(JButton btn, Color bg) {
//        btn.setBackground(bg);
//        btn.setForeground(Color.WHITE);
//        btn.setFocusPainted(false);
//        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
//    }
//}