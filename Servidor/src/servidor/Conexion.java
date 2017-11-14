
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
    
    public Conexion () throws IOException {
            serverSocket = new ServerSocket(puerto);
    }
}
