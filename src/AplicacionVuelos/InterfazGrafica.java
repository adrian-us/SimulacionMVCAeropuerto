package AplicacionVuelos;

import javax.swing.*;

public class InterfazGrafica {
    private JPanel panelPrincipal;
    private JTextArea txaRegistro;


    public InterfazGrafica(){
    }

    public void agregarRegistro(String informacion){
        txaRegistro.append(informacion + "\n");
    }

    public JPanel getPanelPrincipal(){
        return panelPrincipal;
    }
}
