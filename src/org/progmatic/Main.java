package org.progmatic;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(10, 10, 500, 800);
        frame.setTitle("RaceCar");
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.add(new Car());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
