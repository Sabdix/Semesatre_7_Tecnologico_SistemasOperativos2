package servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabdi
 */
public class Server extends Conexion {
    
    public Server() throws IOException {
        super("Servidor");
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Esperando Conexion"); //Imprimir Esperando conexion en donde se desplegaran los mensajes
            socket = serverSocket.accept();
            System.out.println("Sesión Establecida"); //Imprimir Esperando conexion en donde se desplegaran los mensajes
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                if ((texto = entrada.readLine()) != null)
                    System.out.println(texto);
            }
        }   catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public void sendMessage(String mensaje) throws IOException {
        salida.writeUTF(mensaje+"\n");
    }
    
    public void closeConection() throws IOException {
        serverSocket.close();
        socket.close();
        salida.close();
        entrada.close();
    }
    
    
}
