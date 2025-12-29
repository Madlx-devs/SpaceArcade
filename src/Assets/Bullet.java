package Assets;

import utils.BulletHandling;
import utils.Collidable;
import utils.PlayerKeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet  implements Collidable {
    BulletHandling bulletHandling;
    int x ,y,height,width;
    BufferedImage bulletImage;

    public Bullet(BulletHandling bulletHandling){
        this.width=24;
        this.bulletHandling=bulletHandling;
        this.height=24;
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
        this.x=x;
    }
    public void setY(int y){
       this.y=y;
    }
    public void setImage(){
        try {
            bulletImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(bulletHandling.shooting){
            x+=4;
        }
    }
}
