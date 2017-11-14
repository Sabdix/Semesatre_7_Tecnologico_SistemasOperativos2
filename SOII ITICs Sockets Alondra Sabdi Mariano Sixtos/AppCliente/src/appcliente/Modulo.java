package appcliente;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marin
 */
public class Modulo extends Conexion{
    
    public Modulo() throws IOException{
        super(interfaceCliente.opciones.getSelectedItem().toString());        
    }      
    String tiempo;
    @Override
    public void run(){
        try {
            if (interfaceCliente.opciones.getSelectedItem().toString().equalsIgnoreCase("Servidor")) {
                cs = ss.accept();
            }
            salida = new  DataOutputStream(cs.getOutputStream());                        
            entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            
        while(true){
            Calendar calendario = new GregorianCalendar();
            int h=calendario.get(Calendar.HOUR_OF_DAY);
            int m = calendario.get(Calendar.MINUTE);
            int s= calendario.get(Calendar.SECOND);
            tiempo=h+":"+m+":"+s+" ";
            if((texto = entrada.readLine())!=null){
                interfaceCliente.jArea.setText(interfaceCliente.jArea.getText()+texto+" "+(tiempo)+"\n");
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(Modulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void sendMsg(String s) throws IOException{
        salida.writeUTF(s+"\n");
    }
    public void closeConexion() throws IOException{        
        if(interfaceCliente.opciones.getSelectedItem().toString().equalsIgnoreCase("Servidor")){            
            ss.close();
        }else{            
            cs.close();
        }
    }
    
}
