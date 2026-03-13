package assets.bullet;

import assets.PlayerShip;
import utils.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Bullet implements Collidable {

    private int x;
    private int y;

    private boolean active;

    private static BufferedImage bufferedImage;

    private PlayerShip playerShip;

    public Bullet() {

    }
    public void spawn(){
        this.x= playerShip.getX();
        this.y=playerShip.getY();
        setActive(true);
    }
    public void injectDependencies(PlayerShip playerShip) {
        this.playerShip = playerShip;

    }



    public void draw(Graphics2D g2) {

        if (!isActive()) return;

        g2.drawImage(bufferedImage, x, y, 24, 24, null);
    }

    static  {

        if (bufferedImage == null) {

            try {
                bufferedImage = ImageIO.read(
                        Objects.requireNonNull(
                                Bullet.class.getResourceAsStream("/images/Bullet.png")
                        )
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active=active;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return 24;
    }

    public int getHeight() {
        return 24;
    }


}