package org.progmatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener {

    private PlayerListener playerListener;
    private Image player;
    private int playerX = 180;
    private int playerY = 600;

    private Timer timer;
    private int delay = 8;

    public GamePlay(){
        playerListener = new PlayerListener();
        addKeyListener(playerListener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // play background
        g.setColor(Color.gray);
        g.fillRect(0, 0, 500, 800);

        g.setColor(Color.white);
        g.fillRect(166, 10, 10, 100);
        g.setColor(Color.white);
        g.fillRect(166, 210, 10, 100);
        g.setColor(Color.white);
        g.fillRect(166, 410, 10, 100);
        g.setColor(Color.white);
        g.fillRect(166, 610, 10, 100);

        g.setColor(Color.white);
        g.fillRect(333, 10, 10, 100);
        g.setColor(Color.white);
        g.fillRect(333, 210, 10, 100);
        g.setColor(Color.white);
        g.fillRect(333, 410, 10, 100);
        g.setColor(Color.white);
        g.fillRect(333, 610, 10, 100);

        player = Toolkit.getDefaultToolkit().getImage("car.png");
        g.drawImage(player, playerX, playerY, 150, 150, this);
    }

    private class PlayerListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!(playerX > 280)) {
                    playerX += 170;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!(playerX < 20)) {
                    playerX -= 170;
                }
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
