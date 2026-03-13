package assets.pools;

import assets.bullet.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletPool implements ObjectPool<Bullet> {

    private final List<Bullet> available = new ArrayList<>();
    private final List<Bullet> inUse = new ArrayList<>();

    public BulletPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            available.add(new Bullet());
        }
    }

    @Override
    public Bullet acquireObject() {

        Bullet bullet;

        if (available.isEmpty()) {
            bullet = new Bullet();
        } else {
            bullet = available.remove(available.size() - 1);
        }

        inUse.add(bullet);
        return bullet;
    }

    @Override
    public void releaseObject(Bullet object) {
        inUse.remove(object);
        available.add(object);
    }

    public List<Bullet> getInUse() {
        return inUse;
    }

    @Override
    public int availableObjects() {
        return available.size();
    }

}