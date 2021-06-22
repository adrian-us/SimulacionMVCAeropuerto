package AplicacionControlador;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Servidor {
    private HashMap<Integer, ThreadServidor> conexiones;
    private boolean running = true;
    private ServerSocket serverSocket;
    protected final Controlador controlador;

    public Servidor(Controlador controlador) {
        conexiones = new HashMap();
        this.controlador = controlador;
    }

    public void agregarVuelo(Vuelo vuelo){
        controlador.agregarVuelo(vuelo);
    }

    public void detenerServidor() {
        running = false;
    }

    public void notificarAterrizaje(int id) throws IOException {
        if (conexiones.containsKey(22)){
            conexiones.get(22).notificarAterrizaje(id);
        }
    }

    public void notificarDesembarqueCompleto(int id) throws IOException {
        if (conexiones.containsKey(22)){
            conexiones.get(22).notificarDesembarqueCompleto(id);
        }
    }

    public void correrServidor() throws IOException {
        serverSocket = new ServerSocket(35775);
        int identificador;
        while (running) {
            System.out.println("Esperando conexiones...");
            Socket refSocket = serverSocket.accept();
            /* ========= */
            /* Cuando una conexion es aceptada se lee un Integer que identifica
               la conexion (11 = 'Vuelos', 22 = 'Ventana Controlador', 33 = 'Informacion' */
            DataInputStream reader = new DataInputStream(refSocket.getInputStream());
            identificador = reader.readInt();
            System.out.println("Conexion establecida : " + identificador);
            /* ========= */
            ThreadServidor nuevoHilo = new ThreadServidor(refSocket, this, identificador);
            //conexiones.add(nuevoHilo);
            conexiones.put(identificador, nuevoHilo);
            nuevoHilo.start();
        }
    }

    public HashMap<Integer, ThreadServidor> getConexiones() {
        return conexiones;
    }
}