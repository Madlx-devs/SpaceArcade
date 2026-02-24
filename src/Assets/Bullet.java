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
    private  final int Width =24;
    private int y;
    private int x;
    private BufferedImage bufferedImage;

    public int getHeight() {
        return height;
    }

    public Bullet( BulletHandling bulletHandler) {
        this.x=100;
        this.y=300;
        setImage();
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
        return 0;
    }

    public void setY(int y) {
        this.y = y;
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
        graphics2D.drawImage(bufferedImage,getX(),getY(),getWidth(),getHeight(),null);
    }
    public void update(){
        this.x+=3;
    }
}