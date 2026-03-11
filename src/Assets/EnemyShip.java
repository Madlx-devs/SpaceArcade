package Assets;




import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Objects;
import static Scenes.GamePanel.*;

public class EnemyShip extends Ship {
     private static BufferedImage bufferedImage;

    public EnemyShip(){

      setDefaultValues();
      getImage();
      update();
    }
    @Override
    public void setDefaultValues() {
        setX( WIDTH -getWidth());

        setY(HEIGHT/2);
    }

    @Override
    protected void getImage() {
        try{
            bufferedImage= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/enemy.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
       setX(getX()-speed);
       if(getX()<0){
           setX(WIDTH-getWidth());

       }
    }

    @Override
    public void draw(Graphics2D g2D) {
            g2D.drawImage(bufferedImage,getX(),getY(),48,48,null);
    }

}
