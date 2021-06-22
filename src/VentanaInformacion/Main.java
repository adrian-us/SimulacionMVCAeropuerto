package VentanaInformacion;

/* Vuelos viene siendo el modelo es decir la clase que tiene las funciones necesarias para
   que el controlador logre hacer su simulacion, toda la simulacion se programa en Controlador no aca */

import modelo.OperationModel;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.*;

import static java.lang.Thread.sleep;

public class Main implements Runnable {
    private static final SecureRandom random = new SecureRandom();
    private static final int PUERTO = 35775;
    private final int intervalo;
    private final DataOutputStream writer;
    private final DataInputStream reader;
    private final informacion ventana = new informacion();

    public Main(Socket socketRef, int intervalo) throws IOException {
        writer = new DataOutputStream(socketRef.getOutputStream());
        reader = new DataInputStream(socketRef.getInputStream());
        writer.writeInt(44); /* Mando una identificacion -> 11 = Aplicacion 'Vuelos' */
        this.intervalo = intervalo;
        JFrame frame = new JFrame("Registro de vuelos entrantes");
        frame.setContentPane(ventana.home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new DimensionUIResource(800,500));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void run(){
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        int contador = 0;
        boolean running = true;
        while (running) {
            String contadorStr = " ";
            contadorStr += contador;

            /* Señal de parada */
            if (contador >= 45){
                running = false;
            }
            /* =============== */

            try {
                String str = "Actualizar ventana de Informacion";
                sleep(5000);
                writer.writeByte(4); /* Notificacion tipo 'A' : 0 : Nuevo avion entrante */
                writer.writeUTF(str); /* Paso el nombre del vuelo */
                parseResponse(reader.readUTF());
            } catch (IOException | InterruptedException ignored) {}
        }
        System.out.println("Termina hilo de Vuelos");
    }

    public static void main(String[] args) throws IOException {
        Socket socketRef = new Socket("localhost",PUERTO);
        OperationModel modelo = new OperationModel();
        Main main = new Main(socketRef,4); /* Cada [ 5 ] segundos */
        main.run();
    }

    public void parseResponse(String req){
        String[] strSplit = req.split("Vuelo");
        ventana.updateTable(strSplit);
    }


}