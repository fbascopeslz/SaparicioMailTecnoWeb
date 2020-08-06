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
public class Producto {
    int id;
    String codigo;
    String nombre;
    String descripcion;
    boolean estado;
    float precio;
    int stock;
    String imagen;    
    
    Conexion conexion;

    
    public Producto() {
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
        
    
    public void setProducto(String codigo, String nombre, String descripcion, 
            String estado, String precio, int stock) {        
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = Boolean.parseBoolean(estado);
        this.precio = Float.parseFloat(precio);
        this.stock = stock;        
    }
    
    
    public int registrarProducto() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO producto(\n"
                + "codigo, nombre, descripcion, precio, stock)\n"
                + "VALUES(?, ?, ?, ? ,?)";             

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.descripcion);
            ps.setFloat(4, this.precio);
            ps.setInt(5, this.stock);
            
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
      
    public void eliminarProducto() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM producto\n"
                + "WHERE producto.id = ?";
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
    
    public void modificarProducto() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE producto SET\n" 
                + "codigo = ? ,\n"
                + "nombre = ? ,\n"
                + "descripcion = ? ,\n"
                + "estado = ? ,\n"
                + "precio = ? ,\n"
                + "stock = ? \n"
                + "WHERE producto.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setString(1, this.codigo);
            ps.setString(2, this.nombre);
            ps.setString(3, this.descripcion);
            ps.setBoolean(4, this.estado);
            ps.setFloat(5, this.precio);
            ps.setInt(6, this.stock);
            ps.setInt(7, this.id);
            
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
    public Boolean existeProducto(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "producto.id\n"               
                + "FROM producto\n"                
                + "WHERE producto.id=?";
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
    
    public DefaultTableModel getProducto(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel producto = new DefaultTableModel();
        producto.setColumnIdentifiers(new Object[]{
            "id","codigo", "nombre", "descripcion", "estado", "precio", "stock", "imagen"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "producto.id,\n"
                + "producto.codigo,\n"
                + "producto.nombre,\n"
                + "producto.descripcion,\n"
                + "producto.estado,\n"
                + "producto.precio,\n"
                + "producto.stock,\n"
                + "producto.imagen\n"
                + "FROM producto\n"
                + "WHERE producto.id=?";
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
                producto.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado"),
                    rs.getFloat("precio"),
                    rs.getInt("stock"),
                    rs.getString("imagen")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }
      
    public DefaultTableModel getProductos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "id", "codigo", "nombre", "descripcion", "estado", "precio", "stock", "imagen"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "producto.id,\n"
                + "producto.codigo,\n"
                + "producto.nombre,\n"
                + "producto.descripcion,\n"
                + "producto.estado,\n"
                + "producto.precio,\n"
                + "producto.stock, \n"
                + "producto.imagen\n"
                + "FROM producto\n";                
        
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
                productos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado"),
                    rs.getFloat("precio"),
                    rs.getInt("stock"),
                    rs.getString("imagen"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
    
    
    public DefaultTableModel estadisticaTopProductosMasVendidos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "producto", "cantidad"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT CONCAT(TRIM(producto.nombre)) as producto, COUNT(*) as cantidad\n" +
                    "FROM producto, venta, detalleventa\n" +
                    "WHERE producto.id = detalleventa.idProducto and\n" +
                    "detalleventa.idVenta = venta.id\n" +
                    "GROUP BY producto.nombre, producto.descripcion\n" +
                    "ORDER BY cantidad DESC";                               

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                productos.addRow(new Object[]{                    
                    rs.getString("producto"),                    
                    rs.getInt("cantidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
    
    public DefaultTableModel estadisticaTopProductosMasVendidosDia(String fecha) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "producto", "cantidad"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT TRIM(producto.nombre) as producto, SUM(detalleventa.cantidad) as cantidad\n" +
                    "FROM venta, detalleventa, producto\n" +
                    "WHERE detalleventa.idVenta = venta.id and producto.id = detalleventa.idProducto\n" +
                    "	and venta.fecha = '" + fecha + "'\n" +
                    "GROUP BY producto.nombre";                               

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                productos.addRow(new Object[]{                    
                    rs.getString("producto"),                    
                    rs.getInt("cantidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
    
    public DefaultTableModel estadisticaTopProductosMasVendidosRangoFechas(String fecha1, String fecha2) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "producto", "cantidad"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT TRIM(producto.nombre) as producto, SUM(detalleventa.cantidad) as cantidad\n" +
                    "FROM venta, detalleventa, producto\n" +
                    "WHERE detalleventa.idVenta = venta.id and producto.id = detalleventa.idProducto\n" +
                    "   and venta.fecha >= '" + fecha1 + "' and venta.fecha <= '" + fecha2 + "'" +
                    "GROUP BY producto.nombre";                               

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                productos.addRow(new Object[]{                    
                    rs.getString("producto"),                    
                    rs.getInt("cantidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Producto g = new Producto();
        
        DefaultTableModel dtm = g.estadisticaTopProductosMasVendidos();
        System.out.println("");
        
    }
    
}
