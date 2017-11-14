package appcliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author marin
 */
public class Conexion extends Thread{
    private final int puerto = 5000;
    private final String host = interfaceCliente.ipp.getText();
    protected String texto ="";
    protected ServerSocket ss;
    protected Socket cs;
    protected DataOutputStream salida;
    protected BufferedReader entrada;
    
    public Conexion(String tipo) throws IOException{
        if(tipo.equalsIgnoreCase("Servidor")){
            ss = new ServerSocket(puerto);
            cs = new Socket();
        }else{
                cs = new Socket(host, puerto);                
        }
    }
}
