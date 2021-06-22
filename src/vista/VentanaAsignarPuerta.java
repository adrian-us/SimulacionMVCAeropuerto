package vista;

import javax.swing.*;

public class VentanaAsignarPuerta {
    private JComboBox comboBox;
    private JButton btnCancelar;
    private JButton btnAsignar;
    private JLabel lblNombre;
    private JLabel lblIdentificador;
    private JLabel labelTipo;
    private JLabel lblTipoAvion;
    private JLabel labelPuertas;
    private JLabel labelAsignar;
    private JLabel lblArregloPuertas;
    private JPanel panelPrincipal;

    public VentanaAsignarPuerta(){}

    /* Cada vez que se levanta la pantalla */
    public void setLblIdentificador(String identificador){
        lblIdentificador.setText("ID : " + identificador);
    }

    public void setLblNombre(String nombre){
        lblNombre.setText(nombre);
    }

    public void setLblTipoAvion(String tipoAvion){
        lblTipoAvion.setText(tipoAvion);
    }

    public void setLabelPistas(String arregloPistas){
        lblArregloPuertas.setText(arregloPistas);
    }
    /* =========================== */

    public JPanel getPanelPrincipal(){
        return panelPrincipal;
    }

    public JButton getBtnCancelar(){
        return btnCancelar;
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Asignar puerta");
        frame.setContentPane(new VentanaAsignarPuerta().getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
