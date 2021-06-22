package AplicacionVuelos;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;

import static java.lang.Thread.sleep;

public class Vuelos implements Runnable {
    private static final String[] AEROLINEAS = {
            "Allegiant Air",
            "American Airlines",
            "Delta",
            "InterJet",
            "JetBlue",
            "Jetstar",
            "Qatar Airways",
            "Spirit",
            "Volaris"
    };
    private static final SecureRandom random = new SecureRandom();
    private static final int PUERTO = 35775;
    private final int intervalo;
    private final DataOutputStream writer;
    private final InterfazGrafica interfaz = new InterfazGrafica();

    public Vuelos(Socket socketRef, int intervalo) throws IOException {
        writer = new DataOutputStream(socketRef.getOutputStream());
        writer.writeInt(11); /* Mando una identificacion -> 11 = Aplicacion 'Vuelos' */
        this.intervalo = intervalo;
        JFrame frame = new JFrame("Registro de vuelos entrantes");
        frame.setContentPane(interfaz.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new DimensionUIResource(500,500));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static int aleatorio(int min, int max){
        return random.nextInt(max - min) + min;
    }

    @Override
    public void run(){
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
                //TODO: Parametrizar y hacer random los valores, para que cada avion tenga diferentes valores
                sleep(intervalo * 1000L);
                writer.writeByte(0); /* Notificacion tipo 'A' : 0 : Nuevo avion entrante */
                writer.writeInt(aleatorio(0,3)); /* Paso el tipo de vuelo */
                writer.writeInt(contador); /* Paso el nombre del vuelo */
                writer.writeUTF(AEROLINEAS[aleatorio(0,9)]+contadorStr); /* Paso la identificacion del vuelo */
                writer.writeInt(5); /* Paso el tiempo de aterrizaje */
                writer.writeInt(5); /* Paso el tiempo de desembarque */
                contador++;
                interfaz.agregarRegistro("Nuevo vuelo entrante, controlador notificado. Cantidad de vuelos aceptados : " + contador);
                /* Duerme la cantidad de segundos */
            } catch (IOException | InterruptedException ignored) {}
        }
        System.out.println("Termina hilo de Vuelos");
    }

    public static void main(String[] args) throws IOException {
        Socket socketRef = new Socket("localhost",PUERTO);
        Vuelos vuelos = new Vuelos(socketRef,15); /* Cada [ 5 ] segundos */
        vuelos.run();
    }


}
