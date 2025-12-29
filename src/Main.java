import Scenes.OverlayScreen;

import javax.swing.*;

import java.awt.*;

import static Scenes.GamePanel.getGamePanel;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.getContentPane().add(getGamePanel());
        JLayeredPane jLayeredPane= OverlayScreen.getLayeredPanel();
        //window.getContentPane().add(jLayeredPane);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
