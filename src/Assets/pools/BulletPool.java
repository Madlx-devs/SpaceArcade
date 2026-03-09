package Assets.pools;

import Assets.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletPool implements ObjectPool<Bullet> {
 private final  List<Bullet> available= new ArrayList<>();
 private final List<Bullet> inUse = new ArrayList<>();

    public BulletPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            available.add(new Bullet());
        }
    }

    @Override
    public Bullet acquireObject() {
        if(available.isEmpty()){
            return new Bullet();
        }
        Bullet bullet = available.remove(available.size() - 1);
        inUse.add(bullet);
        return bullet;
    }

    @Override
    public void releaseObject(Bullet object) {
        inUse.remove(object);
        available.add(object);
    }

    @Override
    public int availableObjects() {
        return available.size();
    }

    @Override
    public int usedObjects() {
        return inUse.size();
    }
}
