package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class Server {
        public static void main(String[] args) throws IOException {
                int numeroPuerto = 6000;// Puerto

                ServerSocket servidor = new ServerSocket(numeroPuerto); //INICIO EL SERVER
                Socket clienteConectado = null;
                System.out.println("Esperando los clientes.....");

                //Espero la conexión de mi cliente
                clienteConectado = servidor.accept();   //RECIBO LA CONEXION

                //Creo el flujo de entrada
                InputStream entrada = null;
                entrada = clienteConectado.getInputStream();
                DataInputStream flujoEntrada = new DataInputStream(entrada);
                String resCliente = flujoEntrada.readUTF(); //Guardo la primera entrada del cliente

                //CREO UN FLUJO DE SALIDA
                OutputStream salida = null;
                salida = clienteConectado.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(salida);

                while (!resCliente.equals("*")){
                        flujoSalida.writeUTF("Tu string es de la longitud: " + resCliente.length());    //Le mando el mensaje al cliente
                        resCliente = flujoEntrada.readUTF(); //Me espero hasta la próxima entrada del cliente
                }

                clienteConectado.close();

                //Cierro los flujos
                flujoSalida.close();
                salida.close();
                flujoEntrada.close();
                entrada.close();

                //Cierro el server
                servidor.close();
        }
}
