package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.dto.ZonaDTO;
import com.ventaboletos.negocio.facade.interfaces.IGestorCompra;
import com.ventaboletos.negocio.facade.util.FabricaNegocio;
import com.ventaboletos.presentacion.utilerias.Mensaje;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PantallaCompraBoleto extends JFrame {

    private JComboBox<String> comboConciertos;
    private JComboBox<String> comboZonas;
    private JLabel lblPrecio, lblCupo;
    private JTextField txtCantidad, txtEmail;
    private JButton btnComprar;

    private IGestorCompra gestor;
    private List<ConciertoDTO> listaConciertosCargados;

    private final Color COLOR_PRIMARIO = new Color(46, 204, 113);
    private final Color COLOR_FONDO = new Color(236, 240, 241);
    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);

    public PantallaCompraBoleto() {
        setTitle("Taquilla Virtual - Comprar Boleto");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Mensaje notificador = new Mensaje();
        this.gestor = FabricaNegocio.crearGestorCompra(notificador);

        initUI();
        cargarConciertos();
    }

    private void initUI() {
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        panelHeader.setBorder(new EmptyBorder(15, 15, 15, 15));
        JLabel lblTitulo = new JLabel("Venta de Boletos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(FUENTE_TITULO);
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0; 
        panelForm.add(new JLabel("Seleccione Concierto:"), gbc);
        
        comboConciertos = new JComboBox<>();
        comboConciertos.addActionListener(e -> actualizarZonas());
        gbc.gridx = 1; 
        panelForm.add(comboConciertos, gbc);

        gbc.gridx = 0; gbc.gridy = 1; 
        panelForm.add(new JLabel("Seleccione Zona:"), gbc);
        
        comboZonas = new JComboBox<>();
        comboZonas.addActionListener(e -> actualizarInfoZona());
        gbc.gridx = 1; 
        panelForm.add(comboZonas, gbc);

        JPanel panelInfo = new JPanel(new GridLayout(1, 2, 10, 0));
        panelInfo.setBackground(Color.WHITE);
        lblPrecio = new JLabel("Precio: $0.00");
        lblPrecio.setForeground(new Color(192, 57, 43));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblCupo = new JLabel("Cupo: 0");
        lblCupo.setForeground(Color.GRAY);
        
        panelInfo.add(lblPrecio);
        panelInfo.add(lblCupo);
        
        gbc.gridx = 1; gbc.gridy = 2;
        panelForm.add(panelInfo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; 
        panelForm.add(new JLabel("Cantidad de Boletos:"), gbc);
        
        txtCantidad = new JTextField("1");
        gbc.gridx = 1; 
        panelForm.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 4; 
        panelForm.add(new JLabel("Su Email (Para envío):"), gbc);
        
        txtEmail = new JTextField();
        gbc.gridx = 1; 
        panelForm.add(txtEmail, gbc);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(COLOR_FONDO);
        panelFooter.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        btnComprar = new JButton("CONFIRMAR COMPRA");
        btnComprar.setBackground(COLOR_PRIMARIO);
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnComprar.setFocusPainted(false);
        btnComprar.addActionListener(this::accionComprar);
        
        panelFooter.add(btnComprar);
        add(panelFooter, BorderLayout.SOUTH);
    }

    private void cargarConciertos() {
        listaConciertosCargados = gestor.obtenerConciertosDisponibles();
        comboConciertos.removeAllItems();
        
        for (ConciertoDTO c : listaConciertosCargados) {
            comboConciertos.addItem(c.getNombre());
        }
    }

    private void actualizarZonas() {
        comboZonas.removeAllItems();
        int index = comboConciertos.getSelectedIndex();
        
        if (index >= 0 && index < listaConciertosCargados.size()) {
            ConciertoDTO seleccionado = listaConciertosCargados.get(index);
            for (ZonaDTO z : seleccionado.getZonas()) {
                comboZonas.addItem(z.getNombre());
            }
        }
    }

    private void actualizarInfoZona() {
        int indexConcierto = comboConciertos.getSelectedIndex();
        int indexZona = comboZonas.getSelectedIndex();

        if (indexConcierto >= 0 && indexZona >= 0) {
            ConciertoDTO concierto = listaConciertosCargados.get(indexConcierto);
            ZonaDTO zona = concierto.getZonas().get(indexZona);
            
            lblPrecio.setText("Precio: $" + zona.getPrecio());
            lblCupo.setText("Disponibles: " + zona.getCapacidad());
        }
    }

    private void accionComprar(ActionEvent e) {
        try {
            String concierto = (String) comboConciertos.getSelectedItem();
            String zona = (String) comboZonas.getSelectedItem();
            String email = txtEmail.getText();
            
            if (concierto == null || zona == null) {
                JOptionPane.showMessageDialog(this, "Seleccione concierto y zona");
                return;
            }
            
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El email es obligatorio");
                return;
            }

            int cantidad = Integer.parseInt(txtCantidad.getText());
            if (cantidad <= 0) throw new NumberFormatException();

            boolean exito = gestor.procesarCompra(concierto, zona, cantidad, email);

            if (exito) {
                cargarConciertos(); 
                txtCantidad.setText("1");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
        }
    }
}