package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.Tabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelDuenios extends JPanel {
    JScrollPane jscrollPane;
    JPanel panelDerecha;
    Boton boton1;
    Boton boton2;
    Boton boton3;
    Boton boton4;


    public PanelDuenios(){
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridBagLayout());
        boton1 = new Boton("Texto 1");
        boton2 = new Boton("Texto 2");
        boton3 = new Boton("Texto 3");
        boton4 = new Boton("Texto 4");

        Mostrar();
    }

    public void Mostrar(){
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla tabla = new Tabla(modelo);
        tabla.setRowHeight(140);

        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefonos");
        modelo.addColumn("Email");
        jscrollPane.setViewportView(tabla);

        panelDerecha.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(30, 5, 30, 50);

        gbc.gridy = 0;
        panelDerecha.add(boton1, gbc);

        gbc.gridy = 1;
        panelDerecha.add(boton2, gbc);

        gbc.gridy = 2;
        panelDerecha.add(boton3, gbc);

        gbc.gridy = 3;
        panelDerecha.add(boton4, gbc);



        add(panelDerecha, BorderLayout.EAST);
        add(jscrollPane, BorderLayout.CENTER);
    }
}
