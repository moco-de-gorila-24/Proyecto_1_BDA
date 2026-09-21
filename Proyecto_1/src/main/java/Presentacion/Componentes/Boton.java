package Presentacion.Componentes;

import javax.swing.*;
import java.awt.*;

/**
 * Componente personalizado que extiende {@link JButton} y dibuja un botón
 * con esquinas redondeadas y estilo visual propio del sistema veterinario.
 *
 * @author ACER
 */
public class Boton extends JButton {

    /** Radio de las esquinas redondeadas del botón. */
    private int esquina = 30;

    /**
     * Constructor que crea un botón con el texto indicado.
     *
     * @param texto texto que se mostrará en el botón.
     */
    public Boton(String texto) {
        super(texto);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(12, 26, 12, 26));
    }

    /**
     * Sobrescribe el método de pintado para dibujar un rectángulo redondeado
     * como fondo del botón, cambiando el color cuando está presionado.
     *
     * @param g contexto gráfico utilizado para pintar el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(Color.DARK_GRAY);
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, esquina, esquina);
        g2.dispose();

        super.paintComponent(g);
    }
}
