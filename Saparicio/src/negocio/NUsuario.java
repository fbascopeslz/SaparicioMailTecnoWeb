/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Usuario;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class NUsuario {
    
    private Usuario usuario;

    public NUsuario() {
        this.usuario = new Usuario();
    }

    public int registrarUsuario(String ci, String nombres, String paterno, 
            String materno, String fechaNac, String telefono, String email,
            String direccion, String usuario, String password, int idRol) throws ParseException {        
        this.usuario.setUsuario(ci, nombres, paterno, materno, fechaNac, 
                telefono,  email, direccion,  usuario,  password, "true", idRol);
        return this.usuario.registrarUsuario();
    }

    public void modificarUsuario(int id, String ci, String nombres, String paterno, 
            String materno, String fechaNac, String telefono, String email,
            String direccion, String usuario, String password, String estado, int idRol) throws ParseException {
        this.usuario.setId(id);
        this.usuario.setUsuario(ci, nombres, paterno, materno, fechaNac, 
                telefono,  email, direccion,  usuario,  password, estado, idRol);
        this.usuario.modificarUsuario();
    }

    public void eliminarUsuario(int id) {
        this.usuario.setId(id);
        this.usuario.eliminarUsuario();
    }

    public DefaultTableModel mostrarUsuarios() {
        return this.usuario.getUsuarios();
    }

    public DefaultTableModel mostrarUsuarioId(int id) {
        return this.usuario.getUsuario(id);
    }

    public Boolean existeUsuario(int id) {
        return this.usuario.existeUsuario(id);
    }
    
}
