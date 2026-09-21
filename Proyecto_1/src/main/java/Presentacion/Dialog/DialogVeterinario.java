package Presentacion.Dialog;

import Dominio.Entidades.Veterinario;
import Dominio.Especialidades.Especialidad;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;

import javax.swing.*;
import java.awt.*;

public class DialogVeterinario extends JDialog {

    private CampoBusqueda campoNombre;
    private CampoBusqueda campoApellidoP;
    private CampoBusqueda campoApellidoM;
    private CampoBusqueda campoCedula;
    private CampoBusqueda campoTelefono;

    private JComboBox<Especialidad> comboEspecialidad;

    private Boton botonAceptar;
    private Boton botonCancelar;

    private boolean aceptado = false;
    private Veterinario veterinario;

    //Constructor para agregar

    public DialogVeterinario(Frame owner) {
        this(owner, null);
    }

    //Constructor para agregar/modificar

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

    //Componentes

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

    //Aceptar

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

    //Cargar datos para modificar

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

    //Getters

    public boolean isAceptado() {
        return aceptado;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }
}