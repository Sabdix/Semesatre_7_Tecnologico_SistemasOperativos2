/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcliente;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author marin
 */
public class AppCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner t = new Scanner(System.in);
        Modulo cliente = new Modulo();
        System.out.println("Iniciando Cliente\n");
        //cliente.startCliente();
        cliente.start();
        cliente.sendMsg(t.nextLine());
        //cliente.closeConexion();
        
    }
    
}
