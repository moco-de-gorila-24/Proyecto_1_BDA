package Presentacion.Dialog;

import Presentacion.Componentes.Boton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;

public class DialogDuenio extends JDialog{
    Label labelMotivo, labelDiagnostico, labelTratamiento, labelCosto, labelFecha, labelHora, labelEstado;
    JTextArea areaApellidoPaterno;
    JTextArea areaApellidoMaterno;
    JTextArea areaDireccion;
    Presentacion.Componentes.CampoBusqueda campoCosto;
    Presentacion.Componentes.CampoBusqueda campoIdDuenio;
    Presentacion.Componentes.CampoBusqueda campoNombre;
    boolean aceptado = false;

    DatePicker datePickerFecha;
    TimePicker timePickerHora;
    Boton botonAceptar, botonCancelar;
    JPanel panelContenido, panelBotones;

    public DialogDuenio(Frame owner) {
        super(owner);
        setSize(600, 650);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes(){
        setLayout(new BorderLayout(5, 5));

        JPanel panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelContenido.add(new JLabel("ID Duenio:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        campoIdDuenio = new Presentacion.Componentes.CampoBusqueda("\\d{1,6}", 10);
        panelContenido.add(campoIdDuenio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelContenido.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        campoNombre = new Presentacion.Componentes.CampoBusqueda("\\d{1,6}", 10);
        panelContenido.add(campoNombre, gbc);

        // Motivo (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        panelContenido.add(new JLabel("Apellido Paterno:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaApellidoPaterno = new JTextArea(4, 25);
        areaApellidoPaterno.setLineWrap(true);
        areaApellidoPaterno.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaApellidoPaterno), gbc);

        // Diagnóstico (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(new JLabel("Apellido Materno:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaApellidoMaterno = new JTextArea(4, 25);
        areaApellidoMaterno.setLineWrap(true);
        areaApellidoMaterno.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaApellidoMaterno), gbc);

        // Tratamiento (JTextArea grande)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelContenido.add(new JLabel("Direccion:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.25;
        areaDireccion = new JTextArea(4, 25);
        areaDireccion.setLineWrap(true);
        areaDireccion.setWrapStyleWord(true);
        panelContenido.add(new JScrollPane(areaDireccion), gbc);

        // Costo (solo números con decimales)
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(new JLabel("Telefono:"), gbc);
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

    }
}
