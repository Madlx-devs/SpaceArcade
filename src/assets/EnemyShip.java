package assets;


import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Objects;
import static Scenes.GamePanel.*;

public class EnemyShip extends Ship {
   private  static int counter=0;
    private int id =0;

    public  void setId() {
         this.id =++counter;
    }
    public int getId() {
    return id;
    }

    private static final BufferedImage bufferedImage;

    public EnemyShip(){

      setDefaultValues();
      isActive=true;
      setId();
    }
    @Override
    public void setDefaultValues() {
        setX( WIDTH -getWidth());

        setY(HEIGHT/2);
    }
    public  boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean active) {
        isActive = active;

    }


     static {
        try{
            bufferedImage= ImageIO.read(Objects.requireNonNull(EnemyShip.class.getResourceAsStream("/images/enemy.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        if (getIsActive()) {
            setX(getX() - speed);
        }
    }

    @Override
    public boolean isWithinBounds() {
       return this.getX()>0 && this.getX()< WIDTH && this.getY()>0 && this.getY()<HEIGHT;
    }
    @Override
    public void draw(Graphics2D g2D) {
        if(getIsActive()){
            g2D.drawImage(bufferedImage,getX(),getY(),48,48,null);
        }
    }
    public void reset( ) {
        this.setX(WIDTH - getWidth());
        this.setY(HEIGHT / 2);
        this.isActive = true;
    }
     //toString method for debugging purposes
    @Override
    public String toString() {
        return "enemyShip{" +
                "id=" + id +
                ", x=" + getX() +
                ", y=" + getY() +
                ", isActive=" + getIsActive() +
                '}';
    }
}
