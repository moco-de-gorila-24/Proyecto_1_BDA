package Presentacion.Paneles;

import DAO.DuenioDAO;
import Dominio.Entidades.Duenio;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogDuenio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * Panel que muestra el listado de dueños registrados y permite realizar
 * operaciones CRUD sobre ellos (agregar, modificar y eliminar), además de
 * filtrarlos mediante un campo de búsqueda en vivo.
 *
 * @author ACER
 */
public class PanelDuenios extends JPanel {

    /** Panel con scroll que contiene la tabla de dueños. */
    private JScrollPane jscrollPane;

    /** Panel lateral que contiene los botones de acción. */
    private JPanel panelDerecha;

    /** Panel que contiene el campo de búsqueda y la tabla. */
    private JPanel panelTabla;

    /** Botón para agregar un dueño. */
    private Boton botonAgregar;

    /** Botón para modificar un dueño. */
    private Boton botonModificar;

    /** Botón para eliminar un dueño. */
    private Boton botonEliminar;

    /** Campo de búsqueda en vivo. */
    private CampoBusqueda campoBusqueda;

    /** Modelo de datos de la tabla. */
    private DefaultTableModel modelo;

    /** Tabla que muestra los dueños. */
    private Tabla tabla;

    /** DAO para operaciones sobre dueños. */
    private DuenioDAO duenioDAO;

    /**
     * Constructor que inicializa el DAO, construye la interfaz gráfica,
     * carga los dueños desde la base de datos y configura los eventos.
     */
    public PanelDuenios() {

        duenioDAO = new DuenioDAO();

        setLayout(new BorderLayout());

        //Panel de la tabla

        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));

        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        //Campo de busqueda

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        campoBusqueda = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*", 25);

        campoBusqueda.setToolTipText("Buscar por nombre o apellido");

        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);

        panelTabla.add(panelBusqueda, BorderLayout.NORTH);

        //Modelo de la tabla

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

        tabla = new Tabla(modelo);
        tabla.setRowHeight(50);

        jscrollPane.setViewportView(tabla);

        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        //Botones

        botonAgregar = new Boton("Agregar Dueño");
        botonModificar = new Boton("Modificar Dueño");
        botonEliminar = new Boton("Eliminar Dueño");

        panelDerecha = new JPanel(new GridBagLayout());

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

        //Agregar al panel principal

        add(panelDerecha, BorderLayout.WEST);

        add(panelTabla, BorderLayout.CENTER);

        //Cargar datos de la base de datos

        cargarDuenios();
        
        //Busqueda en vivo

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
            }});

        //Boton agregar
        botonAgregar.addActionListener(e -> agregarDuenio());

        //Boton modificar
        botonModificar.addActionListener(e -> modificarDuenio());

        //Boton eliminar
        botonEliminar.addActionListener(e -> eliminarDuenio());
    }

    /**
     * Carga todos los dueños desde la base de datos y los inserta en el modelo
     * de la tabla, uniendo los teléfonos en una sola cadena.
     */
    private void cargarDuenios() {

        modelo.setRowCount(0);

        List<Duenio> duenios = duenioDAO.consultarTodos();

        for (Duenio duenio : duenios) {
            modelo.addRow(new Object[]{duenio.getIdDueno(), duenio.getNombre(), duenio.getApellidoP(), duenio.getApellidoM(), duenio.getDireccion(), obtenerTelefonos(duenio), duenio.getEmail()});
        }
    }

    /**
     * Convierte la lista de teléfonos de un dueño en una cadena separada por comas.
     *
     * @param duenio dueño del que se obtienen los teléfonos.
     * @return cadena con los teléfonos separados por comas, o cadena vacía si no tiene.
     */
    private String obtenerTelefonos(Duenio duenio) {

        if (duenio.getTelefonos() == null || duenio.getTelefonos().isEmpty()) {
            return "";
        }

        return String.join(", ", duenio.getTelefonos());
    }

    
    /**
     * Abre el diálogo de registro de dueño. Si el usuario acepta, inserta el
     * dueño en la base de datos y recarga la tabla.
     */
    private void agregarDuenio() {

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);

        DialogDuenio dialog = new DialogDuenio(frame);

        if (dialog.isAceptado()) {

            Duenio duenio = dialog.getDuenio();

            boolean resultado = duenioDAO.insertar(duenio);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Dueño registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                cargarDuenios();
            } else {

                JOptionPane.showMessageDialog(this, "No se pudo registrar el dueño.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    /**
     * Abre el diálogo de modificación con los datos del dueño seleccionado.
     * Si el usuario acepta, actualiza el dueño en la base de datos y recarga
     * la tabla.
     */
    private void modificarDuenio() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un dueño para modificar.", "Modificar dueño", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertimos la fila de la vista a la fila real del modelo.
        int filaModelo = tabla.convertRowIndexToModel(filaVista);

        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());

        Duenio duenio =duenioDAO.consultar(id);

        if (duenio == null) {

            JOptionPane.showMessageDialog(this, "No se encontro el dueño.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);

        DialogDuenio dialog = new DialogDuenio(frame, duenio);

        if (dialog.isAceptado()) {

            Duenio duenioActualizado = dialog.getDuenio();

            boolean resultado = duenioDAO.actualizar(duenioActualizado);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Dueño modificado correctamente.", "Modificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarDuenios();
            } else {

                JOptionPane.showMessageDialog(this, "No se pudo modificar el dueño.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Elimina el dueño seleccionado, previa confirmación del usuario, y
     * recarga la tabla.
     */
    private void eliminarDuenio() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un dueño para eliminar.", "Eliminar dueño", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(filaVista);

        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());

        String nombre = modelo.getValueAt(filaModelo, 1).toString();

        String apellido = modelo.getValueAt(filaModelo, 2).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar al dueño " + nombre + " " + apellido + "?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = duenioDAO.eliminar(id);

        if (resultado) {

            JOptionPane.showMessageDialog(this, "Dueño eliminado correctamente.", "Eliminacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            cargarDuenios();
        } else {

            JOptionPane.showMessageDialog(this, "No se pudo eliminar el dueño.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     /**
     * Filtra las filas de la tabla según el texto ingresado en el campo de
     * búsqueda, aplicando un {@link TableRowSorter}.
     */
    private void filtrar() {

        String texto = campoBusqueda.getText().trim();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

        tabla.setRowSorter(sorter);

        if (texto.isEmpty()) {

            sorter.setRowFilter(null);
        } else {
            
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2, 3));
        }
    }
}