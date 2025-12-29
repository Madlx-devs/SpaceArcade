package Assets;

import Scenes.GamePanel;

import java.awt.*;

 public class HealthBar {

    public static void draw(Graphics2D g2D){
        g2D.setColor(Color.red);
        g2D.fillRect(GamePanel.HEIGHT-20,GamePanel.WIDTH-20,20,20);
    }
}
