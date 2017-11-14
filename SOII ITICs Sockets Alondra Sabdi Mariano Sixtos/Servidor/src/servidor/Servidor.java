
package servidor;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author sabdi
 */
public class Servidor {

    
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Server servidor = new Server();
        servidor.start();
        servidor.sendMessage(teclado.nextLine());
    }
    
}
