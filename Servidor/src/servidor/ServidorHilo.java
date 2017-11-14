
package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabdi
 */
public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSession;
    
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSession = id;
        
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        String accion;
        while(true) {
            try {
                if ((accion = dis.readUTF()) != null) {
                    switch(accion) {
                        case "pruebaConexion":
                            sendMessage("Probando Conexion.... [OK]");
                            System.out.println("Se envio prueba de conexion");
                            break;
                        case "closeConection":
                            closeConection();
                            System.out.println("Se cerro conexion");
                            this.stop();
                            break;
                        case "guardarArchivo":
                            saveFile();
                            break;
                        case "enviarArchivo":
                            sendFile();
                            break;
                        //Se agregan mas cases    
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Enviar mensaje de comprobación de la conexión
    public void sendMessage(String mensaje) throws IOException {
        dos.writeUTF(mensaje+"\n");
    }
    
    //Cerrar la conexion
    public void closeConection() throws IOException {
        dis.close();
        dos.close();
        socket.close();
    }
    
    //Recibir archivos del cliente
    public void saveFile() throws IOException {
        String nombre = dis.readUTF();
        int tamaño = dis.readInt();
        System.out.println("Recibiendo Archivo....");
        FileOutputStream fos = new FileOutputStream("C:/pruebas/"+nombre);
        BufferedOutputStream out = new BufferedOutputStream(fos);
        BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
        
        byte[] buffer = new byte[tamaño];
        
        for(int i=0; i<buffer.length; i++) {
            buffer[i] = (byte)in.read();
        }
        
        out.write(buffer);
        out.flush();
        out.close();
        
        System.out.println("Archivo Recibido "+nombre);
    }
    
    //Para enviar archivos al cliente
    public void sendFile() throws IOException {
        String ruta = dis.readUTF();
        File archivo = new File(ruta);
        
        if (archivo.exists()) {
            int tamañoArchivo = ( int )archivo.length();
            System.out.println( "Enviando Archivo: "+archivo.getName() );
            dos.writeUTF( archivo.getName() );
            dos.writeInt( tamañoArchivo );
            FileInputStream fis = new FileInputStream( ruta );
            BufferedInputStream bis = new BufferedInputStream( fis );
            byte[] buffer = new byte[ tamañoArchivo ];
            bis.read( buffer ); 
            BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream() );
            for( int i = 0; i < buffer.length; i++ )
            {
                bos.write( buffer[ i ] ); 
            }
            System.out.println( "Archivo Enviado: "+archivo.getName() );
            bis.close();
            bos.close();
        } else {
            dos.writeUTF("No se encuentra el archivo");
        }
    }
}
