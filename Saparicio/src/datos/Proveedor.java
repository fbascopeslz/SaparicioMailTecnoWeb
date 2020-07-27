/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import db.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class Proveedor {
    
    int id;
    String nombre;
    String tipoDocumento;
    String numDocumento;
    String telefono;
    String direccion;
    String contacto;
    String telefonoContacto;
    boolean estado;      
    
    Conexion conexion;

    
    public Proveedor() {
        this.conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
   

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }               
        
    
    public void setProveedor(String nombre, String tipoDocumento, String numDocumento, 
            String telefono, String direccion, String contacto, String telefonoContacto,
            String estado) {        
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contacto = contacto;
        this.telefonoContacto = telefonoContacto;
        this.estado = Boolean.parseBoolean(estado);
    }
    
    
    public int registrarProveedor() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO proveedor(\n"
                + "nombre, tipoDocumento, numDocumento, telefono, direccion, contacto, telefonoContacto)\n"
                + "VALUES(?, ?, ?, ? ,?, ?, ?)";             

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.tipoDocumento);
            ps.setString(3, this.numDocumento);
            ps.setString(4, this.telefono);
            ps.setString(5, this.direccion);
            ps.setString(6, this.contacto);
            ps.setString(7, this.telefonoContacto);
            
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
      
    public void eliminarProveedor() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM proveedor\n"
                + "WHERE proveedor.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modificarProveedor() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE proveedor SET\n" 
                + "nombre = ? ,\n"
                + "tipoDocumento = ? ,\n"
                + "numDocumento = ? ,\n"
                + "telefono = ? ,\n"
                + "direccion = ? ,\n"
                + "contacto = ? ,\n"
                + "telefonoContacto = ? \n"
                + "estado = ? \n"
                + "WHERE proveedor.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setString(1, this.nombre);
            ps.setString(2, this.tipoDocumento);
            ps.setString(3, this.numDocumento);
            ps.setString(4, this.telefono);            
            ps.setString(5, this.direccion);
            ps.setString(6, this.contacto);
            ps.setString(7, this.telefonoContacto);                        
            ps.setBoolean(8, this.estado);
            ps.setInt(8, this.id);
            
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
    public Boolean existeProveedor(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "proveedor.id\n"               
                + "FROM proveedor\n"                
                + "WHERE proveedor.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas
        boolean sw = false;
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            int r = 0;
            while(rs.next()){
                r = rs.getInt(1);
            }
            System.out.println(r);
            if(r == id){
                sw= true;
            } 
            // Cierro la conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sw;
    }    
    
    public DefaultTableModel getProveedor(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel proveedor = new DefaultTableModel();
        proveedor.setColumnIdentifiers(new Object[]{
            "id", "nombre", "tipoDocumento", "numDocumento", "telefono", "direccion", "contacto", "telefonoContacto", "estado"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "proveedor.id,\n"
                + "proveedor.nombre,\n"
                + "proveedor.tipoDocumento,\n"
                + "proveedor.numDocumento,\n"
                + "proveedor.telefono,\n"
                + "proveedor.direccion,\n"
                + "proveedor.contacto,\n"
                + "proveedor.telefonoContacto,\n"
                + "proveedor.estado\n"
                + "FROM proveedor\n"
                + "WHERE proveedor.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                proveedor.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tipoDocumento"),
                    rs.getString("numDocumento"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("contacto"),
                    rs.getString("telefonoContacto"),                                        
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return proveedor;
    }
      
    public DefaultTableModel getProveedores() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel proveedores = new DefaultTableModel();
        proveedores.setColumnIdentifiers(new Object[]{
            "id", "nombre", "tipoDocumento", "numDocumento", "telefono", "direccion", "contacto", "telefonoContacto", "estado"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "proveedor.id,\n"
                + "proveedor.nombre,\n"
                + "proveedor.tipoDocumento,\n"
                + "proveedor.numDocumento,\n"
                + "proveedor.telefono,\n"
                + "proveedor.direccion,\n"
                + "proveedor.contacto,\n"
                + "proveedor.telefonoContacto,\n"
                + "proveedor.estado\n"
                + "FROM proveedor\n";                
        
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                proveedores.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tipoDocumento"),
                    rs.getString("numDocumento"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("contacto"),
                    rs.getString("telefonoContacto"),                                        
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return proveedores;
    }
    
    
    public DefaultTableModel estadisticaProveedoresMasSolicitados() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel proveedores = new DefaultTableModel();
        proveedores.setColumnIdentifiers(new Object[]{
            "proveedor", "numerocompras"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT TRIM(proveedor.nombre) as proveedor, COUNT(*) as numerocompras\n" +
                    "FROM ingreso, proveedor\n" +
                    "WHERE ingreso.idProveedor = proveedor.id\n" +
                    "GROUP BY proveedor.nombre\n" +
                    "ORDER BY numerocompras DESC\n" + 
                    "LIMIT 10";                
        
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                proveedores.addRow(new Object[]{                    
                    rs.getString("proveedor"),
                    rs.getInt("numerocompras"),                    
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return proveedores;
    }
            
    
    public static void main(String[] args) {
        // TODO code application logic here
            Proveedor g = new Proveedor();
            //g.setGrupo(1,"Empleados");
            
            g.getProveedores();
            
            //g.setId(4);
            //g.eliminarGrupo();
            //int i = g.registrarGrupo();
            //System.out.println(i);
    }
    
    
}
