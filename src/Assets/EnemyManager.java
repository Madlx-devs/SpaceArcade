package Assets;

import Assets.pools.EnemyshipPool;

import java.awt.*;

public class EnemyManager {
    private final EnemyshipPool pool =new EnemyshipPool(5);

    public void draw(Graphics2D g2){
            EnemyShip ship = pool.acquireObject();
            ship.draw(g2);
    }
    public void updateEnemy() {
        getEnemyShip().update();
    }
    public EnemyShip getEnemyShip() {
        return pool.acquireObject();
    }
}
