package assets;

import java.awt.*;
import java.util.List;

public interface EntityManager<T> {
    void draw(Graphics2D g2);
    void update();
    void remove(T entity);
    List<T> getEntities();
    default  void update(boolean shooting) {
        // Default implementation does nothing, can be overridden by classes that need  to handle shooting logic
    }
     void reset();
}
