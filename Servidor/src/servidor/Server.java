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
        super();
    }
    
    @Override
    public void run() {
        try {
            int idSession = 0;
            while(true) {
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva conexion entrnte" + socket);
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
