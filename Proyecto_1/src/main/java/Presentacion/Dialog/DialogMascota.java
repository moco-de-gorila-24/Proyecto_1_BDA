package Presentacion.Dialog;

import DAO.DuenioDAO;
import Dominio.Entidades.Duenio;
import Dominio.Entidades.Mascota;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

/**
 * Diálogo modal para registrar o modificar una mascota. Contiene campos para
 * nombre, especie, fecha de nacimiento, sexo, raza y selección de dueño.
 *
 * @author ACER
 */
public class DialogMascota extends JDialog {

    /** Campo de nombre de la mascota. */
    private CampoBusqueda campoNombre;

    /** Campo de especie. */
    private CampoBusqueda campoEspecie;

    /** Campo de raza. */
    private CampoBusqueda campoRaza;

    /** Selector de fecha de nacimiento. */
    private DatePicker datePickerFechaNacimiento;

    /** Combo de sexo. */
    private JComboBox<String> comboSexo;

    /** Combo de dueños. */
    private JComboBox<Duenio> comboDueno;

    /** Botón para aceptar el diálogo. */
    private Boton botonAceptar;

    /** Botón para cancelar el diálogo. */
    private Boton botonCancelar;

    /** Indica si el usuario aceptó el diálogo. */
    private boolean aceptado = false;

    /** Mascota que se está creando o modificando. */
    private Mascota mascota;

    /**
     * Constructor para registrar una nueva mascota.
     *
     * @param owner ventana padre del diálogo.
     */
    public DialogMascota(Frame owner) {
        this(owner, null);
    }

    /**
     * Constructor para registrar o modificar una mascota existente.
     *
     * @param owner ventana padre del diálogo.
     * @param mascota mascota a modificar, o {@code null} para una nueva.
     */
    public DialogMascota(Frame owner, Mascota mascota) {
        super(owner, true);

        this.mascota = mascota;

        setTitle(mascota == null ? "Agregar Mascota" : "Modificar Mascota");
        setSize(500, 500);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarDuenios();

        if (mascota != null) {
            cargarDatosMascota();
        }

        setVisible(true);
    }

    /**
     * Construye todos los componentes gráficos del diálogo y configura
     * los eventos de los botones.
     */
    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        //Nombre

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoNombre = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}",20);

        panelContenido.add(campoNombre, gbc);

        //Especie

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Especie:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoEspecie = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,30}", 20);

        panelContenido.add(campoEspecie, gbc);

        //Fecha de nacimiento

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Fecha de nacimiento:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        datePickerFechaNacimiento = new DatePicker();

        panelContenido.add(datePickerFechaNacimiento,gbc);

        //Sexo

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Sexo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        comboSexo = new JComboBox<>(new String[]{"M", "F"});

        panelContenido.add(comboSexo, gbc);

        //Raza

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Raza:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoRaza = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{1,50}", 20);

        panelContenido.add(campoRaza, gbc);

        //Dueño

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Dueño:"),gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        comboDueno = new JComboBox<>();

        panelContenido.add(comboDueno, gbc);

        add(panelContenido, BorderLayout.CENTER);

        //Botones

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        botonCancelar = new Boton("Cancelar");
        botonAceptar = new Boton("Aceptar");

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);

        add(panelBotones, BorderLayout.SOUTH);

        botonCancelar.addActionListener(e -> {
            aceptado = false;
            dispose();
        });

        botonAceptar.addActionListener(e -> aceptar());

    }

    /**
     * Carga todos los dueños en el combo de selección, con un renderer
     * personalizado que muestra id, nombre y apellidos.
     */
    private void cargarDuenios() {

        DuenioDAO duenioDAO = new DuenioDAO();

        List<Duenio> duenios = duenioDAO.consultarTodos();

        comboDueno.removeAllItems();

        for (Duenio duenio : duenios) {
            comboDueno.addItem(duenio);
        }

        comboDueno.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Duenio) {

                    Duenio duenio = (Duenio) value;

                    setText(duenio.getIdDueno() + " - " + duenio.getNombre() + " " + duenio.getApellidoP() + " " + duenio.getApellidoM());
                }

                return this;
            }
        });
    }

    /**
     * Carga los datos de una mascota existente en los campos del formulario,
     * para el modo modificación.
     */
    private void cargarDatosMascota() {

        campoNombre.setText(mascota.getNombre());
        campoEspecie.setText(mascota.getEspecie());
        campoRaza.setText(mascota.getRaza());

        if (mascota.getFechaNacimiento() != null) {

            datePickerFechaNacimiento.setDate(mascota.getFechaNacimiento().toLocalDate());
        }

        if (mascota.getSexo() != null) {

            comboSexo.setSelectedItem(mascota.getSexo());
        }

        for (int i = 0; i < comboDueno.getItemCount(); i++) {

            Duenio duenio = comboDueno.getItemAt(i);

            if (duenio.getIdDueno() == mascota.getIdDueno()) {

                comboDueno.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Valida los campos del formulario, construye el objeto {@link Mascota}
     * y marca el diálogo como aceptado si todo es correcto.
     */
    private void aceptar() {

        if (campoNombre.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la mascota.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            campoNombre.requestFocus();
            return;
        }

        if (!campoNombre.esValido()) {

            JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            campoNombre.requestFocus();
            return;
        }

        if (campoEspecie.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Ingrese la especie de la mascota.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            campoEspecie.requestFocus();
            return;
        }

        if (!campoEspecie.esValido()) {

            JOptionPane.showMessageDialog(this, "La especie solo debe contener letras.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            campoEspecie.requestFocus();
            return;
        }

        if (datePickerFechaNacimiento.getDate() == null) {

            JOptionPane.showMessageDialog(this, "Seleccione la fecha de nacimiento.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (campoRaza.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Ingrese la raza de la mascota.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            campoRaza.requestFocus();
            return;
        }

        if (!campoRaza.esValido()) {

            JOptionPane.showMessageDialog(this, "La raza solo debe contener letras.","Dato inválido",JOptionPane.WARNING_MESSAGE);
            campoRaza.requestFocus();
            return;
        }

        if (comboDueno.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "Seleccione un dueño.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (mascota == null) {
            mascota = new Mascota();
        }

        mascota.setNombre(campoNombre.getText().trim());

        mascota.setEspecie(campoEspecie.getText().trim());

        mascota.setFechaNacimiento(Date.valueOf(datePickerFechaNacimiento.getDate()));

        mascota.setSexo(comboSexo.getSelectedItem().toString());

        mascota.setRaza(campoRaza.getText().trim());

        Duenio duenioSeleccionado = (Duenio) comboDueno.getSelectedItem();

        mascota.setIdDueno(duenioSeleccionado.getIdDueno());

        aceptado = true;
        dispose();
    }

    /**
     * Indica si el usuario aceptó el diálogo.
     *
     * @return {@code true} si se aceptó, {@code false} en caso contrario.
     */
    public boolean isAceptado() {
        return aceptado;
    }

    /**
     * Devuelve la mascota construida o modificada en el diálogo.
     *
     * @return el objeto {@link Mascota}.
     */
    public Mascota getMascota() {
        return mascota;
    }
}