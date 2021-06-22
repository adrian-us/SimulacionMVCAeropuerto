package vista;

import javax.swing.*;

public class VentanaAsignarPista {
    public JComboBox comboBox;
    private JButton btnCancelar;
    private JButton btnAsignar;
    private JPanel panelPrincipal;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel labelTipo;
    private JLabel lblTipoAvion;
    private JLabel labelPistas;
    private JLabel lblArregloPistas;
    private JLabel labelAsignar;


    public VentanaAsignarPista(){}

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
        lblArregloPistas.setText(arregloPistas);
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
        JFrame frame = new JFrame("Asignar pista");
        frame.setContentPane(new VentanaAsignarPista().getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
