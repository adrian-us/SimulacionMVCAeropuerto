package controlador;

import modelo.OperationModel;
import modelo.Vuelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class OperationController implements ActionListener {
    OperationModel modelo;
    Servidor servidor;



    public OperationController(OperationModel modelo){
        this.modelo = modelo;
        servidor = new Servidor();
    }


    public static void main(String[] args) throws IOException {
        OperationController controlador = new OperationController(new OperationModel());
        controlador.servidor.correrServidor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
