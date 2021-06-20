package controlador;

import modelo.OperationModel;

import java.io.IOException;


public class OperationController {
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
}
