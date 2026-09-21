package Presentacion.Paneles;

import DAO.MascotaDAO;
import Dominio.Entidades.Mascota;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogMascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.event.DocumentListener;

public class PanelMascotas extends JPanel {

    private JScrollPane jscrollPane;
    private JPanel panelDerecha;
    private JPanel panelTabla;

    private CampoBusqueda campoBusqueda;

    private DefaultTableModel modelo;
    private Tabla tabla;

    private MascotaDAO mascotaDAO;

    public PanelMascotas() {

        mascotaDAO = new MascotaDAO();

        setLayout(new BorderLayout());

        // Panel de la tabla
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));

        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));

        //busqueda

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));

        campoBusqueda = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*",25);

        campoBusqueda.setToolTipText("Buscar por nombre, especie o raza");

        panelBusqueda.add(new JLabel("Buscar:"));

        panelBusqueda.add(campoBusqueda);

        panelTabla.add(panelBusqueda,BorderLayout.NORTH);

        //Modelo de la tabla

        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("ID Dueño");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Fecha nacimiento");
        modelo.addColumn("Sexo");
        modelo.addColumn("Raza");
        modelo.addColumn("Edad");

        tabla = new Tabla(modelo);

        tabla.setRowHeight(40);

        jscrollPane.setViewportView(tabla);

        panelTabla.add(jscrollPane,BorderLayout.CENTER);

        //Botones

        panelDerecha = new JPanel(new GridBagLayout());

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


        //Cargar datos desde MySQL

        cargarMascotas();

        // FILTRO

        campoBusqueda.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
        });

        //Agregar
        btnAgregar.addActionListener(e -> agregarMascota());

        //Modificar
        btnModificar.addActionListener(e -> modificarMascota());

        // ELIMINAR
        btnEliminar.addActionListener(e -> eliminarMascota());
    }

    //Cargar mascotas

    private void cargarMascotas() {

        modelo.setRowCount(0);

        List<Mascota> mascotas = mascotaDAO.consultarTodos();

        for (Mascota mascota : mascotas) {

            modelo.addRow(new Object[]{mascota.getIdMascota(), mascota.getIdDueno(), mascota.getNombre(), mascota.getEspecie(), mascota.getFechaNacimiento(), mascota.getSexo(), mascota.getRaza(), calcularEdad(mascota.getFechaNacimiento())});
        }
    }

    //Agregar mascota

    private void agregarMascota() {

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);

        DialogMascota dialog = new DialogMascota(frame);

        if (dialog.isAceptado()) {

            Mascota mascota = dialog.getMascota();

            boolean resultado = mascotaDAO.insertar(mascota);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Mascota agregada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarMascotas();
            } else {

                JOptionPane.showMessageDialog(this, "No fue posible agregar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Modificar mascota

    private void modificarMascota() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una mascota para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertimos la fila visual a la fila real por si el usuario está utilizando el filtro.

        int filaModelo = tabla.convertRowIndexToModel(filaVista);

        int idMascota = (int) modelo.getValueAt(filaModelo, 0);

        Mascota mascota = mascotaDAO.consultar(idMascota);

        if (mascota == null) {

            JOptionPane.showMessageDialog(this, "No se encontró la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);

        DialogMascota dialog = new DialogMascota(frame, mascota);

        if (dialog.isAceptado()) {

            Mascota mascotaActualizada = dialog.getMascota();

            boolean resultado = mascotaDAO.actualizar(mascotaActualizada);

            if (resultado) {

                JOptionPane.showMessageDialog(this, "Mascota modificada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                cargarMascotas();
            } else {

                JOptionPane.showMessageDialog(this, "No fue posible modificar la mascota.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Eliminar mascota

    private void eliminarMascota() {

        int filaVista = tabla.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione una mascota para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int filaModelo = tabla.convertRowIndexToModel(filaVista);

        int idMascota = (int) modelo.getValueAt(filaModelo, 0);

        String nombre = modelo.getValueAt(filaModelo, 2).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar a la mascota \"" + nombre + "\"?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = mascotaDAO.eliminar(idMascota);

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Mascota eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cargarMascotas();
        } else {
            JOptionPane.showMessageDialog(this, "No fue posible eliminar la mascota.", "Error",JOptionPane.ERROR_MESSAGE);
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

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto), 2, 3, 5, 6));
        }
    }

    //Calcular edad

    private int calcularEdad(Date fechaNacimiento) {

        if (fechaNacimiento == null) {
            return 0;
        }

        LocalDate nacimiento = fechaNacimiento.toLocalDate();

        LocalDate hoy = LocalDate.now();

        return Period.between(nacimiento, hoy).getYears();
    }
}