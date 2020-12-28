package org.progmatic;

import javax.swing.*;
import java.awt.*;

public class Car extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        String imageURL = "car.png";
        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        g2.drawImage(image, 5, 5, this);
    }
}
