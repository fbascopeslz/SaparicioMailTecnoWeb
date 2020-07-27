/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fbasc
 */
public class Conexion {
    
    private Connection connection = null;
    private String host;
    private String user;
    private String password;
    private static Conexion Conexion = null;

    public Conexion() {
        this.host = DB.DB_HOST;
        this.user = DB.DB_USER;
        this.password = DB.DB_PASSWORD;
    }

    public static Conexion getInstancia() {
        if (Conexion == null) {
            Conexion = new Conexion();
        }
        return Conexion;
    }

    public Connection getConexion() {
        return this.connection;
    }

    public void abrirConexion() {
        String db1 = DB.DB_NAME;
        
        String url_db = "jdbc:postgresql://" + this.host + ":5432/" + db1;

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url_db, this.user, this.password);
            System.out.println(this.connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
