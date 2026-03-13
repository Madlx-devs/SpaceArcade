package assets.bullet;

import Scenes.GamePanel;
import assets.EnemyShip;
import assets.PlayerShip;
import assets.pools.BulletPool;
import utils.CollisionDetection;

import java.awt.*;
import java.util.List;


public class BulletManager {

    private final BulletPool bulletPool = new BulletPool(10);
    private final PlayerShip playerShip;
    private final EnemyShip enemyShip;
    private final  int speed = 10;

    private long lastShotTime = 0;

    public BulletManager(PlayerShip playerShip, EnemyShip enemyShip) {
        this.playerShip = playerShip;
        this.enemyShip = enemyShip;
    }

    private void shoot(boolean shooting) {

        long currentTime = System.currentTimeMillis();
        // milliseconds
        long shootDelay = 150;
        if (shooting && currentTime - lastShotTime >= shootDelay) {

            Bullet bullet = bulletPool.acquireObject();
            bullet.injectDependencies(playerShip);
            bullet.spawn();

            lastShotTime = currentTime;
        }
    }

    public void updateBullets(boolean shooting) {
        shoot(shooting);
        List<Bullet> bullets = bulletPool.getInUse();

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            if(bullet.isActive()){
                bullet.setX(bullet.getX()+speed);
            }
            if(bullet.getX() > GamePanel.WIDTH || bullet.getX() < 0|| !bullet.isActive()){
                bulletPool.releaseObject(bullet);
            }

        }
    }

    public void drawBullets(Graphics2D g2) {

        for (Bullet bullet : bulletPool.getInUse()) {
            bullet.draw(g2);
        }
    }

    public List<Bullet> getBullet() {
        return bulletPool.getInUse();
    }
}