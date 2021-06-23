package VentanaInformacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class informacion {
    public JPanel home;
    private JTable tabla;
    DefaultTableModel model;

    public informacion(){
        model = new DefaultTableModel(0,0);
        tabla.setModel(model);
        model.addColumn("Nombre");
        model.addColumn("Atrasado");
        model.addColumn("Tiempo Aterrizaje");
        model.addColumn("Tiempo Desembarque");
        model.addColumn("Estado");
        model.addColumn("Tipo de Avion");
        model.setColumnIdentifiers(new Object[]{"Nombre", "Atrasado", "Tiempo Aterrizaje", "Tiempo Desembarque", "Estado", "Tipo de Avion"});



    }

    public void updateTable(String[] newData){
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for(String string : newData){
            String[] split = string.split("/");
            if(split.length ==6)
            model.addRow(new Object[]{split[0], split[1], split[2], split[3], split[4], split[5] });
        }

    }


}
