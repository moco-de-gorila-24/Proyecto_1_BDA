package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.Tabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelDuenios extends JPanel {
    JScrollPane jscrollPane;
    JPanel panelDerecha;
    JPanel panelTabla;
    Boton boton1;
    Boton boton2;
    Boton boton3;
    Boton boton4;


    public PanelDuenios(){
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridBagLayout());
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        boton1 = new Boton("Texto 1");
        boton2 = new Boton("Texto 2");
        boton3 = new Boton("Texto 3");
        boton4 = new Boton("Texto 4");

        Mostrar();
    }

    public void Mostrar(){
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla tabla = new Tabla(modelo);
        tabla.setRowHeight(50);

        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefonos");
        modelo.addColumn("Email");
        modelo.addRow(new Object[]{"Sebastian", "Escalante", "Ramirez", "Obregonyork", "6441234567", "ostionsito@email.com"});
        jscrollPane.setViewportView(tabla);

        panelDerecha.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 5, 15, 8);

        gbc.gridy = 0;
        panelDerecha.add(boton1, gbc);

        gbc.gridy = 1;
        panelDerecha.add(boton2, gbc);

        gbc.gridy = 2;
        panelDerecha.add(boton3, gbc);

        gbc.gridy = 3;
        panelDerecha.add(boton4, gbc);

        add(panelDerecha, BorderLayout.EAST);
        add(panelTabla, BorderLayout.CENTER);



    }
}
