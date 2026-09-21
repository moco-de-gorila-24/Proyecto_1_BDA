package Presentacion.Dialog;

import Dominio.Entidades.Veterinario;
import Dominio.Especialidades.Especialidad;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para registrar o modificar un veterinario. Contiene campos
 * para nombre, apellidos, cédula profesional, especialidad y teléfono.
 *
 * @author ACER
 */
public class DialogVeterinario extends JDialog {

    /** Campo de nombre. */
    private CampoBusqueda campoNombre;

    /** Campo de apellido paterno. */
    private CampoBusqueda campoApellidoP;

    /** Campo de apellido materno. */
    private CampoBusqueda campoApellidoM;

    /** Campo de cédula profesional. */
    private CampoBusqueda campoCedula;

    /** Campo de teléfono. */
    private CampoBusqueda campoTelefono;

    /** Combo de especialidades. */
    private JComboBox<Especialidad> comboEspecialidad;

    /** Botón para aceptar el diálogo. */
    private Boton botonAceptar;

    /** Botón para cancelar el diálogo. */
    private Boton botonCancelar;

    /** Indica si el usuario aceptó el diálogo. */
    private boolean aceptado = false;

    /** Veterinario que se está creando o modificando. */
    private Veterinario veterinario;

    /**
     * Constructor para registrar un nuevo veterinario.
     *
     * @param owner ventana padre del diálogo.
     */
    public DialogVeterinario(Frame owner) {
        this(owner, null);
    }

    /**
     * Constructor para registrar o modificar un veterinario existente.
     *
     * @param owner ventana padre del diálogo.
     * @param veterinario veterinario a modificar, o {@code null} para uno nuevo.
     */
    public DialogVeterinario(Frame owner, Veterinario veterinario) {

        super(owner, true);

        this.veterinario = veterinario;

        setTitle(veterinario == null ? "Agregar Veterinario" : "Modificar Veterinario");

        setSize(500, 450);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        iniciarComponentes();

        if (veterinario != null) {
            cargarDatos();
        }

        setVisible(true);
    }

    /**
     * Construye todos los componentes gráficos del diálogo y configura
     * los eventos de los botones.
     */
    public void iniciarComponentes() {

        setLayout(new BorderLayout(5, 5));

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

        campoNombre = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+", 25);

        panelContenido.add(campoNombre, gbc);

        //Apellido paterno

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Apellido Paterno:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoApellidoP = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+", 25);

        panelContenido.add(campoApellidoP, gbc);

        //Apellido materno

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Apellido Materno:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoApellidoM = new CampoBusqueda("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+", 25);

        panelContenido.add(campoApellidoM, gbc);

        //Cedula profesional

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Cédula Profesional:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoCedula = new CampoBusqueda("[A-Z]{0,3}\\d{0,6}", 25);

        panelContenido.add(campoCedula, gbc);

        //Especialidad

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Especialidad:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        comboEspecialidad = new JComboBox<>(Especialidad.values());

        panelContenido.add(comboEspecialidad, gbc);

        //Telefono

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Telefono:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoTelefono = new CampoBusqueda("\\d{10}", 25);

        panelContenido.add(campoTelefono, gbc);

        //Agregar panel

        add(panelContenido, BorderLayout.CENTER);

        //Botones

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        botonCancelar = new Boton("Cancelar");
        botonAceptar = new Boton("Aceptar");

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);

        add(panelBotones, BorderLayout.SOUTH);

        //Eventos

        botonAceptar.addActionListener(e -> aceptar());

        botonCancelar.addActionListener(e -> {
            aceptado = false;
            dispose();
        });
    }

    /**
     * Valida los campos del formulario, construye el objeto {@link Veterinario}
     * y marca el diálogo como aceptado si todo es correcto.
     */
    private void aceptar() {

        //Validar nombre

        if (campoNombre.getText().trim().isEmpty() || !campoNombre.esValido()) {

            JOptionPane.showMessageDialog(this, "Ingrese un nombre valido.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Validar apellido paterno

        if (campoApellidoP.getText().trim().isEmpty() || !campoApellidoP.esValido()) {

            JOptionPane.showMessageDialog(this, "Ingrese un apellido paterno valido.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Validar apellido materno

        if (campoApellidoM.getText().trim().isEmpty() || !campoApellidoM.esValido()) {

            JOptionPane.showMessageDialog(this, "Ingrese un apellido materno valido.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Validar cedula

        if (campoCedula.getText().trim().isEmpty() || !campoCedula.getText().trim().matches("[A-Z]{3}\\d{6}")) {

            JOptionPane.showMessageDialog(this, "Ingrese una cedula profesional valida.\n" + "Formato: ABC123456", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            campoCedula.requestFocus();
            return;
        }

        //Validar telefono

        if (campoTelefono.getText().trim().isEmpty() || !campoTelefono.esValido()) {

            JOptionPane.showMessageDialog(this, "El telefono debe contener exactamente 10 digitos.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Validar especialidad

        if (comboEspecialidad.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "Seleccione una especialidad.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Crear veterinario si es nuevo

        if (veterinario == null) {
            veterinario = new Veterinario();
        }

        //Guardar datos

        veterinario.setNombre(campoNombre.getText().trim());

        veterinario.setApellidoP(campoApellidoP.getText().trim());

        veterinario.setApellidoM(campoApellidoM.getText().trim());

        veterinario.setCedulaProfesional(campoCedula.getText().trim());

        Especialidad especialidad = (Especialidad) comboEspecialidad.getSelectedItem();

        veterinario.setEspecialidad(especialidad.toString());

        veterinario.setTelefono(campoTelefono.getText().trim());

        aceptado = true;

        dispose();
    }

    /**
     * Carga los datos de un veterinario existente en los campos del formulario,
     * para el modo modificación.
     */
    private void cargarDatos() {

        campoNombre.setText(veterinario.getNombre());

        campoApellidoP.setText(veterinario.getApellidoP());

        campoApellidoM.setText(veterinario.getApellidoM());

        campoCedula.setText(veterinario.getCedulaProfesional());

        campoTelefono.setText(veterinario.getTelefono());

        String especialidadGuardada = veterinario.getEspecialidad();

        for (int i = 0; i < comboEspecialidad.getItemCount(); i++) {

            Especialidad especialidad = comboEspecialidad.getItemAt(i);

            if (especialidad.toString().equalsIgnoreCase(especialidadGuardada)) {

                comboEspecialidad.setSelectedIndex(i);
                break;
            }
        }
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
     * Devuelve el veterinario construido o modificado en el diálogo.
     *
     * @return el objeto {@link Veterinario}.
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }
}