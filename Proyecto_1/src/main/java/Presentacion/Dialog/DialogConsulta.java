package Presentacion.Dialog;

import DAO.MascotaDAO;
import DAO.VeterinarioDAO;
import Dominio.Entidades.Consulta;
import Dominio.Entidades.Mascota;
import Dominio.Entidades.Veterinario;
import Presentacion.Componentes.Boton;
import Presentacion.Componentes.CampoBusqueda;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DialogConsulta extends JDialog {

    private CampoBusqueda campoCosto;

    private JTextArea areaMotivo;
    private JTextArea areaDiagnostico;
    private JTextArea areaTratamiento;

    private DatePicker datePickerFecha;
    private TimePicker timePickerHora;

    private JComboBox<Mascota> comboMascota;
    private JComboBox<Veterinario> comboVeterinario;

    private Boton botonAceptar;
    private Boton botonCancelar;

    private boolean aceptado = false;

    private Consulta consulta;

    public DialogConsulta(Frame owner) {
        this(owner, null);
    }

    public DialogConsulta(Frame owner, Consulta consulta) {
        super(owner, true);

        this.consulta = consulta;

        setTitle(consulta == null ? "Agregar Consulta" : "Modificar Consulta");

        setSize(600, 650);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarMascotas();
        cargarVeterinarios();

        if (consulta != null) {
            cargarDatosConsulta();
        }

        setVisible(true);
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelContenido = new JPanel(new GridBagLayout());

        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        //Fecha

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Fecha:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        datePickerFecha = new DatePicker();

        panelContenido.add(datePickerFecha,gbc);

        //Hora

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Hora:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        timePickerHora = new TimePicker();

        panelContenido.add(timePickerHora, gbc);

        //Motivo

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        panelContenido.add(new JLabel("Motivo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        areaMotivo = new JTextArea(3, 25);
        areaMotivo.setLineWrap(true);
        areaMotivo.setWrapStyleWord(true);

        JScrollPane scrollMotivo = new JScrollPane(areaMotivo);

        panelContenido.add(scrollMotivo, gbc);

        //Diagnostico

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        panelContenido.add(new JLabel("Diagnostico:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        areaDiagnostico = new JTextArea(4, 25);
        areaDiagnostico.setLineWrap(true);
        areaDiagnostico.setWrapStyleWord(true);

        JScrollPane scrollDiagnostico = new JScrollPane(areaDiagnostico);

        panelContenido.add(scrollDiagnostico, gbc);

        //Tratamiento

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        panelContenido.add(new JLabel("Tratamiento:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        areaTratamiento = new JTextArea(4, 25);
        areaTratamiento.setLineWrap(true);
        areaTratamiento.setWrapStyleWord(true);

        JScrollPane scrollTratamiento = new JScrollPane(areaTratamiento);

        panelContenido.add(scrollTratamiento, gbc);

        //Costo

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelContenido.add(new JLabel("Costo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        campoCosto = new CampoBusqueda("\\d{0,8}(\\.\\d{0,2})?", 20);

        panelContenido.add(campoCosto, gbc);

        //Mascota

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Mascota:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        comboMascota = new JComboBox<>();

        panelContenido.add(comboMascota, gbc);

        //Veterinario

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0;

        panelContenido.add(new JLabel("Veterinario:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        comboVeterinario = new JComboBox<>();

        panelContenido.add(comboVeterinario, gbc);

        add(panelContenido, BorderLayout.CENTER);

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        botonCancelar = new Boton("Cancelar");
        botonAceptar = new Boton("Aceptar");

        panelBotones.add(botonCancelar);
        panelBotones.add(botonAceptar);

        add(panelBotones, BorderLayout.SOUTH);

        //Eventos
        
        botonCancelar.addActionListener(e -> {
            aceptado = false;
            dispose();
        });

        botonAceptar.addActionListener(e -> aceptar());
    }

    //Cargar mascotas

    private void cargarMascotas() {

        MascotaDAO mascotaDAO = new MascotaDAO();

        List<Mascota> mascotas = mascotaDAO.consultarTodos();

        comboMascota.removeAllItems();

        for (Mascota mascota : mascotas) {
            comboMascota.addItem(mascota);
        }

        comboMascota.setRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Mascota) {

                    Mascota mascota = (Mascota) value;

                    setText(mascota.getIdMascota() + " - " + mascota.getNombre() + " (" + mascota.getEspecie() + ")");
                }

                return this;
            }
        });
    }

    //Cargar veterinarios

    private void cargarVeterinarios() {

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

        List<Veterinario> veterinarios = veterinarioDAO.consultarTodos();

        comboVeterinario.removeAllItems();

        for (Veterinario veterinario : veterinarios) {
            comboVeterinario.addItem(veterinario);
        }

        comboVeterinario.setRenderer(new DefaultListCellRenderer() {

                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                        if (value instanceof Veterinario) {

                            Veterinario veterinario = (Veterinario) value;

                            setText(veterinario.getIdVeterinario() + " - Dr. " + veterinario.getNombre() + " " + veterinario.getApellidoP());
                        }

                        return this;
                    }
                }
        );
    }

    //Cargar datos para modificar

    private void cargarDatosConsulta() {

        if (consulta.getFechaHora() != null) {

            LocalDateTime fechaHora = consulta.getFechaHora().toLocalDateTime();

            datePickerFecha.setDate(fechaHora.toLocalDate());
            timePickerHora.setTime(fechaHora.toLocalTime());
        }

        areaMotivo.setText(consulta.getMotivo() != null ? consulta.getMotivo() : "");
        areaDiagnostico.setText(consulta.getDiagnostico() != null ? consulta.getDiagnostico() : "");
        areaTratamiento.setText( consulta.getTratamiento() != null ? consulta.getTratamiento() : "");

        if (consulta.getCosto() != null) {

            campoCosto.setText(consulta.getCosto().toString());
        }

        // Seleccionar mascota
        for (int i = 0; i < comboMascota.getItemCount(); i++) {

            Mascota mascota = comboMascota.getItemAt(i);

            if (mascota.getIdMascota() == consulta.getIdMascota()) {

                comboMascota.setSelectedIndex(i);
                break;
            }
        }

        // Seleccionar veterinario
        for (int i = 0; i < comboVeterinario.getItemCount(); i++) {

            Veterinario veterinario = comboVeterinario.getItemAt(i);

            if (veterinario.getIdVeterinario() == consulta.getIdVeterinario()) {

                comboVeterinario.setSelectedIndex(i);
                break;
            }
        }
    }

    //Validar y aceptar

    private void aceptar() {

        //Fecha
        if (datePickerFecha.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione la fecha de la consulta.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Hora
        if (timePickerHora.getTime() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione la hora de la consulta.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Motivo
        String motivo = areaMotivo.getText().trim();

        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el motivo de la consulta.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            areaMotivo.requestFocus();
            return;
        }

        if (motivo.length() > 200) {
            JOptionPane.showMessageDialog(this, "El motivo no puede superar los 200 caracteres.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            areaMotivo.requestFocus();
            return;
        }

        //Diagnostico
        String diagnostico = areaDiagnostico.getText().trim();

        //Tratamiento
        String tratamiento = areaTratamiento.getText().trim();

        //Costo
        String textoCosto = campoCosto.getText().trim();

        if (textoCosto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el costo de la consulta.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            campoCosto.requestFocus();
            return;
        }

        if (!campoCosto.esValido()) {
            JOptionPane.showMessageDialog(this, "El costo debe ser un numero valido con maximo 2 decimales.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            campoCosto.requestFocus();
            return;
        }

        BigDecimal costo;

        try {

            costo = new BigDecimal(textoCosto);

            if (costo.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "El costo no puede ser negativo.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
                campoCosto.requestFocus();
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un costo valido.", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            campoCosto.requestFocus();
            return;
        }

        //Mascota
        if (comboMascota.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una mascota.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Veterinario
        if (comboVeterinario.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un veterinario.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Crear/actualizar consulta

        if (consulta == null) {
            consulta = new Consulta();
        }

        LocalDateTime fechaHora = LocalDateTime.of(datePickerFecha.getDate(), timePickerHora.getTime());

        consulta.setFechaHora(Timestamp.valueOf(fechaHora));
        consulta.setMotivo(motivo);
        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setCosto(costo);

        Mascota mascotaSeleccionada = (Mascota) comboMascota.getSelectedItem();

        Veterinario veterinarioSeleccionado = (Veterinario) comboVeterinario.getSelectedItem();

        consulta.setIdMascota(mascotaSeleccionada.getIdMascota());
        consulta.setIdVeterinario(veterinarioSeleccionado.getIdVeterinario());

        aceptado = true;

        dispose();
    }

    //Getters

    public boolean isAceptado() {
        return aceptado;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}