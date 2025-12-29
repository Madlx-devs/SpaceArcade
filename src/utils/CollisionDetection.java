package utils;

public interface CollisionDetection {

    <T extends Collidable, U extends Collidable>
    boolean detectCollision(T a, U b);
}
