package AplicacionVentanaControlador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAsignarPista {
    private JButton btnCancelar;
    private JButton btnAsignar;
    private JComboBox comboBox;
    private JLabel lblNombre;
    private JLabel lblIdentificacion;
    private JPanel panelPrincipal;
    private JLabel labelTipo;
    private JLabel lblTipoAvion;
    private JLabel lblArregloPistas;
    private JLabel labelPistas;
    private JLabel labelAsignar;
    private int[] pistasDisponibles;

    public VentanaAsignarPista() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton)e.getSource());
                topFrame.setVisible(false);
            }
        });
    }

    /* Cada vez que se levanta la pantalla */
    public void setLblIdentificador(String identificador){
        lblIdentificacion.setText("ID : " + identificador);
    }
    public void setLblNombre(String nombre){ lblNombre.setText(nombre); }
    public void setLblTipoAvion(String tipoAvion){
        lblTipoAvion.setText(tipoAvion);
    }
    public void setLblPistas(String arregloPistas){
        lblArregloPistas.setText(arregloPistas);
    }
    /* =========================== */

    public void limpiarVentana(){
        lblNombre.setText("");
        lblIdentificacion.setText("");
        lblTipoAvion.setText("");
        lblArregloPistas.setText("");
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public int getPistaEscogida(){
        return comboBox.getSelectedIndex();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public int[] getPistasDisponibles() {
        return pistasDisponibles;
    }

    public void setPistasDisponibles(int[] pistasDisponibles) {
        this.pistasDisponibles = pistasDisponibles;
    }
}
