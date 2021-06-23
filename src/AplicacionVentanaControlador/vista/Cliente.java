package AplicacionVentanaControlador.vista;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread{
    private Socket socketRef;
    public DataInputStream reader;
    public DataOutputStream writer;
    private OperationView refVista;
    private final boolean running = true;
    private static final int PUERTO = 35775;

    public Cliente(OperationView refVista) throws IOException {
        socketRef = new Socket("localhost",PUERTO);
        reader = new DataInputStream(socketRef.getInputStream());
        writer = new DataOutputStream(socketRef.getOutputStream());
        writer.writeInt(22); /* Se identifica como el cliente '22' */
        this.refVista = refVista;
    }

    @Override
    public void run(){
        byte instruccionID;
        while (running){
            try {
                instruccionID = reader.readByte(); /* Espero hasta recibir instruccion */
                switch (instruccionID){
                    case 0:
                        System.out.println("Nuevo vuelo entrante, asignar pista!");
                        refVista.agregarBoton(reader.readUTF(), reader.readInt(),0);
                        break;
                    case 1:
                        System.out.println("Vuelo aterrizado listo para asignar puerta!");
                        break;
                    case 2:
                        System.out.println("Vuelo finalizo de desembarcar!");
                        break;
                }
            } catch (IOException ignored) {}
        }
    }

    public String[] pedirInformacionPista(int identificador) throws IOException {
        System.out.println("Check");
        String[] arregloInformacion = new String[4];
        writer.writeByte(101);
        writer.writeInt(identificador);
        for (int i = 0; i < 4; i++){
            arregloInformacion[i] = reader.readUTF();
            System.out.println("dato recibido :" + arregloInformacion[i]);
        }
        return arregloInformacion;
    }

    public String[] pedirInformacionPuerta(int identificador) throws IOException {
        String[] arregloInformacion = new String[4];
        writer.writeByte(102);
        writer.writeInt(identificador);
        for (int i = 0; i < 4; i++){
            arregloInformacion[i] = reader.readUTF();
        }
        return arregloInformacion;
    }

    public int[] pedirInformacionPistasDisponibles() throws IOException {
        writer.writeByte(103);
        int cantidad = reader.readInt();
        int pistas[] = new int[cantidad];
        for (int i = 0; i < cantidad; i++){
            pistas[i] = reader.readInt();
        }
        return pistas;
    }

    public int[] pedirInformacionPuertasDisponibles() throws IOException {
        writer.writeByte(104);
        int cantidad = reader.readInt();
        int puertas[] = new int[cantidad];
        for (int i = 0; i < cantidad; i++){
            puertas[i] = reader.readInt();
        }
        return puertas;
    }


}
