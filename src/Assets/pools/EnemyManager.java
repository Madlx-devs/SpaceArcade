package Assets.pools;

import Assets.EnemyShip;

import java.awt.*;

public class EnemyManager {
    private final EnemyshipPool pool =new EnemyshipPool(5);

    private void spawnEnemy(){
        EnemyShip ship = pool.acquireObject();
    }
    public void DrawEnemy(Graphics2D g2){
        for(int i=0;i<5;i++){
            EnemyShip ship = pool.acquireObject();
            ship.draw(g2);
        }
    }
    public void updateEnemy(){
       spawnEnemy();
    }
}
