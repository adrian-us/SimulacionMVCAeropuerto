package controlador;

import modelo.OperationModel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServidor extends Thread{
    private final int id;
    private final DataInputStream reader;
    private final DataOutputStream writer;
    private static final boolean running = true;
    Servidor servidor;

    public ThreadServidor(final Socket socketRef,
                          final Servidor servidor,
                          final int id) throws IOException {
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.servidor = servidor;
        this.id = id;
    }


    @Override
    public void run(){
        byte instruccionID;
        while (running){
            try {
                instruccionID = reader.readByte(); /* Esperar hasta que reciba un Byte */
                switch (instruccionID) {
                    case 0 -> /* Tipo 'A' : 0 : Vuelo entrante listo para aterrizar */{
                        System.out.println("=====================================");
                        System.out.println("Vuelo entrante listo para aterrizar!");
                        System.out.println("Tipo de vuelo : " + OperationModel.decifrarTipoVuelo(reader.readInt()));
                        System.out.println("Identificacion : " + reader.readInt());
                        System.out.println("Nombre del vuelo : " + reader.readUTF());
                        System.out.println("Estado del vuelo : " + OperationModel.decifrarEstadoVuelo(reader.readInt()));
                        System.out.println("Tiempo de llegada : " + reader.readInt());
                        System.out.println("Tiempo de aterrizaje : " + reader.readInt());
                        System.out.println("Tiempo de embarque : " + reader.readInt());
                        System.out.println("Tiempo de desembarque : " + reader.readInt());
                        System.out.println("=====================================\n");
                        /* Notificar a la vista controlador (vista)*/
                        
                    }
                    case 1 -> /* Tipo 'B' : 1 : Vuelo aterrizando termino de aterrizar, listo para embarcar */
                            System.out.println("Vuelo aterrizando listo para embarcar!");
                    case 2 -> /* Tipo 'C' : 2 : Vuelo embarcando termino de embarcar, listo para desembarcar */
                            System.out.println("Vuelo embarcando listo para desembarcar!");
                    case 3 -> /* Tipo 'D' : 3 : Vuelo desembarcando termino de desembarcar, listo para declararse finalizado */
                            System.out.println("Vuelo desembarcando listo para declararse finalizado!");
                    default -> System.out.println("Mensaje recibido pero no existe instruccion respectiva!");
                }
            } catch (IOException ignored) {}
        }
    }
}