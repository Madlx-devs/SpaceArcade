package Assets;

import Scenes.GamePanel;
import utils.BulletHandling;
import utils.Collidable;
import utils.PlayerKeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet  implements Collidable {
    BulletHandling bulletHandling;
    int x ,y,height,width;
    BufferedImage bulletImage;
    PlayerShip playerShip;

    public Bullet(BulletHandling bulletHandling , PlayerShip playerShip){
        this.width=24;
        this.bulletHandling=bulletHandling;
        this.height=24;
        this.playerShip=playerShip;
        setDefaultValues();
        setImage();

    }
    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    public void setX(int x){
        this.x=playerShip.getX()+ bulletImage.getWidth()+100;
    }
    public void setY(int y){
       this.y=playerShip.getY()- bulletImage.getHeight();
    }
    public void setImage(){
        try {
            bulletImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        UpdateBulletLocation();
        if(bulletHandling.shooting){
            x+=4;
            if(x>GamePanel.WIDTH)x=0;
        }
    }

    public void draw(Graphics2D g2D){
        g2D.drawImage(bulletImage,x,y,getWidth(),getHeight(),null);
    }

    public void setDefaultValues(){
        this.x =playerShip.getX()+this.width;
        this.y =playerShip.getY()/2;
    }
    public void UpdateBulletLocation(){
         setX(playerShip.getX()+this.width);
         setY( playerShip.getY()/2);
    }
}
