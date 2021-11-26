/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
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
