/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author fbasc
 */
public class ClientePOP {
    private static final int PORT = 110; // POP
    private static final String SERVIDOR = "mail.tecnoweb.org.bo";
    private static final String USER = "grupo10sa";
    private static final String PASS = "grupo10grupo10";
    //private static final String DESTINATARIO = "grupo01sc@ficct.uagrm.edu.bo";
    //private static final String Subject1 = "prueba correo";


    public static String readMail() {    
        // Estableciendo variables
        String result = null;
        BufferedReader reader;
        DataOutputStream writer;
        String command;

        try {
            // Estableciendo Conexion Socket
            Socket socket = new Socket(SERVIDOR, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());

            if (socket != null && reader != null && writer != null) {
                reader.readLine();
                // Autenticando Usuario
                command = "USER " + USER + "\r\n";
                writer.writeBytes(command);
                reader.readLine();

                command = "PASS " + PASS + "\r\n";
                writer.writeBytes(command);
                reader.readLine();

                // Listar los correos 5
                command = "LIST \r\n";
                writer.writeBytes(command);

                // Revisar si hay correos
                char cant = reader.readLine().charAt(4);              
                getMultiline(reader);
                if (cant != '0') { // Hay mensajes
                    // Leer mensaje
                    command = "RETR 1\n";
                    writer.writeBytes(command);
                    reader.readLine(); 

                    writer.writeBytes(command);
                    result = getMultiline(reader);

                    //System.out.println(result);
                    // Eliminar mensaje despues de leer

                    command = "DELE 1\n";
                    writer.writeBytes(command);
                    reader.readLine();
                }

                command = "QUIT\r\n";
                writer.writeBytes(command);
                reader.readLine();
            }

            // Cerrar Conexion
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ClientePOP p = new ClientePOP();
        String mensaje = p.readMail();
        System.out.println(mensaje);
    }
    
}
