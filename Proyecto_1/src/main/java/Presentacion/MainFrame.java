package Presentacion;

import Presentacion.Componentes.Boton;
import Presentacion.Paneles.PanelConsultas;
import Presentacion.Paneles.PanelDuenios;

import javax.swing.*;
import java.awt.*;
import Presentacion.Paneles.PanelMascotas;
import Presentacion.Paneles.PanelVeterinarios;

/**
 * Ventana principal del sistema veterinario. Se encarga de construir la
 * interfaz gráfica y de permitir la navegación entre los distintos módulos
 * (Dueños, Mascotas, Veterinarios y Consultas) mediante un {@link CardLayout}.
 *
 * @author Luis
 */
public class MainFrame extends JFrame {
    
    /**
     * Constructor que configura las propiedades básicas de la ventana:
     * título, tamaño, posición, operación de cierre y redimensionamiento.
     */
    public MainFrame() {
        setTitle("Main frame");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    /**
     * Construye y muestra la interfaz gráfica principal. Crea los botones de
     * navegación, los paneles de cada módulo y configura el {@link CardLayout}
     * para cambiar entre ellos.
     */
    public void Iniciar(){
        JPanel PanelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Boton botonDueño = new Boton("Dueños");
        Boton botonMascota = new Boton("Mascotas");
        Boton botonVeterinario = new Boton("Veterinarios");
        Boton botonConsulta = new Boton("Consultas");

        Dimension dimBoton = new Dimension(140, 45);
        botonDueño.setPreferredSize(dimBoton);
        botonMascota.setPreferredSize(dimBoton);
        botonVeterinario.setPreferredSize(dimBoton);
        botonConsulta.setPreferredSize(dimBoton);

        PanelNorte.add(botonDueño);
        PanelNorte.add(botonMascota);
        PanelNorte.add(botonVeterinario);
        PanelNorte.add(botonConsulta);

        CardLayout cardLayout = new CardLayout();
        JPanel panelContenido = new JPanel(cardLayout);

        panelContenido.add(new PanelDuenios(), "duenios");
        panelContenido.add(new PanelMascotas(), "mascotas");
        panelContenido.add(new PanelVeterinarios(), "veterinarios");
        panelContenido.add(new PanelConsultas(), "consultas");

        botonDueño.addActionListener(e -> cardLayout.show(panelContenido, "duenios"));
        botonMascota.addActionListener(e -> cardLayout.show(panelContenido, "mascotas"));
        botonVeterinario.addActionListener(e -> cardLayout.show(panelContenido, "veterinarios"));
        botonConsulta.addActionListener(e -> cardLayout.show(panelContenido, "consultas"));

        add(PanelNorte, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        cardLayout.show(panelContenido, "consultas");
        setVisible(true);
    }
}
