package Scenes;

import javax.swing.*;
import java.awt.*;

public class OverlayScreen extends JLayeredPane {

    private static OverlayScreen overlayScreen;

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 80;

    private int health = 100;

    private OverlayScreen() {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        setHealth(health);
    }

    public static OverlayScreen getLayeredPanel() {
        if (overlayScreen == null) {
            overlayScreen = new OverlayScreen();
        }
        return overlayScreen;
    }

    public void setHealth(int hp) {
        health = Math.max(0, hp);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        int barWidth = (int) (200 * (health / 100.0));

        // top-right corner
        g.fillRect(GamePanel.WIDTH - 220, 20, barWidth, 20);
    }
}
