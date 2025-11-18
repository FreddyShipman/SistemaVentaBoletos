package com.ventaboletos.presentacion;

// Imports necesarios para el DTO y la Fachada
import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.negocio.facade.IGestionConciertos;
import com.ventaboletos.negocio.facade.GestionConciertosFacade;
import com.ventaboletos.dto.UsuarioDTO;
import com.ventaboletos.negocio.facade.AutenticacionFacade;
import com.ventaboletos.negocio.facade.IAutenticacion;
import com.ventaboletos.dto.BoletoDTO;
import com.ventaboletos.negocio.facade.IVentaBoletos;
import com.ventaboletos.negocio.facade.VentaBoletosFacade;

// Imports para el Test Driver
import java.util.Date;
import java.util.List;

// Imports para el futuro JFrame
// (Están comentados, pero se usaran después, aclarando que este no sera una clase main sino un JFrame)
// import javax.swing.JOptionPane;
// import javax.swing.table.DefaultTableModel;


/**
 * Esta clase representa la pantalla (JFrame) para gestionar conciertos.
 * * Por ahora (Versión 1), contiene un método main() para actuar como
 * un "Test Driver" de consola y probar la fachada "Mock".
 * 
 * * @author JOSÉ ALFREDO GUZMAN MORENO - 00000252524
 */
// 1. La clase ya está lista para ser un JFrame base
public class FrmGestionConciertos /*extends javax.swing.JFrame */ {
    
    // TEST DRIVER (Método Main para pruebas de consola)
    
    /**
     * Este es el Test Driver para la V1.
     * Ejecuta este archivo para probar la fachada "Mock".
     */
    public static void main(String[] args) {
        
        // 1. Instanciamos nuestra fachada (implementación "Mock")
        IGestionConciertos negocio = new GestionConciertosFacade();

        System.out.println("----- INICIO DE PRUEBAS (V1 - MOCK) -----");
        
        // PRUEBA 1: REGISTRAR UN NUEVO CONCIERTO
        System.out.println("\n----- PRUEBA 1: Registrando Concierto... -----");
        try {
            // La intención es simular datos que vendrían de los JTextFields
            ConciertoDTO dto = new ConciertoDTO();
            dto.setNombre("Concierto de prueba mockito");
            dto.setArtista("Los mock");
            dto.setFecha(new Date());
            dto.setLugar("FrmGestionConcierdos");
            
            // Llamamos a la fachada
            negocio.registrarNuevoConcierto(dto);
            
            System.out.println(">>> PRUEBA 1: EXITO. El 'Mock' acepto el concierto");
            
        } catch (Exception e) {
            System.err.println(">>> PRUEBA 1: FALLO. " + e.getMessage());
        }

        // PRUEBA 2: OBTENER TODOS LOS CONCIERTOS
        System.out.println("\n----- PRUEBA 2: Obteniendo Conciertos... -----");
        
        List<ConciertoDTO> lista = negocio.obtenerTodosLosConciertos();
        
        if (lista.isEmpty()) {
            System.out.println(">>> PRUEBA 2: La fachada 'Mock' no devolvio conciertos.");
        } else {
            System.out.println(">>> PRUEBA 2: EXITO. Se obtuvieron " + lista.size() + " conciertos (simulados):");
            for (ConciertoDTO c : lista) {
                System.out.println("  -> " + c.getNombre() + " por " + c.getArtista() + " en " + c.getLugar());
            }
        }
        
        // PRUEBA 3: AUTENTICACIÓN (LOGIN) ---
        System.out.println("\n----- PRUEBA 3: Probando Login Mock -----");
        
        // 1. Instanciamos la fachada de autenticación
        IAutenticacion loginNegocio = new AutenticacionFacade();

        // SUB-PRUEBA A: Login Correcto (Admin)
        try {
            System.out.println("A) Intentando ingresar con usuario 'admin' y pass '12345'...");
            UsuarioDTO credenciales = new UsuarioDTO("admin", "12345");
            
            UsuarioDTO usuarioLogueado = loginNegocio.autenticar(credenciales);
            
            System.out.println(">>> EXITO: Bienvenido " + usuarioLogueado.getNombreCompleto());
            System.out.println(">>> Rol detectado: " + usuarioLogueado.getRol());
            
        } catch (Exception e) {
            System.err.println(">>> FALLO INESPERADO: " + e.getMessage());
        }

        // SUB-PRUEBA B: Login Incorrecto (Error esperado)
        try {
            System.out.println("\nB) Intentando ingresar con password incorrecto...");
            UsuarioDTO credencialesMalas = new UsuarioDTO("admin", "password_erroneo");
            
            loginNegocio.autenticar(credencialesMalas);
            
            // Si llega aquí, la prueba falló porque debió lanzar error
            System.err.println(">>> ERROR: El sistema dejo pasar una contraseña incorrecta");
            
        } catch (Exception e) {
            System.out.println(">>> EXITO (Error esperado): El sistema rechazo el acceso");
            System.out.println(">>> Mensaje recibido: " + e.getMessage());
        }
        
        // PRUEBA 4: VENTA DE BOLETOS
        System.out.println("\n----- PRUEBA 4: Probando Venta de Boletos Mock -----");
        
        IVentaBoletos ventaNegocio = new VentaBoletosFacade();
        
        // SUB-PRUEBA A: Compra Exitosa
        try {
            System.out.println("A) Intentando comprar asiento B-10...");
            BoletoDTO compra = new BoletoDTO();
            compra.setIdUsuario("cliente1");
            compra.setIdConcierto("1");
            compra.setFila("B");
            compra.setAsiento("10");
            compra.setCosto(1500.00);
            
            ventaNegocio.comprarBoleto(compra); // Debe imprimir ÉXITO
            
        } catch (Exception e) {
            System.err.println(">>> FALLO: " + e.getMessage());
        }

        // SUB-PRUEBA B: Compra Fallida (Asiento Ocupado)
        try {
            System.out.println("\nB) Intentando comprar asiento prohibido (A-1)...");
            BoletoDTO compraMala = new BoletoDTO();
            compraMala.setIdUsuario("cliente1");
            compraMala.setFila("A");
            compraMala.setAsiento("1"); // Este activa el error en el Mock
            compraMala.setCosto(1500.00);
            
            ventaNegocio.comprarBoleto(compraMala);
            System.err.println(">>> ERROR: El sistema permitio comprar un asiento ocupado");
            
        } catch (Exception e) {
            System.out.println(">>> EXITO (Error esperado): " + e.getMessage());
        }

        // SUB-PRUEBA C: Historial
        System.out.println("\nC) Consultando historial de compras...");
        List<BoletoDTO> misBoletos = ventaNegocio.consultarHistorialCompras("cliente1");
        for (BoletoDTO b : misBoletos) {
            System.out.println("  -> Ticket: " + b.getIdBoleto() + " | Evento: " + b.getNombreConcierto());
        }
        
        System.out.println("\n----- FIN DE PRUEBAS -----");
    }
    
    
    // DOCUMENTACIÓN PARA LA FUTURA IMPLEMENTACIÓN DEL JFRAME

