
package servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sabdi
 */
public class Conexion extends Thread {
    private final int puerto = 5000;
    private final String ip = "localhost";
    protected String texto;
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected DataOutputStream salida;
    protected BufferedReader entrada;
    
    public Conexion (String tipo) throws IOException {
        if (tipo.equalsIgnoreCase("Servidor")) {
            serverSocket = new ServerSocket(puerto);
            socket = new Socket();
        } else {
            socket = new Socket(ip, puerto);
        }
    }
}
