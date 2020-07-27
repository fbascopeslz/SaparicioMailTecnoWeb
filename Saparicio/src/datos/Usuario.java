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
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fbasc
 */
public class Usuario {
    
    int id;
    String ci;
    String nombres;
    String paterno;
    String materno;
    Date fechaNac;
    String telefono;
    String email;
    String direccion;
    String usuario;
    String password;    
    boolean estado;
    int idRol;   
    
    Conexion conexion;

    
    public Usuario() {
        this.conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }               
        
    
    public void setUsuario(String ci, String nombres, String paterno, 
            String materno, String fechaNac, String telefono,
            String email, String direccion, String usuario,
            String password, String estado, int idRol) throws ParseException {        
        this.ci = ci;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.fechaNac = Date.valueOf(fechaNac);
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.usuario = usuario;
        this.password = password;        
        this.estado = Boolean.parseBoolean(estado);
        this.idRol = idRol;
    }
    
    
    public int registrarUsuario() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO usuario(\n"
                + "ci, , nombres, paterno, materno, fechaNac, telefono, email, direccion, usuario, password, idRol)\n"
                + "VALUES(?, ?, ?, ? ,?, ?, ?, ?, ? ,?, ?)";             

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
            ps.setString(6, this.telefono);
            ps.setString(7, this.email);
            ps.setString(8, this.direccion);
            ps.setString(9, this.usuario);
            ps.setString(10, this.password);
            ps.setInt(11, this.idRol);
            
            
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
      
    public void eliminarUsuario() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM usuario\n"
                + "WHERE usuario.id = ?";
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
    
    public void modificarUsuario() {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE usuario SET\n" 
                + "ci = ? ,\n"
                + "nombres = ? ,\n"
                + "paterno = ? ,\n"
                + "materno = ? ,\n"
                + "fechaNac = ? ,\n"
                + "telefono = ? ,\n"
                + "email = ? ,\n"
                + "direccion = ? ,\n"
                + "usuario = ? ,\n"
                + "password = ? ,\n"
                + "estado = ? ,\n"
                + "idRol = ? \n"
                + "WHERE usuario.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setString(1, this.ci);
            ps.setString(2, this.nombres);
            ps.setString(3, this.paterno);
            ps.setString(4, this.materno);
            ps.setDate(5, this.fechaNac);
            ps.setString(6, this.telefono);
            ps.setString(7, this.email);
            ps.setString(8, this.direccion);
            ps.setString(9, this.usuario);
            ps.setString(10, this.password);            
            ps.setBoolean(11, this.estado);
            ps.setInt(12, this.idRol);
            
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
    public Boolean existeUsuario(int id) {
        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "usuario.id\n"               
                + "FROM usuario\n"                
                + "WHERE usuario.id=?";
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
    
    public DefaultTableModel getUsuario(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel usuario = new DefaultTableModel();
        usuario.setColumnIdentifiers(new Object[]{
            "id", "ci", "nombres", "paterno", "materno", "fechaNac", "telefono",
            "email", "direccion", "usuario", "password", "estado", "idRol"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "usuario.id,\n"
                + "usuario.ci,\n"
                + "usuario.nombres,\n"
                + "usuario.paterno,\n"
                + "usuario.materno,\n"
                + "usuario.fechaNac,\n"
                + "usuario.telefono,\n"
                + "usuario.email,\n"
                + "usuario.direccion,\n"
                + "usuario.usuario,\n"
                + "usuario.password,\n"
                + "usuario.estado,\n"
                + "usuario.idRol\n"
                + "FROM usuario\n"
                + "WHERE usuario.id=?";
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
                usuario.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("ci"),
                    rs.getString("nombres"),
                    rs.getString("paterno"),
                    rs.getString("materno"),
                    rs.getString("fechaNac"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("usuario"),
                    rs.getString("password"),
                    rs.getBoolean("estado"),                    
                    rs.getInt("idRol")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuario;
    }
      
    public DefaultTableModel getUsuarios() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel usuarios = new DefaultTableModel();
        usuarios.setColumnIdentifiers(new Object[]{
            "id", "ci", "nombres", "paterno", "materno", "fechaNac", "telefono",
            "email", "direccion", "usuario", "password", "estado", "idRol"
        });

        // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "usuario.id,\n"
                + "usuario.ci,\n"
                + "usuario.nombres,\n"
                + "usuario.paterno,\n"
                + "usuario.materno,\n"
                + "usuario.fechaNac,\n"
                + "usuario.telefono,\n"
                + "usuario.email,\n"
                + "usuario.direccion,\n"
                + "usuario.usuario,\n"
                + "usuario.password,\n"
                + "usuario.estado,\n"
                + "usuario.idRol\n"
                + "FROM usuario";                
        
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
                usuarios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("ci"),
                    rs.getString("nombres"),
                    rs.getString("paterno"),
                    rs.getString("materno"),
                    rs.getString("fechaNac"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion"),
                    rs.getString("usuario"),
                    rs.getString("password"),
                    rs.getBoolean("estado"),                    
                    rs.getInt("idRol")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuarios;
    }
            
    
    public static void main(String[] args) {
        // TODO code application logic here
            Usuario u = new Usuario();
            //g.setGrupo(1,"Empleados");
            
            DefaultTableModel dtm = u.getUsuarios();
            System.out.println("");            
            //g.setId(4);
            //g.eliminarGrupo();
            //int i = g.registrarGrupo();
            //System.out.println(i);
    }
    
}
