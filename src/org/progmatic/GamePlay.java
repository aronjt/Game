package org.progmatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener {

    private PlayerListener playerListener;
    private RestartListener restartListener;

    private Image player;
    private int playerX = 180;
    private int playerY = 600;

    private Image police;
    private int policeX = 180;
    private int policeY = 0;

    private Image tank;
    private int tankX = 10;
    private int tankY = 500;

    private Image helicopter;
    private int helicopterX;
    private int helicopterY = -70;

    private Image explosion;
    private int explosionX;
    private int explosionY;

    private boolean play = true;
    private boolean loss = false;

    int rnd = (int)(Math.random()*2);

    private Timer timer;
    private int delay = 200;

    private int score = 0;

    public GamePlay(){
        playerListener = new PlayerListener();
        restartListener = new RestartListener();
        addKeyListener(playerListener);
        addKeyListener(restartListener);
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

        if (play) {
            player = Toolkit.getDefaultToolkit().getImage("car.png");
            g.drawImage(player, playerX, playerY, 150, 150, this);

            police = Toolkit.getDefaultToolkit().getImage("policecar.png");
            g.drawImage(police, policeX, policeY, 150, 150, this);

            tank = Toolkit.getDefaultToolkit().getImage("tank.png");
            g.drawImage(tank, tankX, tankY, 150, 150, this);

            helicopter = Toolkit.getDefaultToolkit().getImage("helicopter.png");

            if (!(policeY > 800)) {
                policeY += 20;
            } else {
                policeY = -120;
                int random = (int)(Math.random()*3);
                if (random == 0) {
                    policeX = 10;
                } else if (random == 1) {
                    policeX = 350;
                }
                score += 10;
            }

            if (!(tankY > 800)) {
                tankY += 20;
            } else {
                tankY = -120;
                int random = (int)(Math.random()*3);
                if (random == 0) {
                    tankX = 180;
                } else if (random == 1) {
                    tankX = 350;
                }
                score += 10;
            }

            switch (score) {
                case 60 -> {
                    timer.setDelay(150);
                    g.setFont(new Font("serif", Font.BOLD, 25));
                    g.drawString("Level 2", 200, 400);
                }
                case 120 -> {
                    timer.setDelay(125);
                    g.setFont(new Font("serif", Font.BOLD, 25));
                    g.drawString("Level 3", 200, 400);
                }
                case 160 -> {
                    timer.setDelay(100);
                    g.setFont(new Font("serif", Font.BOLD, 25));
                    g.drawString("Level 4", 200, 400);
                }
                case 200 -> {
                    timer.setDelay(75);
                    g.setFont(new Font("serif", Font.BOLD, 25));
                    g.drawString("Level 5", 200, 400);
                }
            }

            if (score >= 60) {
                if (policeX == 10) {
                    if (rnd == 0) {
                        helicopterX = 180;
                    } else {
                        helicopterX = 350;
                    }
                } else if (policeX == 180) {
                    if (rnd == 0) {
                        helicopterX = 10;
                    } else {
                        helicopterX = 350;
                    }
                } else {
                    if (rnd == 0) {
                        helicopterX = 10;
                    } else {
                        helicopterX = 180;
                    }
                }
                g.drawImage(helicopter, helicopterX, helicopterY, 150, 150, this);
            }

            if (score >= 60) {
                if (!(helicopterY > 800)) {
                    helicopterY += 20;
                } else {
                    helicopterY = -120;
                    score += 10;
                    rnd = (int) (Math.random() * 2);
                }
            }

            if (policeX == playerX && policeY + 140 >= playerY && policeY <= playerY + 140) {
                explosionX = playerX;
                explosionY = playerY;
                play = false;
                loss = true;
            }

            if (tankX == playerX && tankY + 140 >= playerY && tankY <= playerY + 140) {
                explosionX = playerX;
                explosionY = playerY;
                play = false;
                loss = true;
            }

            if (helicopterX == playerX && helicopterY + 140 >= playerY && helicopterY <= playerY + 140) {
                explosionX = playerX;
                explosionY = playerY;
                play = false;
                loss = true;
            }

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Score: " + score, 400, 30);
        }

        if (loss) {
            explosion = Toolkit.getDefaultToolkit().getImage("explosion.png");
            g.drawImage(explosion, explosionX, explosionY, 150, 150, this);
        }

        if (loss) {
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Vesztettél", 190, 350);
            g.drawString("Elért pont: " + score, 170, 400);
            g.setFont(new Font("serif", Font.BOLD, 22));
            g.drawString("Újraindításhoz nyomd meg a SPACE gombot", 25, 575);
        }
    }

    private class PlayerListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) { }

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
        public void keyReleased(KeyEvent e) { }
    }

    private class RestartListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                policeX = 180;
                policeY = 0;
                tankX = 10;
                tankY = 500;
                playerX = 180;
                playerY = 600;
                helicopterY = -70;
                score = 0;
                timer.setDelay(200);
                play = true;
                loss = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}
