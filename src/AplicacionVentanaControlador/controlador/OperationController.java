package AplicacionVentanaControlador.controlador;

import AplicacionVentanaControlador.modelo.OperationModel;
import AplicacionVentanaControlador.vista.OperationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OperationController implements ActionListener {
    OperationModel modelo;
    OperationView vista;

    public OperationController(OperationModel modelo, OperationView vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void _init_(){
        vista.getVentanaAsignarPista().getBtnAsignar().addActionListener(this);
        //vista.getVentanaAsignarPuerta().getBtnAsignar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getVentanaAsignarPista().getBtnAsignar())){
            System.out.println("Revisar asignacion pista : " + vista.getVentanaAsignarPista().getPistaEscogida());
            String pistasDisp = "Pistas disponibles : [ ";
            for (int i : vista.getVentanaAsignarPista().getPistasDisponibles()){
                pistasDisp += i + ",";
            }
            pistasDisp += " ]";
        } else if (e.getSource().equals(vista.getVentanaAsignarPuerta().getBtnAsignar())){
            System.out.println("Revisar asignacion puerta : ");
        }
    }

    public static void main(String[] args) throws IOException {
        OperationView vistaControlador = new OperationView();
        OperationModel modelo = new OperationModel();
        OperationController controlador = new OperationController(new OperationModel(),vistaControlador);
        vistaControlador.setVisible(true);
    }
}
