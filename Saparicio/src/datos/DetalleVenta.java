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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class DetalleVenta {
            
    int idVenta;
    ArrayList<DetVenta> listaDetVen;
    
    Conexion conexion;
    
    public DetalleVenta() {
        this.conexion = Conexion.getInstancia();
        this.listaDetVen = new ArrayList();
    }

    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    
    public int registrarDetalleVenta(String detalleVenta) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        
        
        //Procesar la cadena para los DetallesVenta
        this.procesarDetalleVenta(detalleVenta);
        

        try {
                 
            
            // Preparo la consulta
            String sql = "INSERT INTO detalleventa(\n"
                    + "cantidad, precioVenta, descuento, idVenta, idProducto) VALUES\n";
                    
            if (listaDetVen.size() == 1) {
                sql = sql + "(?, ?, ?, ? ,?);";
            } else {
                for (int i = 0; i < listaDetVen.size(); i++) {
                    if (listaDetVen.size() == i + 1) {
                        sql = sql + "(?, ?, ?, ? ,?);";
                    } else {
                        sql = sql + "(?, ?, ?, ? ,?), ";
                    }                    
                }
            }            
        
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            
            int c = 1;
            for (int i = 0; i < listaDetVen.size(); i++) {
                ps.setInt(c, this.listaDetVen.get(i).cantidad);
                c++;
                ps.setFloat(c, this.listaDetVen.get(i).precioVenta);
                c++;
                ps.setFloat(c, this.listaDetVen.get(i).descuento);
                c++;
                ps.setInt(c, this.listaDetVen.get(i).idVenta);
                c++;
                ps.setInt(c, this.listaDetVen.get(i).idProducto);
                c++;
            }     
            
            
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
        
    //Ejp: [{ 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0.5}]
    public void procesarDetalleVenta(String cadena) {
        String cad = "";
        cad = cadena.replace("[", "");
        cad = cad.replace("]", "");
        
        String[] elementos = null;
        elementos = cad.split("\\|");
        
        for (int i = 0; i < elementos.length; i++) {
            String ele = elementos[i].replace("{", "");
            ele = ele.replace("}", "");
            String[] elemento = ele.split(",");
            
            DetVenta detVen = new DetVenta(
                    Integer.parseInt(elemento[1].trim()),
                    Float.parseFloat(elemento[2].trim()),
                    Float.parseFloat(elemento[3].trim()),
                    this.idVenta,
                    Integer.parseInt(elemento[0].trim())                   
            );
            listaDetVen.add(detVen);
        }                        
    }
    
    
    public DefaultTableModel getDetallesVentaId(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detallesventa = new DefaultTableModel();
        detallesventa.setColumnIdentifiers(new Object[]{
            "id", "nombre", "cantidad", "precioVenta", "descuento"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT detalleventa.id, \n"
                + "producto.nombre, \n"
                + "detalleventa.cantidad, \n"
                + "detalleventa.precioVenta, \n"
                + "detalleventa.descuento \n"
                + "FROM venta, detalleventa, producto \n"
                + "WHERE venta.id = detalleventa.idVenta and "
                + "producto.id = detalleventa.idProducto and "
                + "venta.id = ?";
        
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
                detallesventa.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),                    
                    rs.getInt("cantidad"),
                    rs.getFloat("precioVenta"),
                    rs.getFloat("descuento")                    
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detallesventa;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        DetalleVenta dv = new DetalleVenta();
        dv.idVenta = 1;
        dv.listaDetVen = new ArrayList();
        //dv.procesarDetalleVenta("[{ 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0.5}]");
        dv.registrarDetalleVenta("[{ 3, 15, 12.35, 0 } | { 3, 15, 12.35, 0 } | { 3, 15, 12.35, 0.5}]");
        System.out.println("");
            
    }
    
    
    //inner class
    class DetVenta {
        int cantidad;
        float precioVenta;
        float descuento;
        int idVenta;
        int idProducto;
        
        public DetVenta(int cantidad, float precioVenta, float descuento, 
                int idVenta, int idProducto) {
            this.cantidad = cantidad;
            this.precioVenta = precioVenta;
            this.descuento = descuento;
            this.idVenta = idVenta;
            this.idProducto = idProducto;
        }    
    }
        
}
