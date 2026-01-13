package Assets;

import Scenes.GamePanel;

import java.awt.*;

 public  class HealthBar {
    public static int HEALTH =100;

    public static void draw(Graphics2D g2D){
        final int HEIGHT =20;
        int width = (int) (200 * (HEALTH / 100.0));

        g2D.setColor(Color.red);
        g2D.fillRect(0,0,width,20);
    }

    public static void updateHealth(){

        if(HEALTH>0) HEALTH-=10;

    }
}
