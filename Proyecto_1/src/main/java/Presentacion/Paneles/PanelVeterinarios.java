package Presentacion.Paneles;

import DAO.VeterinarioDAO;
import Dominio.Entidades.Veterinario;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogVeterinario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class PanelVeterinarios extends JPanel {

    private JScrollPane jscrollPane;
    private JPanel panelDerecha;
    private JPanel panelTabla;

    private CampoBusqueda campoBusqueda;

    private DefaultTableModel modelo;
    private Tabla tabla;

    private VeterinarioDAO veterinarioDAO;

    //Constructor

    public PanelVeterinarios() {

        veterinarioDAO = new VeterinarioDAO();

        setLayout(new BorderLayout());

        //Scroll de la tabla
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));

        //Panel derecho
        panelDerecha = new JPanel(new GridBagLayout());

        //Panel de tabla
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        //Buscador

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        campoBusqueda = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*", 25);

        campoBusqueda.setToolTipText("Buscar por nombre o especialidad");

        panelBusqueda.add(new JLabel("Buscar:"));

        panelBusqueda.add(campoBusqueda);

        panelTabla.add(panelBusqueda,BorderLayout.NORTH);

        //Modelo de tabla

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
        modelo.addColumn("Cédula");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Teléfono");

        //Tabla
        tabla = new Tabla(modelo);
        tabla.setRowHeight(40);
        jscrollPane.setViewportView(tabla);
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        //Botones

        Boton btnAgregar = new Boton("Agregar Veterinario");

        Boton btnModificar = new Boton("Modificar Veterinario");

        Boton btnEliminar = new Boton("Eliminar Veterinario");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 5, 15, 8);

        //Agregar
        gbc.gridy = 0;
        panelDerecha.add(btnAgregar, gbc);

        //Modificar
        gbc.gridy = 1;
        panelDerecha.add(btnModificar, gbc);

        //Eliminar
        gbc.gridy = 2;
        panelDerecha.add(btnEliminar, gbc);

        //Agregar paneles
        add(panelDerecha, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        //Cargar datos
        cargarVeterinarios();

        //Buscador
        campoBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });

        //Evento agregar
        btnAgregar.addActionListener(e -> agregarVeterinario());

        //Evento modificar
        btnModificar.addActionListener(e -> modificarVeterinario());

        //Evento eliminar
        btnEliminar.addActionListener(e -> eliminarVeterinario());
    }

    //Cargar veterinarios

    private void cargarVeterinarios() {

        modelo.setRowCount(0);

        List<Veterinario> veterinarios = veterinarioDAO.consultarTodos();

        for (Veterinario veterinario : veterinarios) {

            modelo.addRow(new Object[]{veterinario.getIdVeterinario(), veterinario.getNombre(), veterinario.getApellidoP(), veterinario.getApellidoM(), veterinario.getCedulaProfesional(), veterinario.getEspecialidad(), veterinario.getTelefono()});
        }
    }

    //Agregar veterinario

    private void agregarVeterinario() {

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);

        DialogVeterinario dialog = new DialogVeterinario(frame);

        if (dialog.isAceptado()) {

            Veterinario veterinario = dialog.getVeterinario();

            boolean resultado = veterinarioDAO.insertar(veterinario);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Veterinario agregado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarVeterinarios();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el veterinario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Modificar veterinario

    private void modificarVeterinario() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un veterinario para modificar.", "Veterinario", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertimos la fila visual a la fila real del modelo porque puede existir un filtro activo.

        int filaModelo = tabla.convertRowIndexToModel(filaVista);
        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());
        Veterinario veterinario = veterinarioDAO.consultar(id);

        if (veterinario == null) {

            JOptionPane.showMessageDialog(this, "No se encontro el veterinario seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
        DialogVeterinario dialog = new DialogVeterinario(frame, veterinario);

        if (dialog.isAceptado()) {

            Veterinario veterinarioActualizado = dialog.getVeterinario();
            boolean resultado = veterinarioDAO.actualizar(veterinarioActualizado);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Veterinario actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarVeterinarios();

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el veterinario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Eliminar veterinario

    private void eliminarVeterinario() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un veterinario para eliminar.", "Veterinario", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(filaVista);

        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());

        String nombre = modelo.getValueAt(filaModelo, 1).toString();

        String apellidoP = modelo.getValueAt(filaModelo, 2).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar al veterinario " + nombre + " " + apellidoP + "?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = veterinarioDAO.eliminar(id);

        if (resultado) {

            JOptionPane.showMessageDialog(this, "Veterinario eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cargarVeterinarios();
        } else {
            
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el veterinario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Filtrar

    private void filtrar() {

        String texto = campoBusqueda.getText().trim();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

        tabla.setRowSorter(sorter);

        if (texto.isEmpty()) {
            
            sorter.setRowFilter(null);
        } else {

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2, 3, 5));
        }
    }
}