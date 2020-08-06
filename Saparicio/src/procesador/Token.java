/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

/**
 *
 * @author fbasc
 */
public class Token {
    
    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int FUNC = 2; // Alguna Funcion
    public static final int GB = 3; //Guion Bajo
    public static final int LLA = 4; // Llave Abierta
    public static final int LLC = 5; // Llave Cerrada
    public static final int SEPARADOR = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int TRUE = 9;
    public static final int FALSE = 10;
    public static final int HELP = 11;
    public static final int BARRA = 12;// |

//Funciones
    public static final int REGISTRARPRODUCTO = 100;
    public static final int MODIFICARPRODUCTO = 101;
    public static final int ELIMINARPRODUCTO = 102;
    public static final int OBTENERPRODUCTO = 103;
    public static final int MOSTRARPRODUCTOS = 104;
    
    public static final int REGISTRARPROVEEDOR = 105;
    public static final int MODIFICARPROVEEDOR = 106;
    public static final int ELIMINARPROVEEDOR = 107;
    public static final int OBTENERPROVEEDOR = 108;
    public static final int MOSTRARPROVEEDORES = 109;
    
    public static final int REGISTRARUSUARIO = 110;
    public static final int MODIFICARUSUARIO = 111;
    public static final int ELIMINARUSUARIO = 112;
    public static final int OBTENERUSUARIO = 113;
    public static final int MOSTRARUSUARIOS = 114;
    
    public static final int REGISTRARCLIENTE = 115;
    public static final int MODIFICARCLIENTE = 116;
    public static final int ELIMINARCLIENTE = 117;
    public static final int OBTENERCLIENTE = 118;
    public static final int MOSTRARCLIENTES = 119;
    
    public static final int REGISTRARVENTA = 120;
    public static final int OBTENERVENTAYDETALLES = 121;
    public static final int MOSTRARVENTAS = 122;
    
    public static final int REGISTRARINGRESO = 123;
    public static final int OBTENERINGRESOYDETALLES = 124;
    public static final int MOSTRARINGRESOS = 125;
    
    public static final int REPORTEPRODUCTOS = 126;
    public static final int REPORTEVENTAS = 127;
    public static final int REPORTEINGRESOS = 128;    
    
    public static final int ESTADISTICASPRODUCTOSMASVENDIDOS = 129;
    public static final int ESTADISTICASCLIENTESFIELES = 130;
    public static final int ESTADISTICASPROVEEDORESMASSOLICITADOS = 131;
    public static final int ESTADISTICASFINANCIERAS = 132;
        
    
    private int nombre;
    private int atributo;
    private String toStr;
    //private double numeroFlotante;

    public Token() {
    }

    public Token(int nombre, int atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getAtributo() {
        return atributo;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }

    public String getToStr() {
        return toStr;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }
    
}
