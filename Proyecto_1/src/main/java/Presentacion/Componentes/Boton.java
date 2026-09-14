package Presentacion.Componentes;

import javax.swing.*;
import java.awt.*;

public class Boton extends JButton {
    private int esquina = 30;

    public Boton(String texto) {
        super(texto);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
//        setOpaque(true);
//        setContentAreaFilled(true);
//        setFocusPainted(false);

        setBorder(BorderFactory.createEmptyBorder(12, 26, 12, 26));
    }
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
