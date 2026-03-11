package Assets;




import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
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
    void setDefaultValues() {
        x= WIDTH -width;

        y= HEIGHT/2;
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
       x-=speed;
       if(x<0){
           x=WIDTH-width;

       }
    }

    @Override
    public void draw(Graphics2D g2D) {
            g2D.drawImage(bufferedImage,x,y,48,48,null);
    }

}
