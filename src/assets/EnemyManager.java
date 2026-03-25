package assets;

import assets.pools.EnemyshipPool;

import java.awt.*;

import java.util.List;


public class EnemyManager implements EntityManager<EnemyShip> {
    private final EnemyshipPool pool= new EnemyshipPool(5);;

    public EnemyManager(){

    }
    public void spawnEnemy() {
        EnemyShip enemyShip = pool.acquireObject();
        enemyShip.setIsActive(true);
        pool.getInUse().add(enemyShip);
    }

    public void update() {

        for( int i = pool.getInUse().size() - 1; i >= 0; i--) {
            EnemyShip enemyShip = pool.getInUse().get(i);
            if (enemyShip.getIsActive() && enemyShip.isWithinBounds()) {
                System.out.println(enemyShip.toString());
                enemyShip.update();
            }
            else if(!enemyShip.isWithinBounds()){

               remove(enemyShip);
            }

        }
    }
    public void draw(Graphics2D g2){
        for (int i = pool.getInUse().size() - 1; i >= 0; i--) {
            EnemyShip enemyShip = pool.getInUse().get(i);
            enemyShip.draw(g2);
        }
    }
    public void remove(EnemyShip enemyShip) {
        if (!enemyShip.getIsActive()) {
            pool.releaseObject(enemyShip);
        }
    }

    @Override
    public List<EnemyShip> getEntities() {
        return pool.getInUse();
    }

    public void reset(){
        System.out.println("reset");
    }
}
