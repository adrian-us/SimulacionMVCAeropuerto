package controlador;

import modelo.OperationModel;
import modelo.Vuelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadServidor extends Thread{
    private final int id;
    private final DataInputStream reader;
    private final DataOutputStream writer;
    private static final boolean running = true;
    public Servidor servidor;

    public ThreadServidor(final Socket socketRef,
                          final Servidor servidor,
                          final int id) throws IOException {
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.servidor = servidor;
        this.id = id;
    }

    public Vuelo crearVueloConDatos(OperationModel.Tipo tipo, int id, String nombre, int tiempoLlegada,
                                    int tiempoAterrizaje, int tiempoEmbarque, int  tiempoDesembarque){
        Vuelo vuelo = new Vuelo(tipo, id, nombre, tiempoLlegada, tiempoAterrizaje, tiempoEmbarque, tiempoDesembarque);
        return vuelo;
    }


    @Override
    public void run(){
        byte instruccionID;
        while (running){
            try {
                instruccionID = reader.readByte(); /* Esperar hasta que reciba un Byte */
                switch (instruccionID) {
                    case 0 -> /* Tipo 'A' : 0 : Vuelo entrante listo para aterrizar */{
                        servidor.agregarVuelo(this.crearVueloConDatos(
                                OperationModel.decifrarTipoVuelo(reader.readInt()),
                                reader.readInt(),
                                reader.readUTF(),
                                reader.readInt(),
                                reader.readInt(),
                                reader.readInt(),
                                reader.readInt()
                        ));
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
