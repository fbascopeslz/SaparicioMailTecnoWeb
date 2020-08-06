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
import java.sql.Time;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author fbasc
 */
public class Ingreso {        
    
    int id;
    float totalCompra;
    String tipoComprobante;
    String numComprobante;    
    String estado;
    int idProveedor;
    int idUsuario;
    
    Conexion conexion;

    
    public Ingreso() {
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
    
    
    public void setIngreso(String totalCompra, String tipoComprobante, String numComprobante,  
            String estado, int idProveedor, int idUsuario) {
        this.totalCompra = Float.parseFloat(totalCompra);
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.estado = estado;        
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
    }
    
    
    public int registrarIngreso() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO ingreso(\n"
                + "fecha, hora, totalCompra, tipoComprobante, numComprobante, estado, idProveedor, idUsuario)\n"
                + "VALUES(?, ?, ?, ? ,?, ?, ?, ?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            
            ps.setDate(1, Date.valueOf(Utils.fechaActual()));                        
            ps.setTime(2, Time.valueOf(Utils.horaActual()));
            
            ps.setFloat(3, this.totalCompra);
            ps.setString(4, this.tipoComprobante);
            ps.setString(5, this.numComprobante);
            ps.setString(6, this.estado);
            
            ps.setInt(7, this.idProveedor);
            ps.setInt(8, this.idUsuario);
            
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado para devolverlo
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
    
    public Boolean existeIngreso(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "ingreso.id\n"               
                + "FROM ingreso\n"                
                + "WHERE ingreso.id=?";
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
    
    public DefaultTableModel getIngreso(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ingreso = new DefaultTableModel();
        ingreso.setColumnIdentifiers(new Object[]{
            "id", "fecha", "hora", "totalCompra", "tipoComprobante", "numComprobante",
            "estado", "idProveedor", "idUsuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "ingreso.id,\n"
                + "ingreso.fecha,\n"
                + "ingreso.hora,\n"
                + "ingreso.totalCompra,\n"
                + "ingreso.tipoComprobante,\n"
                + "ingreso.numComprobante,\n"
                + "ingreso.estado,\n"
                + "ingreso.idProveedor,\n"
                + "ingreso.idUsuario\n"
                + "FROM ingreso\n"
                + "WHERE ingreso.id=?";
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
                ingreso.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getFloat("totalCompra"),
                    rs.getString("tipoComprobante"),
                    rs.getString("numComprobante"),
                    rs.getString("estado"),                                        
                    rs.getInt("idProveedor"),
                    rs.getInt("idUsuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ingreso;
    }
      
    public DefaultTableModel getIngresos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ingresos = new DefaultTableModel();
        ingresos.setColumnIdentifiers(new Object[]{
            "id", "fecha", "hora", "totalCompra", "tipoComprobante", "numComprobante",
            "estado", "proveedor", "usuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

         // Preparo la consulta
        String sql = "SELECT\n"
                + "ingreso.id,\n"
                + "ingreso.fecha,\n"
                + "ingreso.hora,\n"
                + "ingreso.totalCompra,\n"
                + "ingreso.tipoComprobante,\n"
                + "ingreso.numComprobante,\n"
                + "ingreso.estado,\n"                
                + "CONCAT(TRIM(proveedor.nombre)) AS proveedor,\n"
                + "CONCAT(TRIM(usuario.usuario)) AS usuario\n"                
                + "FROM ingreso, proveedor, usuario\n"                
                + "WHERE ingreso.idproveedor = proveedor.id and "
                + "ingreso.idusuario = usuario.id\n"
                + "ORDER BY ingreso.id ASC";                                
        
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
                ingresos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getFloat("totalCompra"),
                    rs.getString("tipoComprobante"),
                    rs.getString("numComprobante"),
                    rs.getString("estado"),                                        
                    rs.getString("proveedor"),
                    rs.getString("usuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ingresos;
    }
    
    
    public DefaultTableModel getIngresosRangoFecha(String fecha1, String fecha2) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.setColumnIdentifiers(new Object[]{
            "id", "fecha", "hora", "totalCompra", "tipoComprobante", "numComprobante",
            "estado", "proveedor", "usuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

         // Preparo la consulta
        String sql = "SELECT\n"
                + "ingreso.id,\n"
                + "ingreso.fecha,\n"
                + "ingreso.hora,\n"
                + "ingreso.totalCompra,\n"
                + "ingreso.tipoComprobante,\n"
                + "ingreso.numComprobante,\n"
                + "ingreso.estado,\n"                
                + "CONCAT(TRIM(proveedor.nombre)) AS proveedor,\n"
                + "CONCAT(TRIM(usuario.usuario)) AS usuario\n"                
                + "FROM ingreso, proveedor, usuario\n"                
                + "WHERE ingreso.idproveedor = proveedor.id and "
                + "ingreso.idusuario = usuario.id and "
                + "fecha >= '" + fecha1 + "' and fecha <= '" + fecha2 +"'";                
        
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
                ventas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getFloat("totalCompra"),
                    rs.getString("tipoComprobante"),
                    rs.getString("numComprobante"),
                    rs.getString("estado"),                                        
                    rs.getString("proveedor"),
                    rs.getString("usuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
            
    
    public static void main(String[] args) {
        // TODO code application logic here
            Ingreso i = new Ingreso();
            //g.setGrupo(1,"Empleados");
            //v.setVenta("2020-06-20", "15:00:00", (float) 153.153, "REALIZADO", "12345", 6, 1);
            //int id = v.registrarVenta();
            
            DefaultTableModel dtm = i.getIngresos();
            System.out.println(dtm);
            
            //g.setId(4);
            //g.eliminarGrupo();
            //int i = g.registrarGrupo();
            //System.out.println(i);
    }
    
}
