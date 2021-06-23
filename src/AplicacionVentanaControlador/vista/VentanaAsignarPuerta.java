package AplicacionVentanaControlador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAsignarPuerta {
    private JComboBox comboBox;
    private JButton btnAsignar;
    private JButton btnCancelar;
    private JPanel panelPrincipal;
    private JLabel lblNombre;
    private JLabel lblIdentificacion;
    private JLabel labelTipo;
    private JLabel lblTipoAvion;
    private JLabel lblArregloPuertas;
    private JLabel labelPuertas;
    private JLabel labelAsignar;
    private int[] puertasDisponibles;

    public VentanaAsignarPuerta() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton)e.getSource());
                topFrame.setVisible(false);
            }
        });
    }

    public JButton getBtnAsignar() {
        return btnAsignar;
    }

    public int getPistaEscogida(){
        return comboBox.getSelectedIndex();
    }

    public int[] getPuertasDisponibles() {
        return puertasDisponibles;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPuertasDisponibles(int[] puertasDisponibles) {
        this.puertasDisponibles = puertasDisponibles;
    }

    public void setLblNombre(String nombre) { lblNombre.setText(nombre); }

    public void setLblIdentificacion(String id) { lblIdentificacion.setText(id); }

    public void setLblTipoAvion(String tipoAvion) { lblTipoAvion.setText(tipoAvion); }

    public void setLblArregloPuertas(String arregloPuertas) { lblArregloPuertas.setText(arregloPuertas); }

    public void limpiarVentana(){
        lblNombre.setText("");
        lblIdentificacion.setText("");
        lblTipoAvion.setText("");
        lblArregloPuertas.setText("");
    }

}
