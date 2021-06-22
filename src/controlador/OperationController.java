package controlador;

import modelo.OperationModel;
import modelo.Vuelo;
import vista.OperationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class OperationController implements ActionListener {
    OperationModel modelo;
    OperationView vista;
    Servidor servidor;

    public OperationController(OperationModel modelo, OperationView vista){
        this.modelo = modelo;
        this.vista = vista;
        servidor = new Servidor(this);
        _init_();
    }

    public void _init_(){
        vista.getVentanaAsignarPista().getBtnAsignar().addActionListener(this);
        vista.getVentanaAsignarPuerta().getBtnAsignar().addActionListener(this);
    }

    public static void main(String[] args) throws IOException {
        OperationController controlador = new OperationController(new OperationModel(),new OperationView());
        controlador.servidor.correrServidor();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getVentanaAsignarPuerta().getBtnAsignar()){
            System.out.println("Revisar asignacion de pista");
        } else if (e.getSource() == vista.getVentanaAsignarPuerta().getBtnAsignar()){
            System.out.println("Revisar asignacion de puerta");
        }
    }

    public void agregarVuelo(Vuelo vuelo){
        modelo.agregarVuelo(vuelo);
        modelo.mostrarVuelos();
    }


    public String datosVuelos() {
        String respuesta = "";
        ArrayList<Vuelo> vuelos = modelo.getVuelos();
        for(Vuelo vuelo: vuelos){
            respuesta += vuelo.toString();
        }
        return respuesta;
    }
}
