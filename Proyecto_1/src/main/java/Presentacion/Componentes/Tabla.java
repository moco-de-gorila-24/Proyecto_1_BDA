package Presentacion.Componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Tabla extends JTable {

    public Tabla(DefaultTableModel tableModel){
        super(tableModel);
        setFillsViewportHeight(true);
        setRowHeight(40);
        setShowGrid(false);
        setSelectionBackground(Color.BLACK);
        setSelectionForeground(Color.WHITE);
        setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);


    }
}
