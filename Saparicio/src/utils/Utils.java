/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author fbasc
 */
public class Utils {
    
    //
    public static String getDestinatario(String contenido) {
        String destinatario = "";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 5
                    && lines[i].substring(0, 5).toUpperCase().equals("FROM:")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'From: '
            destinatario = lines[index].substring(6);
            lines = destinatario.split(" ");
            if (lines.length == 1) { // Correo del Server
                destinatario = lines[0];
            } else { // Desde otro Servidor de Correo
                destinatario = lines[lines.length - 1];
                destinatario = destinatario.split("<")[1].split(">")[0];
            }
        }
        return destinatario;
    }
    
    //
    public static String getSubjectOrden(String contenido) {
        String orden = "";
        String aux = "";
        // Dividir en lineas
        String[] lines = contenido.split("\n");
        int index = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 8
                    && lines[i].substring(0, 8).toUpperCase().equals("SUBJECT:")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            // Quitar la palabra 'Subject: '
            orden = lines[index].substring(8);
            aux = lines[index + 1].substring(0, 3);
            while (!(aux.equals("To:")) && !(aux.equals("MIM")) && !(aux.equals("Thr"))) {
                orden = orden + lines[index + 1].substring(0);
                index++;
                aux = lines[index+1].substring(0,3);
            }
        }

        return orden;
    }
    
    //
    public static String quitar_formato_mail(String c) {
        int j = 0;
        while (j < c.length()) {
            if (c.charAt(j) == ':') {
                String cad = c.substring(j + 1);
                return cad;
            } else {
                j++;
            }
        }
        return c;
    }
    
    //
    public static String quitarComillas(String texto) {
        int len = texto.length() - 1;
        return texto.substring(1, len);
    }
    
    
    //
    public static String dibujarTablawithHTMLwithoutOpciones(DefaultTableModel tabla) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #355bad;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
