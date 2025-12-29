package Assets;

import utils.PlayerKeyHandler;
import Scenes.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PlayerShip extends Ship{
     PlayerKeyHandler keyHandler;
     public PlayerShip(PlayerKeyHandler keyHandler){
        this.keyHandler=keyHandler;
        setDefaultValues();
        getImage();
     }

    @Override
     public void draw(Graphics2D g2D) {
        g2D.drawImage(bufferedImage,x,y,width,height,null);
    }
    @Override
     public void setDefaultValues(){
         x =0;
         y =0;
    }

    @Override
    public void update() {
         if(keyHandler.upPressed){
             y-=speed;
          }
        if(keyHandler.downPressed){
            y+=speed;
        }
        if(keyHandler.leftPressed){
            x-=speed;
        }
        if(keyHandler.rightPressed){
            x+=speed;
        }
        if (x < 0) x = 0;
        if (x > GamePanel.WIDTH - width) x = GamePanel.WIDTH - width;
        if (y < 0) y = 0;
        if (y > GamePanel.HEIGHT - height) y = GamePanel.HEIGHT - height;

    }

    @Override
     protected void getImage() {
        try {
          bufferedImage=  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/playership.png")));
        } catch (IOException e) {
            throw new RuntimeException("image cannot be loaded");
        }
    }


}
