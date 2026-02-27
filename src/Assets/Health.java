package Assets;

import Scenes.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Health {
     //three hearts
        public static  int lives =3;
        BufferedImage image ;


        public Health(){
            loadImage();
        }
        private void loadImage(){
            try {
                image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void draw(Graphics2D g2){
            /*g2.drawString("lives",GamePanel.WIDTH, GamePanel.HEIGHT-48);*/
            g2.drawImage(image , GamePanel.WIDTH-90,0,48,48,null);
            g2.drawImage(image , GamePanel.WIDTH-60,0,48,48,null);
            g2.drawImage(image , GamePanel.WIDTH-30,0,48,48,null);
        }
}
