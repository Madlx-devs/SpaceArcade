package Assets.pools;

public interface ObjectPool<T> {

    T acquireObject();
    void releaseObject(T object);
    int availableObjects();
    int usedObjects();
}
