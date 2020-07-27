/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.DetalleIngreso;
import datos.DetalleVenta;
import datos.Ingreso;
import datos.Venta;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NIngreso {
    
    private Ingreso ingreso;
    private DetalleIngreso dingreso;

    public NIngreso() {
        this.ingreso = new Ingreso();
        this.dingreso = new DetalleIngreso();
    }

    public int registrarIngreso(String totalCompra, String tipoComprobante, 
            String numComprobante, String estado, int idProveedor, 
            int idUsuario, String detalleIngreso) throws ParseException {
        
        this.ingreso.setIngreso(totalCompra, tipoComprobante, numComprobante, 
                estado, idProveedor, idUsuario);
        int idIngreso = this.ingreso.registrarIngreso();
        
        dingreso.setidIngreso(idIngreso);
        dingreso.registrarDetalleIngreso(detalleIngreso);
                
        return idIngreso;
    }

    public DefaultTableModel mostrarIngresos() {
        return this.ingreso.getIngresos();
    }

    public DefaultTableModel mostrarIngresoId(int id) {
        return this.ingreso.getIngreso(id);
    }    
    public DefaultTableModel mostrarDetallesIngresoId(int idVenta) {
        return this.dingreso.getDetallesIngresoId(idVenta);
    }
    
    public Boolean existeVenta(int id) {
        return this.ingreso.existeIngreso(id);
    }
    
}
