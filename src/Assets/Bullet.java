package Assets;

import utils.BulletHandling;
import utils.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public  class Bullet implements Collidable {
    private  final int height =24;
    private  final int width =24;
    private int y;
    private int x;
    private BufferedImage bufferedImage;
    private final PlayerShip playerShip;


    public int getHeight() {
        return height;
    }

    public Bullet( PlayerShip playerShip, BulletHandling bulletHandling) {
       this.playerShip=playerShip;
        setImage();
        update();

    }


    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getWidth() {
        return width ;
    }
    public void setY() {
        this.y = playerShip.getY();
    }

    public void setX() {
        this.x = playerShip.getX();
    }

    private void setImage()  {
        try {
            bufferedImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bullet.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(bufferedImage,playerShip.getX()+this.width, playerShip.getY()/2+width,getWidth(),getHeight(),null);
    }
    public void update(){
        this.y= playerShip.getY()/2;
        System.out.println(this.y);
        this.x+=3;
    }
}