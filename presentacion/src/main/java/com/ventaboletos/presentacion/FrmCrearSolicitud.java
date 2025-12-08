/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

import com.ventaboletos.dto.SolicitudDTO;
import com.ventaboletos.negocio.facade.GestionarSolicitudesDeBoletosFacade;
import com.ventaboletos.negocio.facade.IGestionarSolicitudesDeBoletos;
import com.ventaboletos.negocio.facade.INavegacion;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */

public class FrmCrearSolicitud extends JPanel {

    private INavegacion navegador;
    private IGestionarSolicitudesDeBoletos solicitudesFacade;

    // Componentes de la UI
    public JComboBox<String> comboTipoSolicitud; // Cambiado de concierto a Tipo Solicitud para el ejemplo
    public JTextField txtIdBoleto;
    public JTextArea txtDescripcion;
    public JButton btnSubmit;
    public JButton btnCancelar;

    public FrmCrearSolicitud(INavegacion navegador) {
        this.navegador = navegador;
        this.solicitudesFacade = new GestionarSolicitudesDeBoletosFacade();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#121212")); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // --- Título ---
        JLabel lblTitulo = new JLabel("Nueva Solicitud / Reclamo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(lblTitulo, gbc);

        // --- Campo: Tipo Solicitud ---
        gbc.gridy++;
        this.add(crearLabel("Tipo de Solicitud"), gbc);

        gbc.gridy++;
        comboTipoSolicitud = new JComboBox<>(new String[]{"REEMBOLSO", "CAMBIO_ASIENTO", "ACLARACION", "FACTURACION"});
        estilarInput(comboTipoSolicitud);
        this.add(comboTipoSolicitud, gbc);

        // --- Campo: ID Boleto ---
        gbc.gridy++;
        this.add(crearLabel("ID Boleto Relacionado (Opcional)"), gbc);

        gbc.gridy++;
        txtIdBoleto = new JTextField();
        estilarInput(txtIdBoleto);
        this.add(txtIdBoleto, gbc);

        // --- Campo: Descripción ---
        gbc.gridy++;
        this.add(crearLabel("Descripción del Problema"), gbc);

        gbc.gridy++;
        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setBackground(Color.decode("#1f1f23"));
        txtDescripcion.setForeground(Color.WHITE);
        txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.decode("#333333")));
        
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setPreferredSize(new Dimension(400, 100));
        this.add(scrollDesc, gbc);

        // --- Botones ---
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 10, 0); 
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        
        btnCancelar = new JButton("Cancelar");
        estilarBoton(btnCancelar, Color.decode("#3f3f46"));
        btnCancelar.addActionListener(e -> {
            if(navegador != null) navegador.cambiarVista("SOLICITUDES");
        });

        btnSubmit = new JButton("Enviar Solicitud");
        estilarBoton(btnSubmit, Color.decode("#8b5cf6"));
        
        // --- LOGICA DE GUARDADO ---
        btnSubmit.addActionListener(e -> guardarSolicitud());

        btnPanel.add(btnCancelar);
        btnPanel.add(btnSubmit);

        this.add(btnPanel, gbc);
    }
    
    private void guardarSolicitud() {
        // 1. Crear DTO
        SolicitudDTO nuevaSolicitud = new SolicitudDTO();
        nuevaSolicitud.setIdUsuario("cliente"); // Simulado
        nuevaSolicitud.setTipoSolicitud((String) comboTipoSolicitud.getSelectedItem());
        nuevaSolicitud.setIdBoleto(txtIdBoleto.getText());
        nuevaSolicitud.setDescripcion(txtDescripcion.getText());
        nuevaSolicitud.setEstado("PENDIENTE");
        nuevaSolicitud.setFechaSolicitud(new Date());
        
        // 2. Llamar Facade
        boolean exito = solicitudesFacade.registrarSolicitud(nuevaSolicitud);
        
        // 3. Feedback y Navegación
        if(exito) {
            JOptionPane.showMessageDialog(this, "Solicitud enviada correctamente.");
            if(navegador != null) navegador.cambiarVista("SOLICITUDES");
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar. Revisa la descripción.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Estilos
    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        return lbl;
    }

    private void estilarInput(JComponent comp) {
        comp.setBackground(Color.decode("#1f1f23")); 
        comp.setForeground(Color.WHITE);
        comp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comp.setPreferredSize(new Dimension(400, 40));
    }
    
    private void estilarBoton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(150, 40));
    }
}