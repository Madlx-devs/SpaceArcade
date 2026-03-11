package Assets.pools;

import Assets.EnemyShip;

import java.util.ArrayList;
import java.util.List;


public class EnemyshipPool implements ObjectPool<EnemyShip> {
private final List<EnemyShip> available = new ArrayList<>();
private final List<EnemyShip> inUse = new ArrayList<>();

    public EnemyshipPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            available.add(new EnemyShip());
        }
    }
    @Override
    public EnemyShip acquireObject() {
        EnemyShip ship;
        if(available.isEmpty()){
            ship= new EnemyShip();
            inUse.add(ship);
        }else
        {
            ship= available.remove(available.size()-1);
            inUse.add(ship);
        }
        return ship;
    }

    @Override
    public void releaseObject(EnemyShip ship) {
        inUse.remove(ship);
        available.add(ship);
    }

    @Override
    public int availableObjects() {
        return available.size();
    }

    @Override
    public List<EnemyShip> usedObjects() {
        return inUse;
    }
}
