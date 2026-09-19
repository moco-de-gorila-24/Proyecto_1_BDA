package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogDuenio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDuenios extends JPanel {
    private JScrollPane jscrollPane;
    private JPanel panelDerecha;
    private JPanel panelTabla;
    private Boton botonAgregar;
    private Boton botonModificar;
    private Boton botonEliminar;
    private CampoBusqueda campoBusqueda;
    private DefaultTableModel modelo;
    private Tabla tabla;

    public PanelDuenios() {
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
        campoBusqueda.setToolTipText("Buscar por nombre o apellido (solo letras)");
        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);
        panelTabla.add(panelBusqueda, BorderLayout.NORTH);

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");
        modelo.addRow(new Object[]{"1", "Sebastián", "Escalante", "Ramírez", "Obregón 123", "6441234567", "sebas@email.com"});

        tabla = new Tabla(modelo);
        tabla.setRowHeight(50);
        jscrollPane.setViewportView(tabla);
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        botonAgregar = new Boton("Agregar Dueño");
        botonModificar = new Boton("Modificar Dueño");
        botonEliminar = new Boton("Eliminar Dueño");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 5, 15, 8);

        gbc.gridy = 0;
        panelDerecha.add(botonAgregar, gbc);
        gbc.gridy = 1;
        panelDerecha.add(botonModificar, gbc);
        gbc.gridy = 2;
        panelDerecha.add(botonEliminar, gbc);

        add(panelDerecha, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        // ===== Filtro en vivo =====
        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
        });

        botonAgregar.addActionListener(e -> {
            DialogDuenio dialogDuenio = new DialogDuenio((Frame) SwingUtilities.getWindowAncestor(this));

            dialogDuenio.iniciarComponentes();
        });


        // ELIMINACIÓN DIRECTA
        botonEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un dueño para eliminar");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
                this, "¿Eliminar el dueño seleccionado?",
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
            // Filtra por Nombre (1), Apellido Paterno (2), Apellido Materno (3)
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2, 3));
        }
    }
}