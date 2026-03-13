package assets;

import utils.PlayerKeyHandler;
import Scenes.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerShip extends Ship{
    private static final BufferedImage bufferedImage;
    private final PlayerKeyHandler keyHandler;
     public PlayerShip(PlayerKeyHandler keyHandler){
        this.keyHandler=keyHandler;
        setDefaultValues();
     }

    @Override
     public void draw(Graphics2D g2D) {
        g2D.drawImage(bufferedImage,getX(),getY(),getWidth(),getHeight(),null);
    }
    @Override
     public void setDefaultValues(){
         setX(0);
         setY(0);
    }

    @Override
    public void update() {
         if(keyHandler.upPressed){
             setY(getY()-speed);
          }
        if(keyHandler.downPressed){
            setY(getY()+speed);
        }
        if(keyHandler.leftPressed){
            setX(getX()-speed);
        }
        if(keyHandler.rightPressed){
            setX(getX()+speed);
        }
        if (getX() < 0) setX(0);
        if (getX() > GamePanel.WIDTH - getWidth()) setX(GamePanel.WIDTH - getWidth());
        if (getY() < 0) setY(0);
        if (getY()> GamePanel.HEIGHT - getHeight()) setY(GamePanel.HEIGHT - getHeight());

    }

     static  {
        try {
          bufferedImage=  ImageIO.read(Objects.requireNonNull(PlayerShip.class.getResourceAsStream("/images/playership.png")));
        } catch (IOException e) {
            throw new RuntimeException("image cannot be loaded");
        }
    }

}
