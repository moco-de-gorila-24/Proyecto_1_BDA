package Presentacion.Paneles;

import DAO.ConsultaDAO;
import DAO.MascotaDAO;
import DAO.VeterinarioDAO;
import Dominio.Entidades.Consulta;
import Dominio.Entidades.Mascota;
import Dominio.Entidades.Veterinario;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogConsulta;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * Panel que muestra el listado de consultas veterinarias y permite realizar
 * operaciones CRUD sobre ellas (agregar, modificar y eliminar), además de
 * filtrarlas mediante un campo de búsqueda en vivo.
 *
 * @author ACER
 */
public class PanelConsultas extends JPanel {

    /** Panel con scroll que contiene la tabla de consultas. */
    private JScrollPane jscrollPane;

    /** Panel lateral que contiene los botones de acción. */
    private JPanel panelDerecha;

    /** Panel que contiene el campo de búsqueda y la tabla. */
    private JPanel panelTabla;

    /** Campo de búsqueda en vivo. */
    private CampoBusqueda campoBusqueda;

    /** Modelo de datos de la tabla. */
    private DefaultTableModel modelo;

    /** Tabla que muestra las consultas. */
    private Tabla tabla;

    /** DAO para operaciones sobre consultas. */
    private ConsultaDAO consultaDAO;

    /** DAO para consultar mascotas relacionadas. */
    private MascotaDAO mascotaDAO;

    /** DAO para consultar veterinarios relacionados. */
    private VeterinarioDAO veterinarioDAO;

    /** Formato utilizado para mostrar la fecha y hora. */
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Constructor que inicializa los DAOs, construye la interfaz gráfica,
     * carga las consultas desde la base de datos y configura los eventos.
     */
    public PanelConsultas() {

        consultaDAO = new ConsultaDAO();
        mascotaDAO = new MascotaDAO();
        veterinarioDAO = new VeterinarioDAO();

        setLayout(new BorderLayout());

        // TABLA

        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(900, 500));

        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        //Busqueda

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        campoBusqueda = new CampoBusqueda(".*", 5);
        campoBusqueda.setToolTipText("Buscar por motivo, diagnostico, tratamiento, mascota o veterinario");

        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(campoBusqueda);

        panelTabla.add(panelBusqueda, BorderLayout.NORTH);

        // MODELO

        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Fecha y hora");
        modelo.addColumn("Motivo");
        modelo.addColumn("Diagnóstico");
        modelo.addColumn("Tratamiento");
        modelo.addColumn("Costo");
        modelo.addColumn("Mascota");
        modelo.addColumn("Veterinario");

        tabla = new Tabla(modelo);
        tabla.setRowHeight(40);

        jscrollPane.setViewportView(tabla);

        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        //Botones

        panelDerecha = new JPanel(new GridBagLayout());

        Boton btnAgregar = new Boton("Agregar Consulta");
        Boton btnModificar = new Boton("Modificar Consulta");
        Boton btnEliminar = new Boton("Eliminar Consulta");

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

        //Agregar al panel
        add(panelDerecha, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        //Cargar consultas
        cargarConsultas();

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

        //Eventos
        btnAgregar.addActionListener(e -> agregarConsulta());
        btnModificar.addActionListener(e -> modificarConsulta());
        btnEliminar.addActionListener(e -> eliminarConsulta());
    }

    /**
     * Carga todas las consultas desde la base de datos y las inserta en el modelo
     * de la tabla, sustituyendo los IDs de mascota y veterinario por sus nombres.
     */
    private void cargarConsultas() {

        modelo.setRowCount(0);
        List<Consulta> consultas = consultaDAO.consultarTodos();

        for (Consulta consulta : consultas) {

            String fechaHora = "";

            if (consulta.getFechaHora() != null) {
                fechaHora = formatoFecha.format(consulta.getFechaHora());
            }

            String mascota = obtenerNombreMascota(consulta.getIdMascota());
            String veterinario = obtenerNombreVeterinario(consulta.getIdVeterinario());

            modelo.addRow(new Object[]{consulta.getIdConsulta(), fechaHora, consulta.getMotivo(), consulta.getDiagnostico(), consulta.getTratamiento(), consulta.getCosto(), mascota, veterinario});
        }
    }

    /**
     * Obtiene el nombre de una mascota a partir de su identificador.
     *
     * @param idMascota identificador de la mascota.
     * @return el nombre de la mascota, o "No encontrada" si no existe.
     */
    private String obtenerNombreMascota(int idMascota) {

        Mascota mascota = mascotaDAO.consultar(idMascota);

        if (mascota == null) {
            return "No encontrada";
        }

        return mascota.getNombre();
    }

    /**
     * Obtiene el nombre completo de un veterinario a partir de su identificador.
     *
     * @param idVeterinario identificador del veterinario.
     * @return nombre y apellido paterno del veterinario, o "No encontrado" si no existe.
     */
    private String obtenerNombreVeterinario(int idVeterinario) {

        Veterinario veterinario = veterinarioDAO.consultar(idVeterinario);

        if (veterinario == null) {
            return "No encontrado";
        }

        return veterinario.getNombre() + " " + veterinario.getApellidoP();
    }

    /**
     * Abre el diálogo de registro de consulta. Si el usuario acepta, inserta
     * la consulta en la base de datos y recarga la tabla.
     */
    private void agregarConsulta() {

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
        DialogConsulta dialog = new DialogConsulta(frame);

        if (dialog.isAceptado()) {

            Consulta consulta = dialog.getConsulta();
            boolean resultado = consultaDAO.insertar(consulta);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Consulta agregada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarConsultas();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Abre el diálogo de modificación con los datos de la consulta seleccionada.
     * Si el usuario acepta, actualiza la consulta en la base de datos y recarga
     * la tabla.
     */
    private void modificarConsulta() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una consulta para modificar.", "Consulta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(filaVista);
        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());
        Consulta consulta = consultaDAO.consultar(id);

        if (consulta == null) {
            JOptionPane.showMessageDialog(this, "No se encontro la consulta seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
        DialogConsulta dialog = new DialogConsulta(frame, consulta);

        if (dialog.isAceptado()) {

            Consulta consultaActualizada = dialog.getConsulta();
            boolean resultado = consultaDAO.actualizar(consultaActualizada);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Consulta actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarConsultas();
            } else {

                JOptionPane.showMessageDialog(this, "No se pudo actualizar la consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Elimina la consulta seleccionada, previa confirmación del usuario, y
     * recarga la tabla.
     */ 
    private void eliminarConsulta() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una consulta para eliminar.", "Consulta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(filaVista);
        int id = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());
        String motivo = modelo.getValueAt(filaModelo, 2).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar la consulta?\n\n" + "Motivo: " + motivo, "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = consultaDAO.eliminar(id);

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Consulta eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cargarConsultas();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la consulta.", "Error", JOptionPane.ERROR_MESSAGE);
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
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2, 3, 4, 6, 7));
        }
    }
}