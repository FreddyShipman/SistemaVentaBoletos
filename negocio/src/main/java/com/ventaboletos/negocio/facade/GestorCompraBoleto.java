package com.ventaboletos.negocio.facade;

/**
 *
 * @author alfre
 */

import com.ventaboletos.dto.ConciertoDTO;
import com.ventaboletos.dto.ZonaDTO;
import com.ventaboletos.negocio.facade.interfaces.IGestorCompra;
import com.ventaboletos.negocio.facade.interfaces.INotificador;
import com.ventaboletos.persistencia.dao.ConciertoDAO;
import com.ventaboletos.persistencia.dao.VentaDAO;
import com.ventaboletos.persistencia.entidades.Boleto;
import com.ventaboletos.persistencia.entidades.Concierto;
import com.ventaboletos.persistencia.entidades.Usuario;
import com.ventaboletos.persistencia.entidades.Venta;
import com.ventaboletos.persistencia.entidades.Zona;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorCompraBoleto implements IGestorCompra {

    private ConciertoDAO conciertoDAO;
    private VentaDAO ventaDAO;
    private INotificador notificador;

    public GestorCompraBoleto(INotificador notificador) {
        this.conciertoDAO = ConciertoDAO.getInstancia();
        this.ventaDAO = VentaDAO.getInstancia();
        this.notificador = notificador;
    }

    @Override
    public List<ConciertoDTO> obtenerConciertosDisponibles() {
        List<Concierto> conciertos = conciertoDAO.obtenerTodos();
        List<ConciertoDTO> dtos = new ArrayList<>();

        for (Concierto c : conciertos) {
            List<ZonaDTO> zonasDto = new ArrayList<>();
            for (Zona z : c.getZonas()) {
                zonasDto.add(new ZonaDTO(z.getNombre(), z.getPrecio(), z.getCapacidad()));
            }
            dtos.add(new ConciertoDTO(c.getNombre(), c.getArtista(), c.getFecha(), c.getLugar(), zonasDto));
        }
        return dtos;
    }

    @Override
    public boolean procesarCompra(String nombreConcierto, String nombreZona, int cantidadBoletos, String emailUsuario) {
        Concierto conciertoEncontrado = buscarConcierto(nombreConcierto);
        if (conciertoEncontrado == null) {
            notificador.mostrarError("Error: Concierto no encontrado.");
            return false;
        }

        Zona zonaEncontrada = null;
        for (Zona z : conciertoEncontrado.getZonas()) {
            if (z.getNombre().equalsIgnoreCase(nombreZona)) {
                zonaEncontrada = z;
                break;
            }
        }

        if (zonaEncontrada == null) {
            notificador.mostrarError("Error: Zona no encontrada.");
            return false;
        }

        if (zonaEncontrada.getCapacidad() < cantidadBoletos) {
            notificador.mostrarError("Lo sentimos, solo quedan " + zonaEncontrada.getCapacidad() + " boletos en esta zona.");
            return false;
        }

        zonaEncontrada.setCapacidad(zonaEncontrada.getCapacidad() - cantidadBoletos);

        double totalVenta = zonaEncontrada.getPrecio() * cantidadBoletos;

        List<Boleto> boletosGenerados = new ArrayList<>();
        for (int i = 0; i < cantidadBoletos; i++) {
            boletosGenerados.add(new Boleto(zonaEncontrada.getNombre(), zonaEncontrada.getPrecio()));
        }

        Usuario usuario = new Usuario("Cliente Anonimo", emailUsuario, "1234");

        Venta nuevaVenta = new Venta(new Date(), totalVenta, usuario, conciertoEncontrado, boletosGenerados);
        ventaDAO.guardar(nuevaVenta);

        notificador.mostrarConfirmacion("¡Compra Exitosa!\n"
                + "Total pagado: $" + totalVenta + "\n"
                + "Boletos generados: " + cantidadBoletos + "\n"
                + "Código de Venta: " + nuevaVenta.getIdVenta());
        
        return true;
    }

    private Concierto buscarConcierto(String nombre) {
        for (Concierto c : conciertoDAO.obtenerTodos()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }
}