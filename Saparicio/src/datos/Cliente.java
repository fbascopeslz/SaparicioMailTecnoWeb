/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import db.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import static utils.Utils.fechaActual;

/**
 *
 * @author fbasc
 */
public class Cliente {
    
    int id;
    String ci;
    String nombres;
    String paterno;
    String materno;
    Date fechaNac;
    int telefono;
    String email;
    String direccion;
    
    boolean estado;  
    
    Conexion conexion;

    
    public Cliente() {
        this.conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
   
    
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }               
        
    
    public void setCliente(String ci, String nombres, String paterno, 
            String materno, Date fechaNac, int telefono,
            String email, String direccion, boolean estado) {    
        
        this.ci = ci;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;      
        this.estado = estado;
    }
    
    
    public int registrarCliente() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO cliente(\n"
                + "ci, nombres, paterno, materno, fechaNac, telefono, email, direccion, fechaUnion)\n"                
                + "VALUES(?, ?, ?, ? ,?, ?, ?, ?, ?)";             

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.ci);
            ps.setString(2, this.nombres);
            ps.setString(3, this.paterno);
            ps.setString(4, this.materno);
            ps.setDate(5, this.fechaNac);
            ps.setInt(6, this.telefono);
            ps.setString(7, this.email);
            ps.setString(8, this.direccion);
            
            ps.setDate(9, Date.valueOf(fechaActual()));            
            
            
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
      
    public void eliminarCliente() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM cliente\n"
                + "WHERE cliente.id = ?";
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
    
    public void modificarCliente() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE cliente SET\n" 
                + "ci = ? ,\n"
                + "nombres = ? ,\n"
                + "paterno = ? ,\n"
                + "materno = ? ,\n"
                + "fechaNac = ? ,\n"
                + "telefono = ? ,\n"
                + "email = ? ,\n"
                + "direccion = ? ,\n"
                + "estado = ? \n"
                + "WHERE cliente.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setString(1, this.ci);
            ps.setString(2, this.nombres);
            ps.setString(3, this.paterno);
            ps.setString(4, this.materno);
            ps.setDate(5, this.fechaNac);
            ps.setInt(6, this.telefono);
            ps.setString(7, this.email);
            ps.setString(8, this.direccion);            
            ps.setBoolean(9, this.estado);
            ps.setInt(10, this.id);
            
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
    public Boolean existeCliente(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "cliente.id\n"               
                + "FROM cliente\n"                
                + "WHERE cliente.id = ? ";
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
    
    public DefaultTableModel getCliente(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cliente = new DefaultTableModel();
        cliente.setColumnIdentifiers(new Object[]{
            "id", "ci", "nombres", "paterno", "materno", "fechaNac", "telefono",
            "email", "direccion", "fechaUnion", "estado"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "cliente.id,\n"
                + "cliente.ci,\n"
                + "cliente.nombres,\n"
                + "cliente.paterno,\n"
                + "cliente.materno,\n"
                + "cliente.fechaNac,\n"
                + "cliente.telefono,\n"
                + "cliente.email,\n"
                + "cliente.direccion,\n"
                + "cliente.fechaUnion,\n"
                + "cliente.estado\n"
                + "FROM cliente\n"
                + "WHERE cliente.id = ? ";
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
                cliente.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("ci"),
                    rs.getString("nombres"),
                    rs.getString("paterno"),
                    rs.getString("materno"),
                    rs.getString("fechaNac"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("fechaUnion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
      
    public DefaultTableModel getClientes() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel clientes = new DefaultTableModel();
        clientes.setColumnIdentifiers(new Object[]{
            "id", "ci", "nombres", "paterno", "materno", "fechaNac", "telefono",
            "email", "direccion", "fechaUnion", "estado"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "cliente.id,\n"
                + "cliente.ci,\n"
                + "cliente.nombres,\n"
                + "cliente.paterno,\n"
                + "cliente.materno,\n"
                + "cliente.fechaNac,\n"
                + "cliente.telefono,\n"
                + "cliente.email,\n"
                + "cliente.direccion,\n"
                + "cliente.fechaUnion,\n"
                + "cliente.estado\n"
                + "FROM cliente";                
        
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
                clientes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("ci"),
                    rs.getString("nombres"),
                    rs.getString("paterno"),
                    rs.getString("materno"),
                    rs.getString("fechaNac"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("fechaUnion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
    
    public DefaultTableModel estadisticaClientesFieles() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel clientes = new DefaultTableModel();
        clientes.setColumnIdentifiers(new Object[]{
            "cliente", "numeroventas"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT CONCAT(TRIM(cliente.nombres), ' ', TRIM(cliente.paterno)) as cliente, COUNT(*) as numeroventas\n" +
                    "FROM venta, cliente\n" +
                    "WHERE venta.idCliente = cliente.id\n" +
                    "GROUP BY cliente.nombres, cliente.paterno\n" +
                    "ORDER BY NumeroVentas DESC\n" +
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
                clientes.addRow(new Object[]{                    
                    rs.getString("cliente"),
                    rs.getInt("numeroventas"),                    
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
            Cliente c = new Cliente();
            //g.setGrupo(1,"Empleados");
            
            DefaultTableModel dtm = c.getClientes();
            System.out.println("");            
            //g.setId(4);
            //g.eliminarGrupo();
            //int i = g.registrarGrupo();
            //System.out.println(i);
    }
    
}
