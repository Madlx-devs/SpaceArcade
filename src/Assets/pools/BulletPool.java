package Assets.pools;

import Assets.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletPool implements ObjectPool<Bullet> {
 private static  BulletPool bulletPool =null;
 private final  List<Bullet> available= new ArrayList<>();
 private final List<Bullet> inUse = new ArrayList<>();

    private BulletPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            available.add(new Bullet());
        }
    }

    public static BulletPool getInstance() {
        if(bulletPool==null){
           bulletPool= new BulletPool(10);
        }
        return bulletPool;
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
