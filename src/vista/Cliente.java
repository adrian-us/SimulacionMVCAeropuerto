package vista;

import modelo.OperationModel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread {
    private Socket socketRef;
    public DataInputStream reader;
    public DataOutputStream writer;
    private OperationView refPantalla;
    private final boolean running = true;
    private static final int PUERTO = 35775;

    public Cliente(OperationView refPantalla) throws IOException {
        socketRef = new Socket("localhost",PUERTO);
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        this.refPantalla = refPantalla;
    }

    @Override
    public void run(){
        byte instruccionID;
        while (running){
            try {
                instruccionID = reader.readByte();
                switch (instruccionID) {
                    case 0: /* Tipo 'A' : 0 : Vuelo entrante listo para aterrizar */
                        System.out.println("Nuevo vuelo entrante asignar pista");
                        break;
                    case 1: /* Tipo 'B' : 1 : Vuelo aterrizando listo para embarcar */
                        System.out.println("vuelo aterrizado listo para embarcar");
                        break;
                    case 2:
                        break;
                }
            } catch (IOException ignored) {}
        }
    }
}
