package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.dto.ZonaDTO;
import com.ventaboletos.negocio.facade.GestorConciertos;
import com.ventaboletos.presentacion.utilerias.Mensaje;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PantallaNuevoConcierto extends JFrame {

    private JTextField txtNombre, txtArtista, txtFecha, txtLugar;
    private JTextField txtZonaNombre, txtZonaPrecio, txtZonaCapacidad;
    private JTable tablaZonas;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar;

    private GestorConciertos gestor;
    private List<ZonaDTO> listaZonasTemporal;
    
    private final Color COLOR_PRIMARIO = new Color(41, 128, 185);
    private final Color COLOR_FONDO = new Color(236, 240, 241);  
    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    private final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);

    public PantallaNuevoConcierto() {
        setTitle("Sistema de Gestión de Conciertos");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        Mensaje notificador = new Mensaje();
        this.gestor = new GestorConciertos(notificador);
        this.listaZonasTemporal = new ArrayList<>();

        initUI();
    }

    private void initUI() {
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel lblTitulo = new JLabel("Registrar Nuevo Concierto");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(FUENTE_TITULO);
        panelHeader.add(lblTitulo);
        
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new BoxLayout(panelCuerpo, BoxLayout.Y_AXIS));
        panelCuerpo.setBackground(COLOR_FONDO);
        panelCuerpo.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelCuerpo.add(crearPanelDatosGenerales());
        panelCuerpo.add(Box.createVerticalStrut(20));
        
        panelCuerpo.add(crearPanelZonas());

        add(panelCuerpo, BorderLayout.CENTER);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(COLOR_FONDO);
        panelFooter.setBorder(new EmptyBorder(0, 20, 20, 20));
        
        btnGuardar = new JButton("GUARDAR CONCIERTO COMPLETO");
        estilizarBoton(btnGuardar);
        btnGuardar.addActionListener(this::accionGuardar);
        
        panelFooter.add(btnGuardar);
        add(panelFooter, BorderLayout.SOUTH);
    }

    private JPanel crearPanelDatosGenerales() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(15, 15, 15, 15)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblSec = new JLabel("Datos Generales");
        lblSec.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSec.setForeground(COLOR_PRIMARIO);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblSec, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        gbc.weightx = 0.3; gbc.gridx = 0; panel.add(new JLabel("Nombre del Evento:"), gbc);
        gbc.weightx = 0.7; gbc.gridx = 1; txtNombre = new JTextField(15); panel.add(txtNombre, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(new JLabel("Artista Principal:"), gbc);
        gbc.gridx = 1; txtArtista = new JTextField(15); panel.add(txtArtista, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(new JLabel("Fecha (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1; txtFecha = new JTextField(15); panel.add(txtFecha, gbc);
        
        gbc.gridy++;
        gbc.gridx = 0; panel.add(new JLabel("Lugar / Recinto:"), gbc);
        gbc.gridx = 1; txtLugar = new JTextField(15); panel.add(txtLugar, gbc);

        return panel;
    }

    private JPanel crearPanelZonas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblSec = new JLabel("Configuración de Zonas");
        lblSec.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSec.setForeground(COLOR_PRIMARIO);
        panel.add(lblSec, BorderLayout.NORTH);

        JPanel formZonas = new JPanel(new GridLayout(4, 2, 5, 5));
        formZonas.setBackground(Color.WHITE);
        
        formZonas.add(new JLabel("Nombre Zona:"));
        txtZonaNombre = new JTextField(); formZonas.add(txtZonaNombre);
        
        formZonas.add(new JLabel("Precio ($):"));
        txtZonaPrecio = new JTextField(); formZonas.add(txtZonaPrecio);
        
        formZonas.add(new JLabel("Capacidad:"));
        txtZonaCapacidad = new JTextField(); formZonas.add(txtZonaCapacidad);
        
        JButton btnAdd = new JButton("+ Agregar a la lista");
        btnAdd.addActionListener(e -> agregarZonaTemporal());
        formZonas.add(new JLabel(""));
        formZonas.add(btnAdd);

        panel.add(formZonas, BorderLayout.WEST);

        String[] columnas = {"Zona", "Precio", "Capacidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaZonas = new JTable(modeloTabla);
        tablaZonas.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tablaZonas);
        scroll.setPreferredSize(new Dimension(300, 100));
        
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void estilizarBoton(JButton btn) {
        btn.setBackground(COLOR_PRIMARIO);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void agregarZonaTemporal() {
        try {
            String nombre = txtZonaNombre.getText();
            if(nombre.isEmpty()) throw new Exception("Nombre vacío");
            double precio = Double.parseDouble(txtZonaPrecio.getText());
            int capacidad = Integer.parseInt(txtZonaCapacidad.getText());

            ZonaDTO zona = new ZonaDTO(nombre, precio, capacidad);
            listaZonasTemporal.add(zona);

            modeloTabla.addRow(new Object[]{nombre, "$" + precio, capacidad});
            
            txtZonaNombre.setText("");
            txtZonaPrecio.setText("");
            txtZonaCapacidad.setText("");
            txtZonaNombre.requestFocus();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y Capacidad deben ser numéricos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de zona válido.");
        }
    }

    private void accionGuardar(ActionEvent e) {
        try {
            String nombre = txtNombre.getText();
            String artista = txtArtista.getText();
            String lugar = txtLugar.getText();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = null;
            try {
                fecha = sdf.parse(txtFecha.getText());
            } catch (Exception ex) { fecha = null; }

            ConciertoDTO dto = new ConciertoDTO(nombre, artista, fecha, lugar, listaZonasTemporal);

            gestor.registrarConcierto(dto);

            limpiarFormulario();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void limpiarFormulario() {
        try {
            if (txtNombre != null) txtNombre.setText("");
            if (txtArtista != null) txtArtista.setText("");
            if (txtLugar != null) txtLugar.setText("");
            if (txtFecha != null) txtFecha.setText("");
            
            if (modeloTabla != null) {
                modeloTabla.setRowCount(0);
            }
            
            if (listaZonasTemporal != null) {
                listaZonasTemporal.clear();
            }
        } catch (Exception e) {
            System.out.println("Error menor al limpiar formulario: " + e.getMessage());
        }
    }
}