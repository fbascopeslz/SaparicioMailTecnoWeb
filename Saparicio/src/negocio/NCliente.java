/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Cliente;
import datos.Usuario;
import java.sql.Date;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NCliente {
    
    private Cliente cliente;

    public NCliente() {
        this.cliente = new Cliente();
    }

    public int registrarCliente(String ci, String nombres, String paterno, 
            String materno, String fechaNac, String telefono, String email,
            String direccion) {  
        
        this.cliente.setCliente(ci, 
                                nombres, 
                                paterno, 
                                materno, 
                                Date.valueOf(fechaNac), 
                                Integer.parseInt(telefono),  
                                email, 
                                direccion, 
                                Boolean.parseBoolean("true"));
        
        return this.cliente.registrarCliente();
    }

    public void modificarCliente(int id, String ci, String nombres, String paterno, 
            String materno, String fechaNac, String telefono, String email,
            String direccion, String estado) {
        
        this.cliente.setId(id);
        
        this.cliente.setCliente(ci, 
                                nombres, 
                                paterno, 
                                materno, 
                                Date.valueOf(fechaNac), 
                                Integer.parseInt(telefono), 
                                email, 
                                direccion, 
                                Boolean.parseBoolean(estado));
        
        this.cliente.modificarCliente();
    }

    public void eliminarCliente(int id) {
        this.cliente.setId(id);
        this.cliente.eliminarCliente();
    }

    public DefaultTableModel mostrarClientes() {
        return this.cliente.getClientes();
    }

    public DefaultTableModel mostrarClienteId(int id) {
        return this.cliente.getCliente(id);
    }

    public Boolean existeCliente(int id) {
        return this.cliente.existeCliente(id);
    }
    
}
