package Assets;

import Assets.pools.EnemyshipPool;

import java.awt.*;

public class EnemyManager {
    private final EnemyshipPool pool =new EnemyshipPool(5);

    public void draw(Graphics2D g2){
        for (EnemyShip enemyShip : pool.getInUse()) {
            enemyShip.draw(g2);
        }
    }
    public void update() {
        for(EnemyShip enemyShip: pool.getInUse()){
            enemyShip.update();
        }
    }
    public void remove() {
        for (int i = pool.getInUse().size() - 1; i >= 0; i--) {
            EnemyShip enemyShip = pool.getInUse().get(i);
            if (!enemyShip.getIsActive()){
                pool.releaseObject(enemyShip);
            }
        }
    }
    public EnemyShip getEnemyShip() {
        return pool.acquireObject();
    }
}
