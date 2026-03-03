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
    private final BulletHandling bullethandler;
    private boolean shooting =false;


    public int getHeight() {
        return height;
    }

    public Bullet( PlayerShip playerShip, BulletHandling bulletHandler) {
       this.playerShip=playerShip;
       this.bullethandler=bulletHandler;
        setImage();
        update();

    }


    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getWidth() {
        return width ;
    }
    public void setY() {
        this.y = playerShip.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    private void setImage()  {
        try {
            bufferedImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bullet.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(bufferedImage,getX(), playerShip.getY()+width,getWidth(),getHeight(),null);

    }
    public void update(){
       while (bullethandler.isShooting()){
           setX(getX()+3);
        }
    }
}