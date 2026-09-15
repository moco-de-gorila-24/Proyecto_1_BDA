package Presentacion.Paneles;

import Presentacion.Componentes.Boton;
import Presentacion.Componentes.Tabla;
import Presentacion.Dialog.DialogConsulta;
import Presentacion.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConsultas extends JPanel {
    JScrollPane jscrollPane;
    JPanel panelDerecha;
    JPanel panelTabla;
    Boton agendar;
    Boton modificar;


    public PanelConsultas(){
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(800, 500));
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridBagLayout());
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        panelTabla.add(jscrollPane, BorderLayout.CENTER);

        agendar = new Boton("Agendar consultas");
        modificar = new Boton("Modificar Consulta");

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
        panelDerecha.add(agendar, gbc);

        gbc.gridy = 1;
        panelDerecha.add(modificar, gbc);

        add(panelDerecha, BorderLayout.EAST);
        add(panelTabla, BorderLayout.CENTER);

        agendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                DialogConsulta dialogDuenio = new DialogConsulta(mainFrame);
                dialogDuenio.iniciarComponentes();
            }
        });
    }
}
