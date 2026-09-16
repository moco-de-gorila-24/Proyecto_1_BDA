package Presentacion.Dialog;

import Presentacion.Componentes.Boton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;

public class DialogConsulta extends JDialog {

    JLabel labelMotivo, labelDiagnostico, labelTratamiento, labelCosto, labelFecha, labelHora, labelEstado;
    JTextArea areaMotivo;
    JTextArea areaDiagnostico;
    JTextArea areaTratamiento;
    Presentacion.Componentes.CampoBusqueda campoCosto;
    Presentacion.Componentes.CampoBusqueda campoIdMascota;
    Presentacion.Componentes.CampoBusqueda campoIdVeterinario;
    boolean aceptado = false;

    DatePicker datePickerFecha;
    TimePicker timePickerHora;
    Boton botonAceptar, botonCancelar;
    JPanel panelContenido, panelBotones;

    public DialogConsulta(Frame owner) {
        super(owner);
        setSize(600, 650);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        iniciarComponentes();
    }

    //utilizando expresiones regulares
    private void configurarValidaciones() {
        // Costo: solo números con hasta 2 decimales (ya validado por CampoBusqueda, aquí reforzamos)
        campoCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = campoCosto.getText();
                if (!texto.isEmpty() && !texto.matches("^\\d*\\.?\\d{0,2}$")) {
                    campoCosto.setText(texto.substring(0, texto.length() - 1));
                }
            }
        });

        // ID Mascota: solo dígitos
        campoIdMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = campoIdMascota.getText();
                if (!texto.matches("^\\d{0,6}$")) {
                    campoIdMascota.setText(texto.replaceAll("[^\\d]", ""));
                }
            }
        });

        // ID Veterinario: solo dígitos
        campoIdVeterinario.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = campoIdVeterinario.getText();
                if (!texto.matches("^\\d{0,6}$")) {
                    campoIdVeterinario.setText(texto.replaceAll("[^\\d]", ""));
                }
            }
        });

        // Motivo (JTextArea): letras, números y espacios (máx 100)
        areaMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = areaMotivo.getText();
                if (!texto.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 ]{0,100}$")) {
                    areaMotivo.setText(texto.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 ]", ""));
                }
            }
        });

        // Diagnóstico (JTextArea): letras, números, espacios y signos básicos (máx 200)
        areaDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = areaDiagnostico.getText();
                if (!texto.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 .,;:()\\-]{0,200}$")) {
                    areaDiagnostico.setText(texto.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 .,;:()\\-]", ""));
                }
            }
        });

        // Tratamiento (JTextArea): letras, números, espacios y signos básicos (máx 200)
        areaTratamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = areaTratamiento.getText();
                if (!texto.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 .,;:()\\-]{0,200}$")) {
                    areaTratamiento.setText(texto.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 .,;:()\\-]", ""));
                }
            }
        });

        botonAceptar.addActionListener(e -> {
            if (validarFormulario()) {
                //aquí se llamaría al DAO para guardar/actualizar
                aceptado = true;
                dispose();
            }
        });

        botonCancelar.addActionListener(e -> dispose());
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        // ID Mascota (relación obligatoria)
        if (campoIdMascota.getText().trim().isEmpty())
            errores.append("- El ID de la mascota es obligatorio.\n");
        else if (!campoIdMascota.getText().matches("^\\d{1,6}$"))
            errores.append("- El ID de la mascota debe ser numérico (1-6 dígitos).\n");

        // ID Veterinario (relación obligatoria)
        if (campoIdVeterinario.getText().trim().isEmpty())
            errores.append("- El ID del veterinario es obligatorio.\n");
        else if (!campoIdVeterinario.getText().matches("^\\d{1,6}$"))
            errores.append("- El ID del veterinario debe ser numérico (1-6 dígitos).\n");

        // Motivo (obligatorio)
        if (areaMotivo.getText().trim().isEmpty())
            errores.append("- El motivo es obligatorio.\n");

        // Costo (obligatorio y formato válido)
        if (campoCosto.getText().trim().isEmpty() ||
            !campoCosto.getText().matches("^\\d+(\\.\\d{1,2})?$"))
            errores.append("- El costo debe ser un número válido (ej. 350.00).\n");

        // Diagnóstico (opcional, pero si hay texto, mínimo 3 caracteres)
        if (!areaDiagnostico.getText().trim().isEmpty() &&
            areaDiagnostico.getText().trim().length() < 3)
            errores.append("- El diagnóstico debe tener al menos 3 caracteres.\n");

        // Tratamiento (opcional, pero si hay texto, mínimo 3 caracteres)
        if (!areaTratamiento.getText().trim().isEmpty() &&
            areaTratamiento.getText().trim().length() < 3)
            errores.append("- El tratamiento debe tener al menos 3 caracteres.\n");

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, errores.toString(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void iniciarComponentes(){
        setLayout(new BorderLayout(5, 5));

        JPanel panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // ID Mascota (relación)
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelContenido.add(new JLabel("ID Mascota:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        campoIdMascota = new Presentacion.Componentes.CampoBusqueda("\\d{1,6}", 10);
        panelContenido.add(campoIdMascota, gbc);

        // ID Veterinario (relación)
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelContenido.add(new JLabel("ID Veterinario:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        campoIdVeterinario = new Presentacion.Componentes.CampoBusqueda("\\d{1,6}", 10);
        panelContenido.add(campoIdVeterinario, gbc);

        // Motivo (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaMotivo = new JTextArea(4, 25);
        areaMotivo.setLineWrap(true);
        areaMotivo.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaMotivo), gbc);

        // Diagnóstico (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaDiagnostico = new JTextArea(4, 25);
        areaDiagnostico.setLineWrap(true);
        areaDiagnostico.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaDiagnostico), gbc);

        // Tratamiento (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(new JLabel("Tratamiento:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaTratamiento = new JTextArea(4, 25);
        areaTratamiento.setLineWrap(true);
        areaTratamiento.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaTratamiento), gbc);

        // Costo (solo números con decimales)
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(new JLabel("Costo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        campoCosto = new Presentacion.Componentes.CampoBusqueda("\\d+(\\.\\d{1,2})?", 15);
        panelContenido.add(campoCosto, gbc);

        add(panelContenido, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        botonCancelar = new Boton("Cancelar");
        botonAceptar = new Boton("Aceptar");
        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);
        add(panelBotones, BorderLayout.SOUTH);
        configurarValidaciones();
}

    private void agregarFila(GridBagConstraints gbc, int fila, JComponent label, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila;
        panelContenido.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        panelContenido.add(campo, gbc);
    }
    public boolean isAceptado() { return aceptado; }
    public String getMotivo() { return areaMotivo.getText(); }
    public String getDiagnostico() { return areaDiagnostico.getText(); }
    public String getTratamiento() { return areaTratamiento.getText(); }
    public String getCosto() { return campoCosto.getText(); }
    public String getIdMascota() { return campoIdMascota.getText(); }
    public String getIdVeterinario() { return campoIdVeterinario.getText(); }
}