    // 1. DECLARACIÓN DE LA FACHADA (Variable de instancia)
    // La vista (el Frame) solo debe conocer la INTERFAZ
    // private IGestionConciertos negocio;

    
    // 2. DECLARACIÓN DE COMPONENTES GRÁFICOS
    // private javax.swing.JTextField txtNombre;
    // private javax.swing.JTextField txtArtista;
    // private javax.swing.JTextField txtLugar;
    // private com.toedter.calendar.JDateChooser jDateChooserFecha;
    // private javax.swing.JButton btnGuardar;
    // private javax.swing.JButton btnLimpiar;
    // private javax.swing.JTable tablaConciertos;

    
    // 3. CONSTRUCTOR DEL JFRAME
    // (Este es el que se ejecutará cuando ya sea visual)
    /*
    public FrmGestionConciertos() {
        initComponents();
        
        // Inicializamos la fachada
        this.negocio = new GestionConciertosFacade();
        
        // Llenamos la tabla con los datos "Mock" al iniciar
        this.cargarConciertosEnTabla();
    }
    */

    
    // 4. MÉTODO DEL BOTÓN GUARDAR (El "ActionListener")
    /*
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // 1. Recolectamos datos de los campos de texto
            ConciertoDTO dto = new ConciertoDTO();
            dto.setNombre(txtNombre.getText());
            dto.setArtista(txtArtista.getText());
            dto.setFecha(jDateChooserFecha.getDate());
            dto.setLugar(txtLugar.getText());

            // 2. Le pasamos el DTO a la fachada
            negocio.registrarNuevoConcierto(dto);

            // 3. Mostramos éxito y actualizamos la tabla
            JOptionPane.showMessageDialog(this, "Concierto guardado con éxito (simulado)");
            this.cargarConciertosEnTabla(); // Actualiza la tabla
            this.limpiarCampos();

        } catch (Exception ex) {
            // 4. Si la fachada lanza un error (Regla de negocio simulada)
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error de Negocio", JOptionPane.ERROR_MESSAGE);
        }
    }
    */

    
    // 5. MÉTODO PARA LLENAR LA TABLA
    /*
    private void cargarConciertosEnTabla() {
        // 1. Pedimos a la fachada la lista de conciertos (falsos en este caso)
        List<ConciertoDTO> conciertos = this.negocio.obtenerTodosLosConciertos();
        
        // 2. Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) this.tablaConciertos.getModel();
        
        // 3. Limpiamos la tabla por si tenía filas anteriores
        modelo.setRowCount(0); 
        
        // 4. Recorremos la lista de DTOs y la añadimos al modelo
        for (ConciertoDTO c : conciertos) {
            Object[] fila = {
                c.getId(),
                c.getNombre(),
                c.getArtista(),
                c.getFecha(),
                c.getLugar()
            };
            modelo.addRow(fila);
        }
    }
    */
    
    // 6. MÉTODO PARA LIMPIAR CAMPOS
    /*
    private void limpiarCampos() {
        this.txtNombre.setText("");
        this.txtArtista.setText("");
        this.txtLugar.setText("");
        this.jDateChooserFecha.setDate(null);
    }
    */
}