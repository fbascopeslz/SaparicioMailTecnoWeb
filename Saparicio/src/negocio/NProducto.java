/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Producto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NProducto {
    
    private Producto producto;

    public NProducto() {
        this.producto = new Producto();
    }

    public int registrarProducto(String codigo, String nombre, String descripcion, String precio, int stock) {        
        this.producto.setProducto(codigo, nombre, descripcion, "true", precio, stock);
        return this.producto.registrarProducto();
    }

    public void modificarProducto(int id, String codigo, String nombre, String descripcion, String estado, String precio, int stock) {
        this.producto.setId(id);
        this.producto.setProducto(codigo, nombre, descripcion, estado, precio, stock);
        this.producto.modificarProducto();
    }

    public void eliminarProducto(int id) {
        this.producto.setId(id);
        this.producto.eliminarProducto();
    }

    public DefaultTableModel mostrarProductos() {
        return this.producto.getProductos();
    }

    public DefaultTableModel mostrarProductosId(int id) {
        return this.producto.getProducto(id);
    }

    public Boolean ExisteProducto(int id) {
        return this.producto.existeProducto(id);
    }
    
}
