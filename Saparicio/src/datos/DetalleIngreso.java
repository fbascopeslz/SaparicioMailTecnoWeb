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
public class DetalleIngreso {
    
    int idIngreso;
    ArrayList<DetIngreso> listaDetIng;
    
    Conexion conexion;
    
    public DetalleIngreso() {
        this.conexion = Conexion.getInstancia();
        this.listaDetIng = new ArrayList();
    }

    
    public int getidIngreso() {
        return idIngreso;
    }

    public void setidIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }
    
    
    public int registrarDetalleIngreso(String detalleIngreso) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        
        
        //Procesar la cadena para los DetallesVenta
        this.procesarDetalleIngreso(detalleIngreso);
        

        try {
                             
            // Preparo la consulta
            String sql = "INSERT INTO detalleingreso(\n"
                    + "cantidad, precioCompra, idIngreso, idProducto) VALUES\n";
                    
            if (listaDetIng.size() == 1) {
                sql = sql + "(?, ?, ?, ?);";
            } else {
                for (int i = 0; i < listaDetIng.size(); i++) {
                    if (listaDetIng.size() == i + 1) {
                        sql = sql + "(?, ?, ?, ?);";
                    } else {
                        sql = sql + "(?, ?, ?, ?), ";
                    }                    
                }
            }            
        
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            
            int c = 1;
            for (int i = 0; i < listaDetIng.size(); i++) {
                ps.setInt(c, this.listaDetIng.get(i).cantidad);
                c++;
                ps.setFloat(c, this.listaDetIng.get(i).precioCompra);
                c++;                
                ps.setInt(c, this.listaDetIng.get(i).idIngreso);
                c++;
                ps.setInt(c, this.listaDetIng.get(i).idProducto);
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
        
    //Ejp: [{ 3, 15, 12.35, 0.532 } | { 30, 15, 12.35, 1.5 }]
    public void procesarDetalleIngreso(String cadena) {
        String cad = "";
        cad = cadena.replace("[", "");
        cad = cad.replace("]", "");
        
        String[] elementos = null;
        elementos = cad.split("\\|");
        
        for (int i = 0; i < elementos.length; i++) {
            String ele = elementos[i].replace("{", "");
            ele = ele.replace("}", "");
            String[] elemento = ele.split(",");
            
            DetIngreso detVen = new DetIngreso(
                    Integer.parseInt(elemento[1].trim()),
                    Float.parseFloat(elemento[2].trim()),
                    this.idIngreso,
                    Integer.parseInt(elemento[0].trim())                   
            );
            listaDetIng.add(detVen);
        }                        
    }
    
    
    public DefaultTableModel getDetallesIngresoId(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detallesventa = new DefaultTableModel();
        detallesventa.setColumnIdentifiers(new Object[]{
            "id", "nombre", "cantidad", "precioCompra"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT detalleingreso.id, \n"
                + "producto.nombre, \n"
                + "detalleingreso.cantidad, \n"
                + "detalleingreso.precioCompra \n"
                + "FROM ingreso, detalleingreso, producto \n"
                + "WHERE ingreso.id = detalleingreso.idIngreso and "
                + "producto.id = detalleIngreso.idProducto and "
                + "ingreso.id = ?";
        
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
                    rs.getFloat("precioCompra")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detallesventa;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        DetalleIngreso dv = new DetalleIngreso();
        dv.idIngreso = 1;
        dv.listaDetIng = new ArrayList();
        //dv.procesarDetalleVenta("[{ 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0 } | { 1, 15, 12.35, 0.5}]");
        dv.registrarDetalleIngreso("[{ 3, 15, 12.35, 0 } | { 3, 15, 12.35, 0 } | { 3, 15, 12.35, 0.5}]");
        System.out.println("");
            
    }
    
    
    //inner class
    class DetIngreso {
        int cantidad;
        float precioCompra;
        int idIngreso;
        int idProducto;
        
        public DetIngreso(int cantidad, float precioCompra, int idIngreso, int idProducto) {
            this.cantidad = cantidad;
            this.precioCompra = precioCompra;
            this.idIngreso = idIngreso;
            this.idProducto = idProducto;
        }    
    }
    
}
