/*
 * Created by JFormDesigner on Fri Jun 18 21:06:36 CST 2021
 */

package vista;

import controlador.OperationController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * @author unknown
 */
public class OperationView extends JFrame {
    public OperationView() {
        initComponents();
    }
    protected OperationController controlador;

    private static final Map<Integer, JButton> botonesEntrada = new HashMap<>();
    private static final Map<Integer, JButton> botonesAterrizaje = new HashMap<>();
    private static final Map<Integer, JButton> botonesEmbarque = new HashMap<>();
    private static final Map<Integer, JButton> botonesDesembarque = new HashMap<>();



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane2 = new JScrollPane();
        panelEntrantes = new JPanel();
        scrollPane3 = new JScrollPane();
        panelAterrizaje = new JPanel();
        scrollPane4 = new JScrollPane();
        panelEmbarque = new JPanel();
        scrollPane5 = new JScrollPane();
        panelDesembarque = new JPanel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label4 = new JLabel();
        label3 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== scrollPane2 ========
        {

            //======== panelEntrantes ========
            {
                panelEntrantes.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
                javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax
                .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
                .awt.Font("D\u0069al\u006fg",java.awt.Font.BOLD,12),java.awt
                .Color.red),panelEntrantes. getBorder()));panelEntrantes. addPropertyChangeListener(new java.beans.
                PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er".
                equals(e.getPropertyName()))throw new RuntimeException();}});
                panelEntrantes.setLayout(new GridLayout(0, 1));
            }
            scrollPane2.setViewportView(panelEntrantes);
        }

        //======== scrollPane3 ========
        {

            //======== panelAterrizaje ========
            {
                panelAterrizaje.setLayout(new GridLayout(0, 1));
            }
            scrollPane3.setViewportView(panelAterrizaje);
        }

        //======== scrollPane4 ========
        {

            //======== panelEmbarque ========
            {
                panelEmbarque.setLayout(new GridLayout(0, 1));
            }
            scrollPane4.setViewportView(panelEmbarque);
        }

        //======== scrollPane5 ========
        {

            //======== panelDesembarque ========
            {
                panelDesembarque.setLayout(new GridLayout(0, 1));
            }
            scrollPane5.setViewportView(panelDesembarque);
        }

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(1, 4));

            //---- label2 ----
            label2.setText("Entrantes");
            panel1.add(label2);

            //---- label4 ----
            label4.setText("Aterrizando");
            panel1.add(label4);

            //---- label3 ----
            label3.setText("Embarcando");
            panel1.add(label3);

            //---- label1 ----
            label1.setText("Desembarcando");
            panel1.add(label1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 854, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                        .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                    .addGap(633, 633, 633))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane2;
    private JPanel panelEntrantes;
    private JScrollPane scrollPane3;
    private JPanel panelAterrizaje;
    private JScrollPane scrollPane4;
    private JPanel panelEmbarque;
    private JScrollPane scrollPane5;
    private JPanel panelDesembarque;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label3;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /* Metodos */
    public void agregarBoton(String nombre, int identificador, int estado){
        JButton boton = new JButton(nombre);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton presionado!");
            }
        });
        switch (estado){
            case 0:
                panelEntrantes.add(boton);
                panelEntrantes.updateUI();
                botonesEntrada.put(identificador, boton);
                break;
            case 1:
                panelAterrizaje.add(boton);
                panelAterrizaje.updateUI();
                botonesAterrizaje.put(identificador, boton);
                break;
            case 2:
                panelEmbarque.add(boton);
                panelEmbarque.updateUI();
                botonesEmbarque.put(identificador, boton);
                break;
            case 3:
                panelDesembarque.add(boton);
                panelDesembarque.updateUI();
                botonesDesembarque.put(identificador, boton);
                break;
        }
    }

    public static void main(String[] args) {
//        JFrame frame = new JFrame("Simulacion");
//        frame.setContentPane();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OperationView().setVisible(true);
            }
        });
    }
}
