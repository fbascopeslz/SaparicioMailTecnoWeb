/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.DetalleVenta;
import datos.Venta;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NVenta {
    
    private Venta venta;
    private DetalleVenta dventa;

    public NVenta() {
        this.venta = new Venta();
        this.dventa = new DetalleVenta();
    }

    public int registrarVenta(String total, String estado, String numComprobante, 
            int idCliente, int idUsuario, String detalleVenta) throws ParseException {
        
        this.venta.setVenta(total, estado, numComprobante, idCliente, idUsuario);
        int idVenta = this.venta.registrarVenta();
        
        dventa.setIdVenta(idVenta);
        dventa.registrarDetalleVenta(detalleVenta);
                
        return idVenta;
    }

    public DefaultTableModel mostrarVentas() {
        return this.venta.getVentas();
    }

    public DefaultTableModel mostrarVentaId(int id) {
        return this.venta.getVenta(id);
    }    
    public DefaultTableModel mostrarDetallesVentaId(int idVenta) {
        return this.dventa.getDetallesVentaId(idVenta);
    }
    
    public Boolean existeVenta(int id) {
        return this.venta.existeVenta(id);
    }
  
    
}
