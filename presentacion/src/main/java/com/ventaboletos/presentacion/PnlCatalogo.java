//package com.ventaboletos.presentacion;
//
///**
// *
// * @author alfre
// */
//
//import com.ventaboletos.dto.ConciertoDTO;
//import com.ventaboletos.negocio.facade.IGestionConciertos;
//import com.ventaboletos.negocio.facade.util.FabricaNegocio;
//import javax.swing.JOptionPane;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class PnlCatalogo extends JPanel {
//
//    private FrmMenuPrincipal frame;
//    private JTable tablaConciertos;
//    private DefaultTableModel modeloTabla;
//    private List<ConciertoDTO> listaConciertos; // Mantenemos la lista en memoria para pasar el objeto completo
//
//    public PnlCatalogo(FrmMenuPrincipal frame) {
//        this.frame = frame;
//        initComponents();
//        cargarDatos();
//    }
//
//    private void initComponents() {
//        setLayout(new BorderLayout(20, 20));
//        setBackground(Color.WHITE);
//        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // --- 1. TÍTULO ---
//        JLabel lblTitulo = new JLabel("Catálogo de Conciertos Disponibles");
//        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        lblTitulo.setForeground(new Color(50, 50, 50));
//        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
//        add(lblTitulo, BorderLayout.NORTH);
//
//        // --- 2. TABLA ---
//        String[] columnas = {"ID", "Concierto", "Artista", "Fecha", "Lugar"};
//        modeloTabla = new DefaultTableModel(columnas, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        tablaConciertos = new JTable(modeloTabla);
//        tablaConciertos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        tablaConciertos.setRowHeight(25);
//        tablaConciertos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        
//        // Estilo del encabezado
//        tablaConciertos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
//        tablaConciertos.getTableHeader().setBackground(new Color(230, 230, 230));
//        
//        JScrollPane scrollPane = new JScrollPane(tablaConciertos);
//        scrollPane.getViewport().setBackground(Color.WHITE);
//        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
//        add(scrollPane, BorderLayout.CENTER);
//
//        // --- 3. BOTONES (Footer) ---
//        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
//        pnlBotones.setBackground(Color.WHITE);
//
//        JButton btnVolver = crearBoton("Volver al Menú", new Color(108, 117, 125));
//        JButton btnComprar = crearBoton("Comprar Boletos", new Color(40, 167, 69));
//
//        // Acción Volver
//        btnVolver.addActionListener(e -> frame.mostrarMenuInicial());
//
//        // Acción Comprar
//        btnComprar.addActionListener(e -> {
//            int filaSeleccionada = tablaConciertos.getSelectedRow();
//            if (filaSeleccionada >= 0) {
//                // Obtenemos el DTO correspondiente a la fila seleccionada
//                ConciertoDTO conciertoSeleccionado = listaConciertos.get(filaSeleccionada);
//                
//                // Navegamos al panel de venta pasando el DTO
//                frame.mostrarPanel(new PnlVenta(frame, conciertoSeleccionado));
//            } else {
//                JOptionPane.showMessageDialog(this, "Por favor, seleccione un concierto de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
//            }
//        });
//
//        pnlBotones.add(btnVolver);
//        pnlBotones.add(btnComprar);
//        add(pnlBotones, BorderLayout.SOUTH);
//    }
//
//    private void cargarDatos() {
//        try {
//            // Llamada a la capa de Negocio
//            IGestionConciertos gestion = FabricaNegocio.crearGestionConciertos();
//            listaConciertos = gestion.obtenerTodosLosConciertos();
//
//            modeloTabla.setRowCount(0); // Limpiar tabla
//            for (ConciertoDTO c : listaConciertos) {
//                modeloTabla.addRow(new Object[]{
//                    c.getId(), // Asumiendo que existe getId()
//                    c.getNombre(),
//                    c.getArtista(),
//                    c.getFecha(), // toString automático de LocalDate o String
//                    c.getLugar()
//                });
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error al cargar conciertos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private JButton crearBoton(String texto, Color colorFondo) {
//        JButton btn = new JButton(texto);
//        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        btn.setForeground(Color.WHITE);
//        btn.setBackground(colorFondo);
//        btn.setFocusPainted(false);
//        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        return btn;
//    }
//}