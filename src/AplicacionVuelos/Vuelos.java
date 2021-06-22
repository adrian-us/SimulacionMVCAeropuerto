package AplicacionVuelos;

/* Vuelos viene siendo el modelo es decir la clase que tiene las funciones necesarias para
   que el controlador logre hacer su simulacion, toda la simulacion se programa en Controlador no aca */

import modelo.OperationModel;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;

import static java.lang.Thread.sleep;

public class Vuelos implements Runnable {
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
                sleep(intervalo * 1000L);
                writer.writeByte(0); /* Notificacion tipo 'A' : 0 : Nuevo avion entrante */
                writer.writeInt(aleatorio(0,3)); /* Paso el tipo de vuelo */
                writer.writeInt(contador); /* Paso la identificacion/numero de vuelo */
                writer.writeUTF(OperationModel.getAerolineas()[aleatorio(0,9)]+contadorStr); /* Paso el nombre del vuelo */
                writer.writeInt(0); /* Paso el estado del vuelo -> 0 = ENTRANTE */
                writer.writeInt(5); /* Paso el tiempo de llegada */
                writer.writeInt(5); /* Paso el tiempo de aterrizaje */
                writer.writeInt(5); /* Paso el tiempo de embarque */
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
        Vuelos vuelos = new Vuelos(socketRef,4); /* Cada [ 5 ] segundos */
        vuelos.run();
    }


}
