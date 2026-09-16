package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogConsulta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelConsultas extends JPanel {
    private JScrollPane jscrollPane;
    private JPanel panelDerecha;
    private JPanel panelTabla;
    private Boton agendar;
    private Boton modificar;
    private Boton eliminar;
    private CampoBusqueda campoBusqueda;
    private DefaultTableModel modelo;
    private Tabla tabla;

    public PanelConsultas(){
        setLayout(new BorderLayout());

        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));

        panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridBagLayout());

        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        // ===== Campo de búsqueda arriba de la tabla =====
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        campoBusqueda = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*", 25);
        campoBusqueda.setToolTipText("Buscar por motivo o diagnóstico (solo letras)");
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);
        panelTabla.add(panelBusqueda, BorderLayout.NORTH);

        // Tabla
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modelo.addColumn("ID");
        modelo.addColumn("ID Mascota");
        modelo.addColumn("ID Veterinario");
        modelo.addColumn("Motivo");
        modelo.addColumn("Diagnóstico");
        modelo.addColumn("Tratamiento");
        modelo.addColumn("Costo");

        modelo.addRow(new Object[]{"1", "101", "201", "Vacunación", "Sano", "Refuerzo", "350.00"});

        tabla = new Tabla(modelo);
        tabla.setRowHeight(50);
        jscrollPane.setViewportView(tabla);
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        agendar = new Boton("Agendar consultas");
        modificar = new Boton("Modificar Consulta");
        eliminar = new Boton("Eliminar Consulta");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 5, 15, 8);

        gbc.gridy = 0;
        panelDerecha.add(agendar, gbc);
        gbc.gridy = 1;
        panelDerecha.add(modificar, gbc);
        gbc.gridy = 2;
        panelDerecha.add(eliminar, gbc);

        add(panelDerecha, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        // ===== Filtro en vivo con expresión regular =====
        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        // Acciones
        agendar.addActionListener(e -> {
            DialogConsulta dialog = new DialogConsulta((Frame) SwingUtilities.getWindowAncestor(this));
            dialog.setVisible(true);
            if (dialog.isAceptado()) {
                modelo.addRow(new Object[]{
                    modelo.getRowCount() + 1,
                    dialog.getIdMascota(),
                    dialog.getIdVeterinario(),
                    dialog.getMotivo(),
                    dialog.getDiagnostico(),
                    dialog.getTratamiento(),
                    dialog.getCosto()
                });
            }
        });

        // ELIMINACIÓN DIRECTA: solo confirmación
        eliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una consulta para eliminar");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
                this, "¿Eliminar la consulta seleccionada?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                modelo.removeRow(fila);
            }
        });

        modificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una consulta para modificar");
                return;
            }
            DialogConsulta dialog = new DialogConsulta((Frame) SwingUtilities.getWindowAncestor(this));
            dialog.setVisible(true);
            if (dialog.isAceptado()) {
                modelo.setValueAt(dialog.getIdMascota(), fila, 1);
                modelo.setValueAt(dialog.getIdVeterinario(), fila, 2);
                modelo.setValueAt(dialog.getMotivo(), fila, 3);
                modelo.setValueAt(dialog.getDiagnostico(), fila, 4);
                modelo.setValueAt(dialog.getTratamiento(), fila, 5);
                modelo.setValueAt(dialog.getCosto(), fila, 6);
            }
        });
    }

    // ===== Método de filtrado con TableRowSorter y regex =====
    private void filtrar() {
        String texto = campoBusqueda.getText().trim();
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
                new javax.swing.table.TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra por Motivo (col 3) y Diagnóstico (col 4), ignorando mayúsculas
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 3, 4));
        }
    }
}