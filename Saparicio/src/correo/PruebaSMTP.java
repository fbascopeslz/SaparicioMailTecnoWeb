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
import java.net.UnknownHostException;

/**
 *
 * @author fbasc
 */
public class PruebaSMTP {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String servidor="mail.ficct.uagrm.edu.bo";
        //String servidor="172.20.172.254";
        String servidor="mail.tecnoweb.org.bo";
        //String user_receptor="grupo01sa@ficct.uagrm.edu.bo";
        String user_emisor="grupo10sa@tecnoweb.org.bo";        
        //String user_emisor="evansbv@gmail.com";
        String user_receptor ="fbascopeslz12345@gmail.com";
        String line;
        String comando="";
        int puerto=25;

        try{
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket=new Socket(servidor,puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream (socket.getOutputStream());

            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if( socket != null && entrada != null && salida != null )
            {
                System.out.println("S : "+entrada.readLine());

                comando="HELO "+servidor+" \r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+getMultiline(entrada));

                comando="MAIL FROM : "+user_emisor+" \r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+entrada.readLine());

                comando="RCPT TO : "+user_receptor+" \r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+entrada.readLine());

                comando="DATA\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+getMultiline(entrada));

                comando="SUBJECT: VIVA LA FICCT123\r\n"+".\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+entrada.readLine());

                comando="QUIT\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );
                System.out.println("S : "+entrada.readLine());
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.out.println(" S : No se pudo conectar con el servidor indicado");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //Permite Leer multiples lineas del Protocolo SMTP
    static protected String getMultiline(BufferedReader in) throws IOException{
        String lines = "";
        while (true){
            String line = in.readLine();
            if (line == null){
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3)==' '){
                lines=lines+"\n"+line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines=lines+"\n"+line;
        }
        return lines;
    }

}
