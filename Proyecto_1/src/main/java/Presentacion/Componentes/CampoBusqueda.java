package Presentacion.Componentes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Componente personalizado que extiende {@link JTextField} y agrega validación
 * en vivo mediante expresiones regulares. El campo cambia de color de fondo
 * según si el texto ingresado cumple o no con el patrón definido.
 *
 * @author Andrés
 */
public class CampoBusqueda extends JTextField {

    /** Expresión regular que debe cumplir el texto del campo. */
    private String regex;

    /**
     * Constructor que crea un campo con la expresión regular indicada.
     * Registra un {@link DocumentListener} para validar el contenido cada
     * vez que el usuario escribe.
     *
     * @param regex expresión regular de validación.
     * @param columnas número de columnas visibles del campo.
     */
    public CampoBusqueda(String regex, int columnas) {
        super(columnas);
        this.regex = regex;
        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validar(); }
            public void removeUpdate(DocumentEvent e) { validar(); }
            public void changedUpdate(DocumentEvent e) { validar(); }
        });
    }

    /**
     * Valida el contenido actual del campo y cambia el color del fondo:
     * blanco si está vacío, verde si cumple la expresión regular, rojo si no.
     */
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

    /**
     * Comprueba si el texto actual cumple con la expresión regular.
     *
     * @return {@code true} si el campo está vacío o cumple la expresión,
     *         {@code false} en caso contrario.
     */
    public boolean esValido() {
        return getText().isEmpty() || getText().matches(regex);
    }
}