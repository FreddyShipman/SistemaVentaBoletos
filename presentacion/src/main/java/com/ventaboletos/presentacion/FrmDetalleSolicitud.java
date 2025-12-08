/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

import com.ventaboletos.dto.SolicitudDTO;
import com.ventaboletos.negocio.facade.GestionarSolicitudesDeBoletosFacade;
import com.ventaboletos.negocio.facade.IGestionarSolicitudesDeBoletos;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
/**
 * Pantalla de detalle que recibe una SolicitudDTO y muestra su información.
 */
public class FrmDetalleSolicitud extends JPanel {

    private INavegacion navegador;
    private IGestionarSolicitudesDeBoletos solicitudesFacade;
    private SolicitudDTO solicitudActual;

    // Etiquetas dinámicas
    private JLabel lblTituloId;
    private JLabel lblTipo;
    private JLabel lblEstado;
    private JLabel lblFecha;
    private JLabel lblIdBoleto;
    private JTextArea txtDescripcion;
    private JButton btnCancelarSolicitud;

    // Constructor vacío
    public FrmDetalleSolicitud() {
        this(null);
    }

    // Constructor conectado
    public FrmDetalleSolicitud(INavegacion navegador) {
        this.navegador = navegador;
        this.solicitudesFacade = new GestionarSolicitudesDeBoletosFacade();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(Color.decode("#121212"));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- Cabecera con Botón Volver ---
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setOpaque(false);

        JButton btnVolver = new JButton("← Volver al Listado");
        btnVolver.setForeground(Color.GRAY);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
        btnVolver.addActionListener(e -> {
            if (navegador != null) {
                navegador.cambiarVista("SOLICITUDES");
            }
        });

        lblTituloId = new JLabel("Solicitud #---");
        lblTituloId.setForeground(Color.WHITE);
        lblTituloId.setFont(new Font("SansSerif", Font.BOLD, 28));

        JPanel panelTitulos = new JPanel(new GridLayout(2, 1));
        panelTitulos.setOpaque(false);
        panelTitulos.add(btnVolver);
        panelTitulos.add(lblTituloId);

        panelHeader.add(panelTitulos, BorderLayout.WEST);
        this.add(panelHeader, BorderLayout.NORTH);

        // --- Contenido Central (Formulario de Lectura) ---
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        // Grid de Datos
        JPanel gridDatos = new JPanel(new GridLayout(0, 2, 20, 20));
        gridDatos.setOpaque(false);
        gridDatos.setMaximumSize(new Dimension(800, 200));
        gridDatos.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblTipo = crearLabelValor("---");
        lblEstado = crearLabelValor("---");
        lblFecha = crearLabelValor("---");
        lblIdBoleto = crearLabelValor("---");

        gridDatos.add(crearItemDetalle("Tipo de Solicitud", lblTipo));
        gridDatos.add(crearItemDetalle("Estado Actual", lblEstado));
        gridDatos.add(crearItemDetalle("Fecha Creación", lblFecha));
        gridDatos.add(crearItemDetalle("Boleto Relacionado", lblIdBoleto));

        panelCentro.add(gridDatos);
        panelCentro.add(Box.createVerticalStrut(30));

        // Descripción
        JLabel lblDesc = new JLabel("Descripción / Motivo:");
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelCentro.add(lblDesc);
        panelCentro.add(Box.createVerticalStrut(10));

        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBackground(Color.decode("#1f1f23"));
        txtDescripcion.setForeground(Color.WHITE);
        txtDescripcion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setPreferredSize(new Dimension(800, 100));
        scrollDesc.setBorder(BorderFactory.createLineBorder(Color.decode("#333333")));
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelCentro.add(scrollDesc);

        this.add(panelCentro, BorderLayout.CENTER);

        // --- Botones de Acción (Sur) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);

        btnCancelarSolicitud = new JButton("Cancelar esta Solicitud");
        estilarBoton(btnCancelarSolicitud, Color.decode("#ef4444")); // Rojo

        btnCancelarSolicitud.addActionListener(e -> accionCancelar());

        panelBotones.add(btnCancelarSolicitud);

        this.add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * MÉTODO CLAVE: Recibe el objeto desde el listado
     */
    public void mostrarDatos(SolicitudDTO solicitud) {
        this.solicitudActual = solicitud;

        if (solicitud == null) {
            return;
        }

        lblTituloId.setText("Solicitud #" + solicitud.getIdSolicitud());
        lblTipo.setText(solicitud.getTipoSolicitud());

        // Colorear estado
        String est = solicitud.getEstado();
        lblEstado.setText(est);
        if ("APROBADO".equals(est)) {
            lblEstado.setForeground(Color.GREEN);
        } else if ("RECHAZADO".equals(est)) {
            lblEstado.setForeground(Color.RED);
        } else {
            lblEstado.setForeground(Color.ORANGE);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        lblFecha.setText(solicitud.getFechaSolicitud() != null ? sdf.format(solicitud.getFechaSolicitud()) : "-");

        lblIdBoleto.setText(solicitud.getIdBoleto() != null ? solicitud.getIdBoleto() : "N/A");
        txtDescripcion.setText(solicitud.getDescripcion() != null ? solicitud.getDescripcion() : "Sin descripción.");

        // Si ya no está pendiente, deshabilitar botón cancelar
        btnCancelarSolicitud.setEnabled("PENDIENTE".equalsIgnoreCase(est) || "EN_REVISION".equalsIgnoreCase(est));
    }

    private void accionCancelar() {
        if (solicitudActual == null) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas cancelar esta solicitud?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = solicitudesFacade.cancelarSolicitud(solicitudActual.getIdSolicitud());
            if (exito) {
                JOptionPane.showMessageDialog(this, "Solicitud cancelada.");
                if (navegador != null) {
                    navegador.cambiarVista("SOLICITUDES");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cancelar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Auxiliares Visuales
    private JPanel crearItemDetalle(String titulo, JLabel labelValor) {
        JPanel p = new JPanel(new GridLayout(2, 1));
        p.setOpaque(false);
        JLabel t = new JLabel(titulo);
        t.setForeground(Color.GRAY);
        p.add(t);
        p.add(labelValor);
        return p;
    }

    private JLabel crearLabelValor(String texto) {
        JLabel v = new JLabel(texto);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return v;
    }

    private void estilarBoton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(200, 40));
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
