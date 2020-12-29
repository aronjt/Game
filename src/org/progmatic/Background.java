package org.progmatic;

import javax.swing.*;
import java.awt.*;

public class Background extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        Graphics2D g2 = (Graphics2D) g;
        g.fillRect(0,0,500,800);
    }
}
