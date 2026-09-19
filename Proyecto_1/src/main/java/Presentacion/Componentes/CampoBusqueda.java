/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Componentes;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
/**
 *
 * @author Andrés
 */
public class CampoBusqueda extends JTextField {
    private String regex;

    public CampoBusqueda(String regex, int columnas) {
        super(columnas);
        this.regex = regex;
        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validar(); }
            public void removeUpdate(DocumentEvent e) { validar(); }
            public void changedUpdate(DocumentEvent e) { validar(); }
        });
    }

    private void validar() {
        String texto = getText();
        if (texto.isEmpty()) {
            setBackground(Color.WHITE);
        } else if (texto.matches(regex)) {
            setBackground(new Color(200, 255, 200));
        } else {
            setBackground(new Color(255, 200, 200));
        }
    }

    public boolean esValido() {
        return getText().isEmpty() || getText().matches(regex);
    }
}
