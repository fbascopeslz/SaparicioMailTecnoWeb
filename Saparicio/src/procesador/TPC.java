/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author fbasc
 */
public class TPC {
    
    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            
            "REGISTRARPRODUCTO",
            "MODIFICARPRODUCTO",
            "ELIMINARPRODUCTO",
            "OBTENERPRODUCTO",
            "MOSTRARPRODUCTOS",
            
            "REGISTRARPROVEEDOR",
            "MODIFICARPROVEEDOR",
            "ELIMINARPROVEEDOR",
            "OBTENERPROVEEDOR",
            "MOSTRARPROVEEDORES",
            
            "REGISTRARUSUARIO",
            "MODIFICARUSUARIO",
            "ELIMINARUSUARIO",
            "OBTENERUSUARIO",
            "MOSTRARUSUARIOS",
            
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "ELIMINARCLIENTE",
            "OBTENERCLIENTE",
            "MOSTRARCLIENTES",
            
            "REGISTRARVENTA",
            "OBTENERVENTAYDETALLES",
            "MOSTRARVENTAS",
            
            "REGISTRARINGRESO",
            "OBTENERINGRESOYDETALLES",
            "MOSTRARINGRESOS",
            
            "REPORTEPRODUCTOS",
            "REPORTEVENTAS",
            "REPORTEINGRESOS",
            
            "ESTADISTICASPRODUCTOSMASVENDIDOS",
            "ESTADISTICASCLIENTESFIELES",
            "ESTADISTICASPROVEEDORESMASSOLICITADOS",
            "ESTADISTICASFINANCIERAS"
            
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),

            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO"),
            new Token(Token.FUNC, Token.OBTENERPRODUCTO, "OBTENERPRODUCTO"),
            new Token(Token.FUNC, Token.MOSTRARPRODUCTOS, "MOSTRARPRODUCTOS"),
            
            new Token(Token.FUNC, Token.REGISTRARPROVEEDOR, "REGISTRARPROVEEDOR"),
            new Token(Token.FUNC, Token.MODIFICARPROVEEDOR, "MODIFICARPROVEEDOR"),
            new Token(Token.FUNC, Token.ELIMINARPROVEEDOR, "ELIMINARPROVEEDOR"),
            new Token(Token.FUNC, Token.OBTENERPROVEEDOR, "OBTENERPROVEEDOR"),
            new Token(Token.FUNC, Token.MOSTRARPROVEEDORES, "MOSTRARPROVEEDORES"),
            
            new Token(Token.FUNC, Token.REGISTRARUSUARIO, "REGISTRARUSUARIO"),
            new Token(Token.FUNC, Token.MODIFICARUSUARIO, "MODIFICARUSUARIO"),
            new Token(Token.FUNC, Token.ELIMINARUSUARIO, "ELIMINARUSUARIO"),
            new Token(Token.FUNC, Token.OBTENERUSUARIO, "OBTENERUSUARIO"),
            new Token(Token.FUNC, Token.MOSTRARUSUARIOS, "MOSTRARUSUARIOS"),
            
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.MOSTRARCLIENTES, "MOSTRARCLIENTES"),
            
            new Token(Token.FUNC, Token.REGISTRARVENTA, "REGISTRARVENTA"),           
            new Token(Token.FUNC, Token.OBTENERVENTAYDETALLES, "OBTENERVENTAYDETALLES"),
            new Token(Token.FUNC, Token.MOSTRARVENTAS, "MOSTRARVENTAS"),
            
            new Token(Token.FUNC, Token.REGISTRARINGRESO, "REGISTRARINGRESO"),           
            new Token(Token.FUNC, Token.OBTENERINGRESOYDETALLES, "OBTENERINGRESOYDETALLES"),
            new Token(Token.FUNC, Token.MOSTRARINGRESOS, "MOSTRARINGRESOS"),
            
            new Token(Token.FUNC, Token.REPORTEPRODUCTOS, "REPORTEPRODUCTOS"),
            new Token(Token.FUNC, Token.REPORTEVENTAS, "REPORTEVENTAS"),
            new Token(Token.FUNC, Token.REPORTEINGRESOS, "REPORTEINGRESOS"),
                        
            new Token(Token.FUNC, Token.ESTADISTICASPRODUCTOSMASVENDIDOS, "ESTADISTICASPRODUCTOSMASVENDIDOS"),
            new Token(Token.FUNC, Token.ESTADISTICASCLIENTESFIELES, "ESTADISTICASCLIENTESFIELES"),
            new Token(Token.FUNC, Token.ESTADISTICASPROVEEDORESMASSOLICITADOS, "ESTADISTICASPROVEEDORESMASSOLICITADOS"),
            new Token(Token.FUNC, Token.ESTADISTICASFINANCIERAS, "ESTADISTICASFINANCIERAS")
            
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            //System.out.println("actual: " + i + "   tamaño:"+  lexemas.size() + "cant:"+ tokens.size());
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                //System.out.println("el token es: " + tokens.get(i));
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
    
}
