package com.ventaboletos.presentacion;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.negocio.facade.IGestionConciertos;
import com.ventaboletos.negocio.facade.util.FabricaNegocio;
import java.util.Date;
import javax.swing.JOptionPane;

public class PnlGestionConciertos extends javax.swing.JPanel {

    // Constructor
    public PnlGestionConciertos() {
        initComponents();
    }

    // --- LÓGICA DEL BOTÓN GUARDAR ---
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        try {
            // 1. Recolectar datos de la vista
            String nombre = txtNombre.getText();
            String artista = txtArtista.getText();
            String lugar = txtLugar.getText();
            
            // Nota: Usamos un JSpinner para la fecha para no depender de librerías externas.
            // Si tienes JCalendar (JDateChooser), usa: jDateChooser.getDate();
            Date fecha = (Date) spinnerFecha.getValue(); 

            // 2. Crear el DTO
            ConciertoDTO dto = new ConciertoDTO();
            dto.setNombre(nombre);
            dto.setArtista(artista);
            dto.setLugar(lugar);
            dto.setFecha(fecha);

            // 3. Llamar al Negocio usando la FÁBRICA
            // (Esto desacopla la vista de la implementación concreta)
            IGestionConciertos gestor = FabricaNegocio.crearGestionConciertos();
            gestor.registrarNuevoConcierto(dto);

            // 4. Mostrar éxito (Como en tu imagen de Stitch IA)
            JOptionPane.showMessageDialog(this, 
                "¡Concierto guardado exitosamente!", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarFormulario();

        } catch (Exception e) {
            // 5. Manejo de Errores
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
        }
    }                                          

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtArtista.setText("");
        txtLugar.setText("");
        spinnerFecha.setValue(new Date());
    }

    // --- CÓDIGO DE DISEÑO UI (Similar al generado por NetBeans) ---
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblArtista = new javax.swing.JLabel();
        txtArtista = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        spinnerFecha = new javax.swing.JSpinner(); // Usamos Spinner fecha
        lblLugar = new javax.swing.JLabel();
        txtLugar = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton(); // Botón extra para regresar

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(51, 51, 51));
        lblTitulo.setText("Registrar Nuevo Concierto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 30, 0);
        add(lblTitulo, gridBagConstraints);

        // -- Campo Nombre --
        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblNombre.setText("Nombre del Concierto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 20);
        add(lblNombre, gridBagConstraints);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNombre.setPreferredSize(new java.awt.Dimension(250, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        add(txtNombre, gridBagConstraints);

        // -- Campo Artista --
        lblArtista.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblArtista.setText("Artista / Banda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 20);
        add(lblArtista, gridBagConstraints);

        txtArtista.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtArtista.setPreferredSize(new java.awt.Dimension(250, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        add(txtArtista, gridBagConstraints);

        // -- Campo Fecha --
        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblFecha.setText("Fecha del Evento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 20);
        add(lblFecha, gridBagConstraints);

        spinnerFecha.setModel(new javax.swing.SpinnerDateModel());
        spinnerFecha.setEditor(new javax.swing.JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        spinnerFecha.setPreferredSize(new java.awt.Dimension(250, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        add(spinnerFecha, gridBagConstraints);

        // -- Campo Lugar --
        lblLugar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblLugar.setText("Lugar / Recinto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 5, 20);
        add(lblLugar, gridBagConstraints);

        txtLugar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtLugar.setPreferredSize(new java.awt.Dimension(250, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 20);
        add(txtLugar, gridBagConstraints);

        // -- Botón Guardar --
        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Concierto");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setPreferredSize(new java.awt.Dimension(180, 40));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 20, 0);
        add(btnGuardar, gridBagConstraints);
    }

    // Variables declaration
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblArtista;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSpinner spinnerFecha;
    private javax.swing.JTextField txtArtista;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtNombre;
}