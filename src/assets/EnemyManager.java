package assets;

import assets.pools.EnemyshipPool;

import java.awt.*;

import java.util.List;


public class EnemyManager {
    private final EnemyshipPool pool;

    public EnemyManager(){
        pool= new EnemyshipPool(5);
        for (int i = 0; i < 5; i++) {
            pool.acquireObject();
        }
    }

    public void draw(Graphics2D g2){
        for (EnemyShip enemyShip : pool.getInUse()) {
            enemyShip.draw(g2);
        }
    }
    public void update() {
        for( int i = pool.getInUse().size() - 1; i >= 0; i--) {
                EnemyShip enemyShip=pool.getInUse().get(i);
            if (enemyShip.getIsActive() && enemyShip.isWithinBounds()) {
                enemyShip.update();
            }
            else if(!enemyShip.isWithinBounds()){
              enemyShip.setIsActive(false);
               remove(enemyShip);
            }

        }
    }
    public void remove(EnemyShip enemyShip) {
        if (!enemyShip.getIsActive()) {
            pool.releaseObject(enemyShip);

        }
    }
    public List<EnemyShip> getEnemyShip() {
        return pool.getInUse();
    }
    public void reset(){
        System.out.println("reset");
    }
}
