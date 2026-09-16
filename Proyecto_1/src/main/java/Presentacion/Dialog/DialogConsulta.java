package Presentacion.Dialog;

import Presentacion.Componentes.Boton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;

public class DialogConsulta extends JDialog {

    JLabel labelMotivo, labelDiagnostico, labelTratamiento, labelCosto, labelFecha, labelHora, labelEstado;
    JTextField textFieldMotivo, textFieldDiagnostico, textFieldTratamiento, textFieldCosto;

    DatePicker datePickerFecha;
    TimePicker timePickerHora;
    Boton botonAceptar, botonCancelar;
    JPanel panelContenido, panelBotones;

    public DialogConsulta(Frame owner) {
        super(owner);
        setSize(520, 560);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        iniciarComponentes();
        setVisible(true);
    }

    //utilizando expresiones regulares
    private void configurarValidaciones() {
        textFieldCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = textFieldCosto.getText();
                if (!texto.isEmpty() && !texto.matches("^\\d*\\.?\\d{0,2}$")) {
                    textFieldCosto.setText(texto.substring(0, texto.length() - 1));
                }
            }
        });

        textFieldMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = textFieldMotivo.getText();
                if (!texto.matches("^[a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 ]{0,100}$")) {
                    textFieldMotivo.setText(texto.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ0-9 ]", ""));
                }
            }
        });

        botonAceptar.addActionListener(e -> {
            if (validarFormulario()) {
                //aquí se llamaría al DAO para guardar/actualizar
                dispose();
            }
        });

        botonCancelar.addActionListener(e -> dispose());
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        if (textFieldMotivo.getText().trim().isEmpty())
            errores.append("- El motivo es obligatorio.\n");

        if (textFieldCosto.getText().trim().isEmpty() ||
            !textFieldCosto.getText().matches("^\\d+(\\.\\d{1,2})?$"))
            errores.append("- El costo debe ser un número válido (ej. 350.00).\n");

        if (datePickerFecha.getDate() == null)
            errores.append("- Debes seleccionar una fecha.\n");

        if (timePickerHora.getTime() == null)
            errores.append("- Debes seleccionar una hora.\n");

        if (datePickerFecha.getDate() != null &&
            datePickerFecha.getDate().isBefore(java.time.LocalDate.now()))
            errores.append("- La fecha no puede ser anterior a hoy.\n");

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, errores.toString(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void iniciarComponentes() {
        labelMotivo = new JLabel("Motivo");
        labelMotivo.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldMotivo = new JTextField(20);

        labelDiagnostico = new JLabel("Diagnostico");
        labelDiagnostico.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldDiagnostico = new JTextField(20);

        labelTratamiento = new JLabel("Tratamiento");
        labelTratamiento.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldTratamiento = new JTextField(20);

        labelCosto = new JLabel("Costo");
        labelCosto.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldCosto = new JTextField(20);

        // --- Fecha y hora con LGoodDatePicker ---
        labelFecha = new JLabel("Fecha");
        labelFecha.setFont(new Font("Arial", Font.BOLD, 18));
        datePickerFecha = new DatePicker();

        labelHora = new JLabel("Hora");
        labelHora.setFont(new Font("Arial", Font.BOLD, 18));
        timePickerHora = new TimePicker();

        botonAceptar = new Boton("Aceptar");
        botonCancelar = new Boton("Cancelar");

        panelContenido = new JPanel(new GridBagLayout());
        panelBotones = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        agregarFila(gbc, fila++, labelMotivo, textFieldMotivo);
        agregarFila(gbc, fila++, labelDiagnostico, textFieldDiagnostico);
        agregarFila(gbc, fila++, labelTratamiento, textFieldTratamiento);
        agregarFila(gbc, fila++, labelCosto, textFieldCosto);
        agregarFila(gbc, fila++, labelFecha, datePickerFecha);
        agregarFila(gbc, fila++, labelHora, timePickerHora);

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);
        add(panelContenido);
        add(panelBotones, BorderLayout.SOUTH);

        configurarValidaciones();
    }

    private void agregarFila(GridBagConstraints gbc, int fila, JComponent label, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila;
        panelContenido.add(label, gbc);
        gbc.gridx = 1; gbc.gridy = fila;
        panelContenido.add(campo, gbc);
    }
}