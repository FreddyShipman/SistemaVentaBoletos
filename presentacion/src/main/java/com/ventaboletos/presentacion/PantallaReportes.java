package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ReporteDTO;
import com.ventaboletos.negocio.facade.GestorReportes;
import com.ventaboletos.presentacion.utilerias.Mensaje;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PantallaReportes extends JFrame {

    private JTextField txtFechaInicio, txtFechaFin;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private PanelGrafica panelGrafica;
    
    private GestorReportes gestor;

    private final Color COLOR_PRIMARIO = new Color(142, 68, 173);
    private final Color COLOR_FONDO = new Color(236, 240, 241);

    public PantallaReportes() {
        setTitle("Reportes de Ventas y Ocupación");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.gestor = new GestorReportes(new Mensaje());

        initUI();
    }

    private void initUI() {
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel lblTitulo = new JLabel("Tablero de Resultados Financieros");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBackground(Color.WHITE);
        panelFiltros.add(new JLabel("Fecha Inicio (dd/MM/yyyy):"));
        txtFechaInicio = new JTextField(10);
        panelFiltros.add(txtFechaInicio);
        
        panelFiltros.add(new JLabel("Fecha Fin (dd/MM/yyyy):"));
        txtFechaFin = new JTextField(10);
        panelFiltros.add(txtFechaFin);

        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBackground(COLOR_PRIMARIO);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.addActionListener(e -> accionGenerar());
        panelFiltros.add(btnGenerar);

        add(panelFiltros, BorderLayout.SOUTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.setBackground(COLOR_FONDO);

        String[] columnas = {"Concierto", "Ingresos ($)", "Boletos", "Ocupación %"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaResultados = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Detalle Numérico"));
        panelCentral.add(scrollTabla);

        panelGrafica = new PanelGrafica();
        panelGrafica.setBorder(BorderFactory.createTitledBorder("Gráfica de Ingresos"));
        panelGrafica.setBackground(Color.WHITE);
        panelCentral.add(panelGrafica);

        add(panelCentral, BorderLayout.CENTER);
    }

    private void accionGenerar() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = null;
            Date fin = null;

            if (!txtFechaInicio.getText().isEmpty()) {
                inicio = sdf.parse(txtFechaInicio.getText());
            }
            if (!txtFechaFin.getText().isEmpty()) {
                fin = sdf.parse(txtFechaFin.getText());
            }

            List<ReporteDTO> datos = gestor.generarReporte(inicio, fin);
            
            modeloTabla.setRowCount(0);
            for (ReporteDTO d : datos) {
                modeloTabla.addRow(new Object[]{
                    d.getNombreConcierto(),
                    "$" + d.getTotalIngresos(),
                    d.getBoletosVendidos(),
                    d.getPorcentajeOcupacion() + "%"
                });
            }

            panelGrafica.setDatos(datos);
            panelGrafica.repaint();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto (use dd/MM/yyyy) o error interno.");
            ex.printStackTrace();
        }
    }

    class PanelGrafica extends JPanel {
        private List<ReporteDTO> datos;

        public void setDatos(List<ReporteDTO> datos) {
            this.datos = datos;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (datos == null || datos.isEmpty()) {
                g.drawString("Genere un reporte para ver la gráfica", 50, 50);
                return;
            }

            int anchoBarra = 50;
            int espacio = 30;
            int x = 50;
            int altoMaximo = getHeight() - 50;
            
            double maxIngreso = 0;
            for (ReporteDTO d : datos) maxIngreso = Math.max(maxIngreso, d.getTotalIngresos());

            Graphics2D g2 = (Graphics2D) g;
            
            for (ReporteDTO d : datos) {
                int alturaBarra = (int) ((d.getTotalIngresos() / maxIngreso) * (altoMaximo - 20));
                
                g2.setColor(new Color(52, 152, 219)); // Azul
                g2.fillRect(x, altoMaximo - alturaBarra, anchoBarra, alturaBarra);
                
                g2.setColor(Color.BLACK);
                g2.drawRect(x, altoMaximo - alturaBarra, anchoBarra, alturaBarra);

                g2.drawString("$" + (int)d.getTotalIngresos(), x, altoMaximo - alturaBarra - 5);
                
                String nombre = d.getNombreConcierto();
                if (nombre.length() > 8) nombre = nombre.substring(0, 8) + "..";
                g2.drawString(nombre, x, altoMaximo + 15);

                x += (anchoBarra + espacio);
            }
        }
    }
}