package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMascotas extends JPanel {
    private JScrollPane jscrollPane;
    private JPanel panelDerecha;
    private JPanel panelTabla;
    private CampoBusqueda campoBusqueda;
    private DefaultTableModel modelo;
    private Tabla tabla;

    public PanelMascotas(){
        setLayout(new BorderLayout());

        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));

        panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridBagLayout());

        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        campoBusqueda = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*", 25);
        campoBusqueda.setToolTipText("Buscar por nombre o raza (solo letras)");
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);
        panelTabla.add(panelBusqueda, BorderLayout.NORTH);

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modelo.addColumn("ID");
        modelo.addColumn("ID Dueño");  // Relación con Dueños
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Raza");
        modelo.addColumn("Edad");
        modelo.addRow(new Object[]{"101", "1", "Firulais", "Perro", "Labrador", "3"});

        tabla = new Tabla(modelo);
        tabla.setRowHeight(50);
        jscrollPane.setViewportView(tabla);
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        Boton btnAgregar = new Boton("Agregar Mascota");
        Boton btnModificar = new Boton("Modificar Mascota");
        Boton btnEliminar = new Boton("Eliminar Mascota");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 5, 15, 8);

        gbc.gridy = 0;
        panelDerecha.add(btnAgregar, gbc);
        gbc.gridy = 1;
        panelDerecha.add(btnModificar, gbc);
        gbc.gridy = 2;
        panelDerecha.add(btnEliminar, gbc);

        add(panelDerecha, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        // Filtro en vivo
        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        // ELIMINACIÓN DIRECTA
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una mascota para eliminar");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
                this, "¿Eliminar la mascota seleccionada?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                modelo.removeRow(fila);
            }
        });
    }

    private void filtrar() {
        String texto = campoBusqueda.getText().trim();
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
                new javax.swing.table.TableRowSorter<>(modelo);
        tabla.setRowSorter(sorter);

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Filtra por Nombre (2), Especie (3), Raza (4)
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 2, 3, 4));
        }
    }
}