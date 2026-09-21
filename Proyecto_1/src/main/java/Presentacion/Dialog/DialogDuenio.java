package Presentacion.Dialog;

import Dominio.Entidades.Duenio;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Diálogo modal para registrar o modificar un dueño. Contiene campos para
 * nombre, apellidos, dirección, email y una lista dinámica de teléfonos.
 *
 * @author ACER
 */
public class DialogDuenio extends JDialog {

    /** Campo de nombre. */
    private CampoBusqueda campoNombre;

    /** Campo de apellido paterno. */
    private CampoBusqueda campoApellidoP;

    /** Campo de apellido materno. */
    private CampoBusqueda campoApellidoM;

    /** Campo de dirección. */
    private CampoBusqueda campoDireccion;

    /** Campo de email. */
    private CampoBusqueda campoEmail;

    /** Campo de teléfono. */
    private CampoBusqueda campoTelefono;

    /** Modelo de la lista de teléfonos. */
    private DefaultListModel<String> modeloTelefonos;

    /** Lista visual de teléfonos. */
    private JList<String> listaTelefonos;

    /** Botón para agregar un teléfono a la lista. */
    private Boton botonAgregarTelefono;

    /** Botón para eliminar un teléfono de la lista. */
    private Boton botonEliminarTelefono;

    /** Botón para aceptar el diálogo. */
    private Boton botonAceptar;

    /** Botón para cancelar el diálogo. */
    private Boton botonCancelar;

    /** Indica si el usuario aceptó el diálogo. */
    private boolean aceptado = false;

    /** Dueño que se está creando o modificando. */
    private Duenio duenio;

    /**
     * Constructor para registrar un nuevo dueño.
     *
     * @param owner ventana padre del diálogo.
     */
    public DialogDuenio(Frame owner) {
        this(owner, null);
    }

    /**
     * Constructor para registrar o modificar un dueño existente.
     *
     * @param owner ventana padre del diálogo.
     * @param duenio dueño a modificar, o {@code null} para uno nuevo.
     */
    public DialogDuenio(Frame owner, Duenio duenio) {
        super(owner, true);

        this.duenio = duenio;

        setTitle(duenio == null ? "Agregar Dueño" : "Modificar Dueño");
        setSize(600, 600);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        iniciarComponentes();

        if (duenio != null) {
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
        gbc.insets = new Insets(5, 5, 5, 5);
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

        //Direccion

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Direccion:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoDireccion = new CampoBusqueda(".*", 25);

        panelContenido.add(campoDireccion, gbc);

        //Email

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoEmail = new CampoBusqueda("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", 25);

        panelContenido.add(campoEmail, gbc);

        //Telefono

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Telefono:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        JPanel panelTelefono = new JPanel(new BorderLayout(5, 5));

        campoTelefono = new CampoBusqueda("\\d{10}", 15);

        botonAgregarTelefono = new Boton("Agregar");

        panelTelefono.add(campoTelefono, BorderLayout.CENTER);
        panelTelefono.add(botonAgregarTelefono, BorderLayout.EAST);

        panelContenido.add(panelTelefono, gbc);

        //Lista de telefonos

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        panelContenido.add(new JLabel("Telefonos:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        modeloTelefonos = new DefaultListModel<>();
        listaTelefonos = new JList<>(modeloTelefonos);

        JScrollPane scrollTelefonos = new JScrollPane(listaTelefonos);
        scrollTelefonos.setPreferredSize(new Dimension(300, 100));

        panelContenido.add(scrollTelefonos, gbc);

        //Eliminar telefonos

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        botonEliminarTelefono = new Boton("Eliminar telefono");

        panelContenido.add(botonEliminarTelefono, gbc);
        add(panelContenido, BorderLayout.CENTER);

        //Botones

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        botonCancelar = new Boton("Cancelar");
        botonAceptar = new Boton("Aceptar");

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);

        add(panelBotones, BorderLayout.SOUTH);

        botonAgregarTelefono.addActionListener(e -> agregarTelefono());

        botonEliminarTelefono.addActionListener(e -> eliminarTelefono());

        botonAceptar.addActionListener(e -> aceptar());

        botonCancelar.addActionListener(e -> {
            aceptado = false;
            dispose();
        });
    }

    /**
     * Valida y agrega el teléfono actual a la lista de teléfonos del dueño.
     */
    private void agregarTelefono() {

        String telefono = campoTelefono.getText().trim();

        if (!telefono.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(this, "El telefono debe contener exactamente 10 digitos.", "Telefono invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (modeloTelefonos.contains(telefono)) {

            JOptionPane.showMessageDialog(this, "Ese telefono ya fue agregado.", "Telefono duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTelefonos.addElement(telefono);
        campoTelefono.setText("");
    }

    /**
     * Elimina el teléfono seleccionado de la lista.
     */
    private void eliminarTelefono() {

        int indice = listaTelefonos.getSelectedIndex();

        if (indice == -1) {

            JOptionPane.showMessageDialog(this, "Seleccione un telefono para eliminar.", "Telefono", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTelefonos.remove(indice);
    }

    /**
     * Valida los campos del formulario, construye el objeto {@link Duenio}
     * y marca el diálogo como aceptado si todo es correcto.
     */
    private void aceptar() {

        if (campoNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }

        if (campoApellidoP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El apellido paterno es obligatorio.");
            return;
        }

        if (campoApellidoM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El apellido materno es obligatorio.");
            return;
        }

        if (campoDireccion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La direccion es obligatoria.");
            return;
        }

        if (campoEmail.getText().trim().isEmpty() || !campoEmail.esValido()) {
            JOptionPane.showMessageDialog(this, "Ingrese un correo electronico valido.");
            return;
        }

        if (modeloTelefonos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un telefono.");
            return;
        }

        List<String> telefonos = new ArrayList<>();

        for (int i = 0; i < modeloTelefonos.size(); i++) {
            telefonos.add(modeloTelefonos.getElementAt(i));
        }

        if (duenio == null) {
            duenio = new Duenio();
        }

        duenio.setNombre(campoNombre.getText().trim());
        duenio.setApellidoP(campoApellidoP.getText().trim());
        duenio.setApellidoM(campoApellidoM.getText().trim());
        duenio.setDireccion(campoDireccion.getText().trim());
        duenio.setEmail(campoEmail.getText().trim());
        duenio.setTelefonos(telefonos);

        aceptado = true;

        dispose();
    }

    /**
     * Carga los datos de un dueño existente en los campos del formulario,
     * para el modo modificación.
     */
    private void cargarDatos() {

        campoNombre.setText(duenio.getNombre());
        campoApellidoP.setText(duenio.getApellidoP());
        campoApellidoM.setText(duenio.getApellidoM());
        campoDireccion.setText(duenio.getDireccion());
        campoEmail.setText(duenio.getEmail());

        modeloTelefonos.clear();

        if (duenio.getTelefonos() != null) {

            for (String telefono : duenio.getTelefonos()) {
                modeloTelefonos.addElement(telefono);
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
     * Devuelve el dueño construido o modificado en el diálogo.
     *
     * @return el objeto {@link Duenio}.
     */
    public Duenio getDuenio() {
        return duenio;
    }
}