//        tableString += "<th> Opciones </th> \n";
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {   
                
                tableString += "<td>";                
                if (tabla.getColumnName(j).equals("imagen")) {
                    tableString += "<img width=\"100\" height=\"100\" src=\"" + String.valueOf(tabla.getValueAt(i, j)) + "\"/>";
                } else {
                    tableString += String.valueOf(tabla.getValueAt(i, j));
                }                                                                
                tableString += "</td> \n";
                
            }
            //<a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=ELIMINAR\"><button class=\"button button3\">ELIMINAR</button></a>

            //tableString += "<td><a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=MODIFICAR\"> <button class=\"button button5\">MODIFICAR</button>  </td> \n";
            tableString += "</tr> \n";
        }

        if (tabla.getRowCount() < 1) {
            return "(Esta tabla no contiene datos)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";

        return tableString;
    }    
    
    //
    public static String dibujarTablawithHTMLVenta(DefaultTableModel tablaVenta, DefaultTableModel tablaDetalles) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #FF4040;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                
                +"<h2>Venta</h2>\n"
                
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        
        for (int i = 0; i < tablaVenta.getColumnCount(); i++) {
            tableString += "<th>" + (tablaVenta.getColumnName(i)) + "</th> \n";
        }
        
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tablaVenta.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tablaVenta.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tablaVenta.getValueAt(i, j)))
                        + "</td> \n";
            }            
            tableString += "</tr> \n";
        }

        if (tablaVenta.getRowCount() < 1) {
            return "(Esta tabla no contiene datos)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                
                +"<br/>"
                
        //*************Detalles de la Venta***************
                +"<h2>Detalles de la Venta</h2>\n"
                
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        
        for (int i = 0; i < tablaDetalles.getColumnCount(); i++) {
            tableString += "<th>" + (tablaDetalles.getColumnName(i)) + "</th> \n";
        }
        
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tablaDetalles.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tablaDetalles.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tablaDetalles.getValueAt(i, j)))
                        + "</td> \n";
            }            
            tableString += "</tr> \n";
        }
        
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"                                                 
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
        
    //
    public static String dibujarTablawithHTMLIngreso(DefaultTableModel tablaIngreso, DefaultTableModel tablaDetalles) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #FF4040;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                
                +"<h2>Ingreso</h2>\n"
                
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        
        for (int i = 0; i < tablaIngreso.getColumnCount(); i++) {
            tableString += "<th>" + (tablaIngreso.getColumnName(i)) + "</th> \n";
        }
        
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tablaIngreso.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tablaIngreso.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tablaIngreso.getValueAt(i, j)))
                        + "</td> \n";
            }            
            tableString += "</tr> \n";
        }

        if (tablaIngreso.getRowCount() < 1) {
            return "(Esta tabla no contiene datos)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                
                +"<br/>"
                
        //*************Detalles del Ingreso***************
                +"<h2>Detalles del Ingreso</h2>\n"
                
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        
        for (int i = 0; i < tablaDetalles.getColumnCount(); i++) {
            tableString += "<th>" + (tablaDetalles.getColumnName(i)) + "</th> \n";
        }
        
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tablaDetalles.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tablaDetalles.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tablaDetalles.getValueAt(i, j)))
                        + "</td> \n";
            }            
            tableString += "</tr> \n";
        }
        
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"                                                 
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
        
    //
    public static String dibujarTablaHTMLForPDF(DefaultTableModel tabla, String titulo) {
        //fecha actual
        Calendar fecha = Calendar.getInstance();
        String dia = Integer.toString(fecha.get(Calendar.DATE));
        String mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
        String annio = Integer.toString(fecha.get(Calendar.YEAR));
        String fechaAct = dia + "/" + mes + "/" + annio;
            
        String tableString = "";
        tableString += "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "	<meta charset=\"UTF-8\">\n" +
                "	<title>Reporte Productos</title>\n" +
                "    <style>              \n" +
                "        div.listadoContenedor{                     \n" +
                "            padding: 25px;\n" +
                "            margin: 15px;;\n" +
                "            border-top: 1px solid black;\n" +
                "            border-bottom: 1px solid black;            \n" +
                "        }        \n" +
                "        div.listadoContenedor tr th {\n" +
                "            position: relative;\n" +
                "            padding: 5px;\n" +
                "            text-align: center;\n" +
                "            background-color: deepskyblue;\n" +
                "        }\n" +
                "        div.listadoContenedor tr td{\n" +
                "            position: relative;\n" +
                "            padding: 5px;           \n" +
                "        }     \n" +
                "        body\n" +
                "        {            \n" +
                "			padding: 15px;\n" +
                "            border:3px solid gray;\n" +
                "			/*background-color: cadetblue;*/\n" +
                "        }        \n" +
                "        .imgLogo\n" +
                "        { \n" +
                "            position: absolute;\n" +
                "            \n" +
                "        }        \n" +
                "        div.reporteContact{\n" +
                "            position: absolute;\n" +
                "            top: 0px;\n" +
                "            left: 10px;           \n" +
                "        }        \n" +
                "		\n" +
                "		.column {\n" +
                "			float: left;\n" +
                "			width: 33.33%;\n" +
                "			text-align: center;\n" +
                "		}\n" +
                "\n" +
                "		/* Clear floats after the columns */\n" +
                "		.row:after {		  \n" +
                "		  display: table;\n" +
                "		  clear: both;\n" +
                "		}\n" +
                "    </style>\n" +
                "</head>" +
                
                "<body>     \n" +
                "	<div class=\"row\">	\n" +
                "		<div class=\"column\" style=\"text-align: left;\">	\n" +
                "			<p>Telefono: <b>75304132</b></p>\n" +
                "			<p>Correo:</p>\n" +
                "			<p>distribuidoraAparicio@gmail.com</p>\n" +
                "		</div>				\n" +
                "		<div class=\"column\">\n" +
                
                "			<h1>" + titulo + "</h1>\n" +
                
                "			<h2>" + fechaAct + "</h2>\n" +
                
                "		</div>		\n" +
                "		<div class=\"column\" style=\"text-align: right;\">\n" +
                "			<img src=\"logo.png\">\n" +
                "		</div>					\n" +
                "	</div>" +
                
                "<div class=\"listadoContenedor\" style=\"clear: both;\">    \n" +
                "		<table>\n" +
                "			<thead>\n" +
                "				<tr>" ;
                                
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
        tableString += "</tr> \n"
                + "</thead> \n"
                
                + "<tbody>";

        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {                
                if (tabla.getColumnName(j).equals("imagen")) {                                        
                    tableString += "<td>"
                        + "<img width=\"100px\" height=\"100px\" src=\"" + 
                            (String.valueOf(tabla.getValueAt(i, j))) + "\">"                        
                        + "</td> \n";
                } else {
                    tableString += "<td>"
                        + (String.valueOf(tabla.getValueAt(i, j)))
                        + "</td> \n";                    
                }                                
            }            
            tableString += "</tr> \n";
        }
        
        tableString += "</tbody> \n";

        if (tabla.getRowCount() < 1) {
            return "(Esta tabla no contiene datos)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
        
    
    //
    public static String dibujarGraficasHTMLDonut(DefaultTableModel tabla, String titulo) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "	<style>\n" +
                "		div {\n" +
                "			text-align: center;\n" +
                "		}\n" +
                "	</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>	\n" +
                "	<div>\n" +
                "		<h1>" + titulo + "</h1>\n" +
                "		<img src=\"https://quickchart.io/chart?c=\n" +
                "		{\n" +
                "			type:'doughnut',\n" +
                "			data:{\n" +
                "				labels:[\n";
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "'" + (tabla.getValueAt(i, 0)) + "'" + ", ";
        }
                                                                
        tableString += "				],\n" +
                "				datasets:[{data:[\n";
                
        int sumaTotal = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += (tabla.getValueAt(i, 1)) + ", ";
            sumaTotal += (int)tabla.getValueAt(i, 1);
        }
                
        tableString += "				]}]\n" +
                "			},\n" +
                "			options:{\n" +
                "				plugins:{\n" +
                "					doughnutlabel:{\n" +
                "						labels:[\n" +
                "							{text:'" +
                                                                                Integer.toString(sumaTotal) +
                "                                                           ',font:{size:20}}\n" +
                "							,{text:'TOTAL'}\n" +
                "						]\n" +
                "					}\n" +
                "				}\n" +
                "			}\n" +
                "		}\n" +
                "		\">\n" +
                "	</div>\n" +
                "</body>\n" +
                "</html>";

        return tableString;
    }                    
    
    //
    public static String dibujarGraficasHTMLBarras(DefaultTableModel tabla, String titulo, String label) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "	<style>\n" +
                "		div {\n" +
                "			text-align: center;\n" +
                "		}\n" +
                "	</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>	\n" +
                "	<div>\n" +
                "		<h1>" + titulo + "</h1>\n" +
                "		<img src=\"https://quickchart.io/chart?c=\n" +
                "		{\n" +
                "			type:'bar',\n" +
                "			data:{\n" +
                "				labels:[\n";
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "'" + (tabla.getValueAt(i, 0)) + "'" + ", ";
        }
                                                                
        tableString += "				],\n" +
                "				datasets:[{"
                                                            + "label:'" + label + "', "
                                                            + "data:[\n";
                
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += (tabla.getValueAt(i, 1)) + ", ";
        }
                
        tableString += "				]}]\n" +
                "			}\n" +                
                "		}\n" +
                "		\">\n" +
                "	</div>\n" +
                "</body>\n" +
                "</html>";

        return tableString;
    }
    
    //
    public static String dibujarGraficasHTMLLineasYTorta(DefaultTableModel tabla1, DefaultTableModel tabla2, String titulo) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "	<style>\n" +
                "		body {\n" +
                "			text-align: center;\n" +
                "		}\n" +
                "	</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>	\n" +
                "       <div>\n" +
                "		<h1>" + titulo + "</h1>\n" +
                "		<img src=\"https://quickchart.io/chart?c=\n" +
                "		{\n" +
                "                   type:'line',\n" +
                "                   data:{\n" +
                "                       labels:[\n";
        
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            tableString += "'" + mesToNombreMes(Integer.parseInt((String)tabla1.getValueAt(i, 0))) + "'" + ", ";
        }
                                                                
        tableString += "                        ],\n" +
                "			datasets:["
                + "                                 {"
                + "                                     label:'" + "Ingresos" + "', "
                + "                                     data:[\n";
                
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            tableString += tabla1.getValueAt(i, 1) + ", ";
        }
                
        tableString += "                                ],"
                + "                                     fill:false,"
                + "                                     borderColor:'green'"
                + "                                 },"
                
                + "                                 {"
                + "                                     label:'" + "Gastos" + "', "
                + "                                     data:[\n";
                
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            tableString += tabla1.getValueAt(i, 2) + ", ";
        }
                
        tableString += "                                    ],"
                + "                                     fill:false,"
                + "                                     borderColor:'red'"
                + "                                 }"
                
                + "                             ]\n" +
                "                       }\n" +                
                "               }\n" +
                "           \">\n" +
                "	</div>\n" +
                
                "       <br/>\n" +
                
                "       <div>" +                
                "           <img src=\"https://quickchart.io/chart?c=\n" +
                "           {\n" +
                "               type:'pie',\n" +
                "               data:{\n" +
                "                   labels:[\n" +
                
                "                       'Ingreso', 'Gasto', 'Utilidad'";
                
        tableString += "                   ], \n" +
                "                   datasets:[\n" +
                "                       {\n" +
                "                           data:[\n";
        
        for (int i = 0; i < tabla2.getColumnCount(); i++) {
            tableString += tabla2.getValueAt(0, i) + ", ";
        }   
                                
        tableString += "                           ]\n" +
                "                       }\n" +
                "                   ]\n" +
                "               }\n" +
                "           }\">" +                
                "       </div>" + 
                                
                "</body>\n" +
                "</html>";

        return tableString;
    }
      
    
    //
    public static String md5(String input) 
    { 
        try {   
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
    
    //
    public static String mesToNombreMes(int mes){
        switch(mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Deciembre";
            default:
                return "Mes";                
        }
    }
    
    //
    public static String fechaActual(){
        String fechaActual = "";
        try {
            //fecha actual
            Calendar calendario = Calendar.getInstance();
            String dia = Integer.toString(calendario.get(Calendar.DATE));
            String mes = Integer.toString(calendario.get(Calendar.MONTH) + 1); //los meses empiezan en 0..11
            String annio = Integer.toString(calendario.get(Calendar.YEAR));
            fechaActual += annio + "-" + mes + "-" + dia;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fechaActual;
    }
        
    //
    public static String horaActual(){
        String horaActual = "";
        try {
            //hora actual
            Calendar calendario = Calendar.getInstance();
            String hora = Integer.toString(calendario.get(Calendar.HOUR));
            String minuto = Integer.toString(calendario.get(Calendar.MINUTE));
            String segundo = Integer.toString(calendario.get(Calendar.SECOND));
            horaActual += hora + ":" + minuto + ":" + segundo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horaActual;
    }
    
    //dd-MM-yyyy to yyyy-MM-dd
    public static String ddMMyyyyToyyyyMMddDate(String fecha) {   
        String fechaFormateada = "";       
        try {            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = formatter.parse(fecha);                                  
            fechaFormateada  = new SimpleDateFormat("yyyy-MM-dd").format(date);
            //System.out.println(format);                        
        } catch (Exception e) {
            e.printStackTrace();
        }                      
                    
        return fechaFormateada;
    }
            
    
    
}
