package Assets;

import Assets.pools.BulletPool;
import Scenes.GamePanel;
import utils.BulletHandling;
import utils.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public  class Bullet implements Collidable {
    private  final int width =24;
    private int y;
    private int x;
    private BufferedImage bufferedImage;
    private PlayerShip playerShip;
    private BulletHandling bulletHandler;
    private boolean active;

    public int getHeight() {
        return 24;
    }

    public Bullet( ) {
        setImage();
        if(playerShip!=null){
             spawn();
        }
    }

    public void injectDependencies(PlayerShip playerShip, BulletHandling bulletHandler) {
        this.playerShip = playerShip;
        this.bulletHandler = bulletHandler;

    }
    public  void spawn(){
        this.x= playerShip.getX()+width;
        this.y= playerShip.getY();
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
        graphics2D.drawImage(bufferedImage,x,y,24,24,null);
    }
    public void update() {
        if (getX() > GamePanel.WIDTH) {
            setX(0);
        }
        setX(getX() + 10);
    }
}