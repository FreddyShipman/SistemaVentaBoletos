package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ReporteDTO;
import com.ventaboletos.negocio.facade.IGestorReportes;
import com.ventaboletos.negocio.facade.util.FabricaNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PnlReportes extends JPanel {

    private final FrmMenuPrincipal frame;
    private JTable tblReportes;
    private JLabel lblTotalGeneral;

    public PnlReportes(FrmMenuPrincipal frame) {
        this.frame = frame;
        initComponents();
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            // 1. Llamamos a la Fábrica para obtener el Gestor de Reportes
                IGestorReportes gestor = FabricaNegocio.crearGestorReportes();
            
            // 2. Obtenemos la lista de DTOs procesados
            List<ReporteDTO> lista = gestor.generarReporteGeneral();
            
            // 3. Llenamos la tabla
            DefaultTableModel model = (DefaultTableModel) tblReportes.getModel();
            model.setRowCount(0);
            
            double granTotal = 0.0;
            
            for (ReporteDTO r : lista) {
                Object[] fila = {
                    r.getNombreConcierto(),
                    r.getBoletosVendidos(),
                    String.format("$%.2f", r.getIngresoTotal()) // Formato de moneda
                };
                model.addRow(fila);
                granTotal += r.getIngresoTotal();
            }
            
            // 4. Actualizamos el total general en pantalla
            lblTotalGeneral.setText("Ingresos Totales del Sistema: " + String.format("$%.2f", granTotal));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte: " + e.getMessage());
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- ENCABEZADO ---
        JLabel lblTitulo = new JLabel("Reporte de Ventas por Concierto");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // --- TABLA ---
        tblReportes = new JTable();
        tblReportes.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] { "Concierto", "Boletos Vendidos", "Ingresos Generados" }
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tblReportes.setRowHeight(30);
        tblReportes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JScrollPane scroll = new JScrollPane(tblReportes);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(scroll, BorderLayout.CENTER);

        // --- PIE DE PÁGINA (Total y Botón) ---
        JPanel pnlFooter = new JPanel(new BorderLayout());
        pnlFooter.setBackground(Color.WHITE);
        pnlFooter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTotalGeneral = new JLabel("Ingresos Totales: $0.00");
        lblTotalGeneral.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalGeneral.setForeground(new Color(0, 102, 204));
        pnlFooter.add(lblTotalGeneral, BorderLayout.WEST);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVolver.setPreferredSize(new java.awt.Dimension(150, 40));
        btnVolver.addActionListener(e -> frame.mostrarMenuInicial());
        pnlFooter.add(btnVolver, BorderLayout.EAST);

        add(pnlFooter, BorderLayout.SOUTH);
    }
}