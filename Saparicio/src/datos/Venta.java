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
public class Venta {
    
    int id;
    float total;
    String estado;
    String numComprobante;    
    int idCliente;
    int idUsuario;
    
    Conexion conexion;

    
    public Venta() {
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
        
    
    public void setVenta(String total, String estado, String numComprobante, 
            int idCliente, int idUsuario) {
        this.total = Float.parseFloat(total);
        this.estado = estado;
        this.numComprobante = numComprobante;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }
    
    
    public int registrarVenta() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO venta(\n"
                + "fecha, hora, total, estado, numComprobante, idCliente, idUsuario)\n"
                + "VALUES(?, ?, ?, ? ,?, ?, ?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
                                                           
            ps.setDate(1, Date.valueOf(Utils.fechaActual()));                        
            ps.setTime(2, Time.valueOf(Utils.horaActual()));
            
            ps.setFloat(3, this.total);
            ps.setString(4, this.estado);
            ps.setString(5, this.numComprobante);
            ps.setInt(6, this.idCliente);
            ps.setInt(7, this.idUsuario);
            
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
    
    public Boolean existeVenta(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id\n"               
                + "FROM venta\n"                
                + "WHERE venta.id=?";
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
    
    public DefaultTableModel getVenta(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel venta = new DefaultTableModel();
        venta.setColumnIdentifiers(new Object[]{
            "id","fecha", "hora", "total", "estado", "numComprobante", "idCliente", "idUsuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id,\n"
                + "venta.fecha,\n"
                + "venta.hora,\n"
                + "venta.total,\n"
                + "venta.estado,\n"
                + "venta.numComprobante,\n"
                + "venta.idCliente,\n"
                + "venta.idUsuario\n"
                + "FROM venta\n"
                + "WHERE venta.id=?";
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
                venta.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getFloat("total"),
                    rs.getString("estado"),
                    rs.getString("numComprobante"),                    
                    rs.getInt("idCliente"),
                    rs.getInt("idUsuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return venta;
    }
      
    public DefaultTableModel getVentas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.setColumnIdentifiers(new Object[]{
            "id", "fecha", "hora", "total", "estado", "numComprobante", "cliente", "usuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id,\n"
                + "venta.fecha,\n"
                + "venta.hora,\n"
                + "venta.total,\n"
                + "venta.estado,\n"
                + "venta.numComprobante,\n"
                + "CONCAT(TRIM(cliente.nombres), ' ', TRIM(cliente.paterno)) AS cliente,\n"
                + "CONCAT(TRIM(usuario.usuario)) AS usuario\n"
                + "FROM venta, cliente, usuario\n"
                + "WHERE venta.idcliente = cliente.id and "
                + "venta.idusuario = usuario.id\n"
                + "ORDER BY venta.id ASC";                
        
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
                    rs.getFloat("total"),
                    rs.getString("estado"),
                    rs.getString("numComprobante"),                    
                    rs.getString("cliente"),
                    rs.getString("usuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
    
    
    public DefaultTableModel getVentasRangoFecha(String fecha1, String fecha2) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.setColumnIdentifiers(new Object[]{
            "id", "fecha", "hora", "total", "estado", "numComprobante", "cliente", "usuario"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "venta.id,\n"
                + "venta.fecha,\n"
                + "venta.hora,\n"
                + "venta.total,\n"
                + "venta.estado,\n"
                + "venta.numComprobante,\n"
                + "CONCAT(TRIM(cliente.nombres), ' ', TRIM(cliente.paterno)) AS cliente,\n"
                + "CONCAT(TRIM(usuario.usuario)) AS usuario\n"
                + "FROM venta, cliente, usuario\n"
                + "WHERE venta.idcliente = cliente.id and "
                + "venta.idusuario = usuario.id and "
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
                    rs.getFloat("total"),
                    rs.getString("estado"),
                    rs.getString("numComprobante"),                    
                    rs.getString("cliente"),
                    rs.getString("usuario")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
            
    
    public DefaultTableModel estadisticaFinancierasMeseVsIngresoGasto(String anio) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel estadistica = new DefaultTableModel();
        estadistica.setColumnIdentifiers(new Object[]{
            "mes", "ingreso", "gasto"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT TO_CHAR(venta.fecha, 'MM') as mes, "
                        + "SUM(CAST(venta.total as decimal)) as ingreso, "
                        + "SUM(CAST(ingreso.totalCompra as decimal)) as gasto\n" +
                    "FROM venta, ingreso\n" +
                    "WHERE TO_CHAR(venta.fecha, 'YYYY') = '" + anio +"' and "
                        + "TO_CHAR(ingreso.fecha, 'YYYY') = '" + anio +"'\n" +
                    "GROUP BY TO_CHAR(venta.fecha, 'MM')\n" +
                    "ORDER BY mes ASC";
        
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
                estadistica.addRow(new Object[]{                    
                    rs.getString("mes"),
                    rs.getInt("ingreso"),
                    rs.getInt("gasto"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estadistica;
    }
    
    
    public DefaultTableModel estadisticaFinancierasIngresosGastosUtilidades(String anio) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel estadistica = new DefaultTableModel();
        estadistica.setColumnIdentifiers(new Object[]{
            "ingreso", "gasto", "utilidad"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT CAST(tabla.ingreso as decimal), "
                    + "CAST(tabla.gasto as decimal), "
                    + "(tabla.ingreso - tabla.gasto) as utilidad\n" +
                    "FROM(\n" +
                        "SELECT SUM(venta.total ) as ingreso, SUM(ingreso.totalCompra) as gasto\n" +
                        "FROM venta, ingreso\n" +
                    ") as tabla";
        
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
                estadistica.addRow(new Object[]{                   
                    rs.getInt("ingreso"),
                    rs.getInt("gasto"),
                    rs.getInt("utilidad"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estadistica;
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
            Venta v = new Venta();
            //g.setGrupo(1,"Empleados");
            //v.setVenta("2020-06-20", "15:00:00", (float) 153.153, "REALIZADO", "12345", 6, 1);
            //int id = v.registrarVenta();
            
            DefaultTableModel dtm = v.getVentas();
            System.out.println(dtm);
            
            //g.setId(4);
            //g.eliminarGrupo();
            //int i = g.registrarGrupo();
            //System.out.println(i);
    }
}
