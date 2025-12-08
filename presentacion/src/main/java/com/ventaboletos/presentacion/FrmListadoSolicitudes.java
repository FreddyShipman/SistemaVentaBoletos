/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ventaboletos.presentacion;

/**
 *
 * @author JONATHAN ROMERO OROZCO - 00000251632
 */
import com.ventaboletos.dto.SolicitudDTO;
import com.ventaboletos.negocio.facade.GestionarSolicitudesDeBoletosFacade;
import com.ventaboletos.negocio.facade.IGestionarSolicitudesDeBoletos;
import com.ventaboletos.negocio.facade.INavegacion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;


public class FrmListadoSolicitudes extends JPanel {

    private INavegacion navegador;
    private IGestionarSolicitudesDeBoletos solicitudesFacade;
    
    // Componentes
    public JTextField txtBusqueda;
    public JTable tablaSolicitudes;
    public JButton btnTabAll, btnTabPending, btnTabApproved;
    private DefaultTableModel tableModel; // Para manipular los datos

    // Constructor vacío para pruebas
    public FrmListadoSolicitudes() {
        this(null);
    }

    // Constructor Principal
    public FrmListadoSolicitudes(INavegacion navegador) {
        this.navegador = navegador;
        this.solicitudesFacade = new GestionarSolicitudesDeBoletosFacade();
        
        initComponents();
        cargarSolicitudes(); // Cargar datos al iniciar
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(0, 20));
        this.setBackground(Color.decode("#121212"));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Panel Superior (Título, Botón Crear y Buscador) ---
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.setOpaque(false);

        // Header con título y botón de "Nueva"
        JPanel headerRow = new JPanel(new BorderLayout());
        headerRow.setOpaque(false);
        
        JLabel lblTitulo = new JLabel("Mis Solicitudes");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JButton btnNueva = new JButton("+ Nueva Solicitud");
        btnNueva.setBackground(Color.decode("#8b5cf6"));
        btnNueva.setForeground(Color.WHITE);
        btnNueva.setFocusPainted(false);
        // Acción para ir a CREAR
        btnNueva.addActionListener(e -> {
            if(navegador != null) navegador.cambiarVista("CREAR_SOLICITUD");
        });

        headerRow.add(lblTitulo, BorderLayout.WEST);
        headerRow.add(btnNueva, BorderLayout.EAST);

        JLabel lblSubtitulo = new JLabel("Consulta el estado de tus aclaraciones y reembolsos.");
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Barra de búsqueda
        txtBusqueda = new JTextField("Buscar...");
        txtBusqueda.setBackground(Color.decode("#27272a"));
        txtBusqueda.setForeground(Color.LIGHT_GRAY);
        txtBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtBusqueda.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tabs
        JPanel panelTabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTabs.setOpaque(false);
        panelTabs.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnTabAll = crearBotonTab("Todas", true);
        btnTabPending = crearBotonTab("Pendientes", false);
        btnTabApproved = crearBotonTab("Aprobadas", false);
        
        // Listeners simples para refrescar (en un sistema real filtrarían)
        btnTabAll.addActionListener(e -> cargarSolicitudes());

        panelTabs.add(btnTabAll);
        panelTabs.add(btnTabPending);
        panelTabs.add(btnTabApproved);

        panelTop.add(headerRow);
        panelTop.add(Box.createVerticalStrut(5));
        panelTop.add(lblSubtitulo);
        panelTop.add(Box.createVerticalStrut(15));
        panelTop.add(txtBusqueda);
        panelTop.add(Box.createVerticalStrut(15));
        panelTop.add(panelTabs);

        this.add(panelTop, BorderLayout.NORTH);

        // --- Tabla Central ---
        // Definimos columnas. Usamos un modelo no editable.
        String[] columnas = {"ID", "Tipo", "Boleto Ref", "Fecha", "Estado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos que la tabla no sea editable
            }
        };

        tablaSolicitudes = new JTable(tableModel);
        estilarTabla(tablaSolicitudes);

        // --- EVENTO: Doble Clic para ver detalle ---
        tablaSolicitudes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablaSolicitudes.getSelectedRow() != -1) {
                    abrirDetalleSeleccionado();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaSolicitudes);
        scrollPane.getViewport().setBackground(Color.decode("#121212"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void cargarSolicitudes() {
        // Limpiamos tabla
        tableModel.setRowCount(0);
        
        // Obtenemos datos del Mock (Usuario "cliente")
        List<SolicitudDTO> lista = solicitudesFacade.obtenerSolicitudesPorUsuario("cliente");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (SolicitudDTO s : lista) {
            Object[] fila = {
                s.getIdSolicitud(),
                s.getTipoSolicitud(),
                s.getIdBoleto() != null ? s.getIdBoleto() : "N/A",
                (s.getFechaSolicitud() != null) ? sdf.format(s.getFechaSolicitud()) : "-",
                s.getEstado()
            };
            tableModel.addRow(fila);
        }
    }
    
    private void abrirDetalleSeleccionado() {
        int row = tablaSolicitudes.getSelectedRow();
        String idSolicitud = (String) tableModel.getValueAt(row, 0);
        
        // En un caso real, buscaríamos el objeto completo por ID. 
        // Aquí simulamos creando uno rápido o pasando datos básicos.
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(idSolicitud);
        dto.setTipoSolicitud((String) tableModel.getValueAt(row, 1));
        dto.setEstado((String) tableModel.getValueAt(row, 4));
        
        if (navegador != null) {
            navegador.cambiarVista("DETALLE_SOLICITUD", dto);
        }
    }

    private JButton crearBotonTab(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setForeground(activo ? Color.WHITE : Color.GRAY);
        btn.setFont(new Font("SansSerif", activo ? Font.BOLD : Font.PLAIN, 14));
        return btn;
    }

    private void estilarTabla(JTable table) {
        table.setBackground(Color.decode("#121212"));
        table.setForeground(Color.LIGHT_GRAY);
        table.setGridColor(Color.decode("#27272a"));
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.getTableHeader().setBackground(Color.decode("#121212"));
        table.getTableHeader().setForeground(Color.GRAY);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
    }
}