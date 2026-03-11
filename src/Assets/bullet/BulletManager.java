package Assets.bullet;

import Assets.EnemyShip;
import Assets.PlayerShip;
import Assets.pools.BulletPool;

import java.awt.*;
import java.util.List;


public class BulletManager {

    private final BulletPool bulletPool;
    private final PlayerShip playerShip;
    private final EnemyShip enemyShip;

    private long lastShotTime = 0;

    public BulletManager(BulletPool bulletPool, PlayerShip playerShip, EnemyShip enemyShip) {
        this.bulletPool = bulletPool;
        this.playerShip = playerShip;
        this.enemyShip = enemyShip;
    }

    public void shoot(boolean shooting) {

        long currentTime = System.currentTimeMillis();

        // milliseconds
        long shootDelay = 150;
        if (shooting && currentTime - lastShotTime >= shootDelay) {

            Bullet bullet = bulletPool.acquireObject();
            bullet.injectDependencies(playerShip, bulletPool,enemyShip);
            bullet.spawn();

            lastShotTime = currentTime;
        }
    }

    public void updateBullets() {

        List<Bullet> bullets = bulletPool.getInUse();

        for (int i = bullets.size() - 1; i >= 0; i--) {
            bullets.get(i).update();
        }
    }

    public void drawBullets(Graphics2D g2) {

        for (Bullet bullet : bulletPool.getInUse()) {
            bullet.draw(g2);
        }
    }
}