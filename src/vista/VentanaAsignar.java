package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAsignar {
    private JButton asignarButton;
    private JButton cancelarButton;
    private JComboBox comboBox1;
    private JPanel panelPrincipal;

    public VentanaAsignar(){

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cerrar ventana!");
            }
        });
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Asignar pista");
        frame.setContentPane(new VentanaAsignar().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
