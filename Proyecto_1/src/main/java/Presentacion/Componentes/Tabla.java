package Presentacion.Componentes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Componente personalizado que extiende {@link JTable} y aplica un estilo
 * visual propio del sistema veterinario: encabezado negro con texto blanco,
 * sin líneas de cuadrícula, sin espaciado entre celdas y edición deshabilitada.
 *
 * @author Andrés
 */
public class Tabla extends JTable {

    /**
     * Constructor que crea la tabla con el modelo indicado y aplica el estilo.
     *
     * @param tableModel modelo de datos que alimentará la tabla.
     */
    public Tabla(DefaultTableModel tableModel) {
        super(tableModel);
        setFillsViewportHeight(true);
        setRowHeight(40);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
    }

    /**
     * Indica que ninguna celda de la tabla puede ser editada directamente.
     *
     * @param row fila de la celda.
     * @param column columna de la celda.
     * @return siempre {@code false}.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}