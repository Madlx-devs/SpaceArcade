package Assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Bang {
    private int x;
    private static BufferedImage bufferedImage;

    private int y;
    private final int width=48;
    private final int height=48;
    private boolean active;

    public Bang(int x ,int y){
        this.x=x;
        this.y=y;
        setImage();
        active=true;
    }
    public void setImage() {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/bang.png")));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void draw(Graphics2D g2D){
        if(active){
            g2D.drawImage(bufferedImage,x,y,width,height,null);
        }
    }

}
