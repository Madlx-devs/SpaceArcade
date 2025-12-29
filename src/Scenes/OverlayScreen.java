package Scenes;

import javax.swing.*;
import java.awt.*;


public class OverlayScreen extends JLayeredPane {



    public static OverlayScreen overlayScreen;
     public static int WIDTH=1080;
     public static int HEIGHT=80;
    public static OverlayScreen getLayeredPanel(){
        if(overlayScreen==null){
            overlayScreen=new OverlayScreen();
        }
        return overlayScreen;
    }


    public void draw(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(GamePanel.WIDTH-100,GamePanel.HEIGHT,80,20);

    }
}
