package Presentacion.Dialog;

import Presentacion.Componentes.Boton;

import javax.swing.*;
import java.awt.*;

public class DialogConsulta extends JDialog {
    JLabel labelMotivo;
    JLabel labelDiagnostico;
    JLabel labelTratamiento;
    JLabel labelCosto;
    JTextField textFieldMotivo;
    JTextField textFieldDiagnostico;
    JTextField textFieldTratamiento;
    JTextField textFieldCosto;
    Boton botonAceptar;
    Boton botonCancelar;
    JPanel panelContenido;
    JPanel panelBotones;

    public DialogConsulta(Frame owner) {
        super(owner);

        setSize(500, 480);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes(){
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

        botonAceptar = new Boton("Aceptar");
        botonCancelar = new Boton("Cancelar");

        panelContenido = new JPanel(new GridBagLayout());
        panelBotones = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelContenido.add(labelMotivo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelContenido.add(textFieldMotivo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelContenido.add(labelDiagnostico, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelContenido.add(textFieldDiagnostico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelContenido.add(labelTratamiento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelContenido.add(textFieldTratamiento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelContenido.add(labelCosto, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelContenido.add(textFieldCosto, gbc);

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);
        add(panelContenido);
        add(panelBotones, BorderLayout.SOUTH);

    }

}
