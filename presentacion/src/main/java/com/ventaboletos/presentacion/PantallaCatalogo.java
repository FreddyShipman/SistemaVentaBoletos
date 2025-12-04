package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.negocio.facade.GestorConciertos;
import com.ventaboletos.presentacion.utilerias.Mensaje;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PantallaCatalogo extends JFrame {

    private JTable tablaConciertos;
    private DefaultTableModel modeloTabla;
    private GestorConciertos gestor;

    private final Color COLOR_PRIMARIO = new Color(230, 126, 34);
    private final Color COLOR_FONDO = new Color(253, 242, 233);

    public PantallaCatalogo() {
        setTitle("Cartelera de Conciertos Disponibles");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.gestor = new GestorConciertos(new Mensaje());

        initUI();
        cargarDatos();
    }

    private void initUI() {
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel lblTitulo = new JLabel("Próximos Eventos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        String[] columnas = {"Artista", "Evento", "Fecha", "Lugar"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaConciertos = new JTable(modeloTabla);
        tablaConciertos.setRowHeight(25);
        tablaConciertos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scroll = new JScrollPane(tablaConciertos);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scroll.getViewport().setBackground(Color.WHITE);
        
        add(scroll, BorderLayout.CENTER);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(COLOR_FONDO);
        JButton btnRefrescar = new JButton("Actualizar Lista");
        btnRefrescar.addActionListener(e -> cargarDatos());
        panelFooter.add(btnRefrescar);
        
        add(panelFooter, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);

        List<ConciertoDTO> lista = gestor.obtenerConciertosDisponibles();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (ConciertoDTO c : lista) {
            modeloTabla.addRow(new Object[]{
                c.getArtista(),
                c.getNombre(),
                sdf.format(c.getFecha()),
                c.getLugar()
            });
        }
    }
}