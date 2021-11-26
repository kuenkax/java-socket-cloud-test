package main.java;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String Host = "https://java-socket-test-kkxx.herokuapp.com";
        int Puerto = 6000;//puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new
        DataInputStream(Cliente.getInputStream());

        String cadena = sc.nextLine();
        while (!cadena.equals("*")){
            //Le envio la cadena al servidor
            flujoSalida.writeUTF(cadena);
            //Recibo el mensaje del servidor y lo imprimo
            System.out.println(flujoEntrada.readUTF());

            //Vuelvo a recibir una cadena
            cadena = sc.nextLine();
        }

        //Le envio la cadena al servidor para que cierre (El *)
        flujoSalida.writeUTF(cadena);

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }
}
