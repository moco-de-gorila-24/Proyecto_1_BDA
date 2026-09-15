package Presentacion;

import Presentacion.Componentes.Boton;
import Presentacion.Paneles.PanelConsultas;
import Presentacion.Paneles.PanelDuenios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Main frame");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void Iniciar(){
        JPanel PanelNorte = new JPanel();
        JPanel PanelSur = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        Boton botonDueño = new Boton("Dueños");
        Boton botonMascota = new Boton("Mascotas");
        Boton botonVeterinario = new Boton("Veterinarios");
        Boton botonConsulta = new Boton("Consultas");

        botonDueño.setPreferredSize(new Dimension(140, 50));
        botonMascota.setPreferredSize(new Dimension(140, 50));
        botonVeterinario.setPreferredSize(new Dimension(140, 50));
        botonConsulta.setPreferredSize(new Dimension(140, 50));

        Boton botonGuardar = new Boton("Guardar");
        Boton botonBorrar = new Boton("Borrar");
        Boton botonCancelar = new Boton("Cancelar");
        Boton botonEditar = new Boton("Editar");

        PanelNorte.add(botonDueño);
        PanelNorte.add(botonMascota);
        PanelNorte.add(botonVeterinario);
        PanelNorte.add(botonConsulta);

        PanelDuenios panelD = new PanelDuenios();
        PanelConsultas panelConsultas = new PanelConsultas();
        add(panelConsultas, BorderLayout.CENTER );
        add(PanelNorte, BorderLayout.NORTH);
        add(PanelSur, BorderLayout.SOUTH);

        botonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        setVisible(true);
    }
}
