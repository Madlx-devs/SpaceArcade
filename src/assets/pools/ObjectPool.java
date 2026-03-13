package assets.pools;

import java.util.List;

public interface ObjectPool<T> {

    T acquireObject();
    void releaseObject(T object);
    int availableObjects();
    List<T> getInUse();
}
