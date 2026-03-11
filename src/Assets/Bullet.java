package Assets;

import Assets.pools.BulletPool;
import Scenes.GamePanel;
import utils.Collidable;
import utils.CollisionDetection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Bullet implements Collidable {

    private final int width = 24;
    private int x;
    private int y;

    private boolean active;

    private static BufferedImage bufferedImage;

    private PlayerShip playerShip;
    private BulletPool bulletPool;
    private  EnemyShip enemyShip;

    public Bullet() {
        setImage();
    }

    public void injectDependencies(PlayerShip playerShip, BulletPool bulletPool, EnemyShip enemyShip) {
        this.playerShip = playerShip;
        this.bulletPool = bulletPool;
        this.enemyShip = enemyShip;

    }

    public void spawn() {
        this.x = playerShip.getX() + width;
        this.y = playerShip.getY();
        active = true;
    }

    public void update() {

        if (!active) return;

        x += 10;

        if (x > GamePanel.WIDTH || CollisionDetection.detectCollision(this,enemyShip)) {
            active = false;
            bulletPool.releaseObject(this);

        }
    }

    public void draw(Graphics2D g2) {

        if (!active) return;

        g2.drawImage(bufferedImage, x, y, 24, 24, null);
    }

    private void setImage() {

        if (bufferedImage == null) {

            try {
                bufferedImage = ImageIO.read(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream("/images/Bullet.png")
                        )
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        return width;
    }

    public int getHeight() {
        return 24;
    }
}