package Presentacion;

import Presentacion.Componentes.Boton;
import Presentacion.Paneles.PanelDuenios;

import javax.swing.*;
import java.awt.*;

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
        panelD.setBackground(Color.GRAY);
        add(panelD, BorderLayout.CENTER );
        add(PanelNorte, BorderLayout.NORTH);
        add(PanelSur, BorderLayout.SOUTH);



        setVisible(true);
    }
}
