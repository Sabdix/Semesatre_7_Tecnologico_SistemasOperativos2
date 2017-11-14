
package servidor;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author sabdi
 */
public class Servidor {

    
    public static void main(String[] args) throws IOException {
        Server servidor = new Server();
        servidor.start();
    }
    
}
