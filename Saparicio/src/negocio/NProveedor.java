/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Producto;
import datos.Proveedor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NProveedor {
    
    private Proveedor proveedor;

    public NProveedor() {
        this.proveedor = new Proveedor();
    }

    public int registrarProveedor(String nombre, String tipoDocumento, String numDocumento, 
            String telefono, String direccion, String contacto, String telefonoContacto) {        
        this.proveedor.setProveedor(nombre, tipoDocumento, numDocumento, telefono, 
                direccion, contacto, telefonoContacto, "true");
        return this.proveedor.registrarProveedor();
    }

    public void modificarProveedor(int id, String nombre, String tipoDocumento, 
            String numDocumento, String telefono, String direccion, String contacto, 
            String telefonoContacto, String estado) {
        this.proveedor.setId(id);
        this.proveedor.setProveedor(nombre, tipoDocumento, numDocumento, 
                telefono, direccion, contacto, telefonoContacto, estado);
        this.proveedor.modificarProveedor();
    }

    public void eliminarProveedor(int id) {
        this.proveedor.setId(id);
        this.proveedor.eliminarProveedor();
    }

    public DefaultTableModel mostrarProveedores() {
        return this.proveedor.getProveedores();
    }

    public DefaultTableModel mostrarProveedorId(int id) {
        return this.proveedor.getProveedor(id);
    }

    public Boolean existeProveedor(int id) {
        return this.proveedor.existeProveedor(id);
    }
    
}
