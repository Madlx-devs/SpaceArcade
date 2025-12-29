package Scenes;

import javax.swing.*;


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
}
