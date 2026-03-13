package assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bang {
    private int x;


    private int y;
    private final int width=48;
    private final int height=48;
    private boolean active;
    private static final BufferedImage bufferedImage;
     static {
        try {
            bufferedImage = ImageIO.read(Objects.requireNonNull(Bang.class.getResourceAsStream("/images/bang.png")));
        }
        catch (IOException e) {
            throw new RuntimeException("image cannot be loaded");
        }
    }
    public Bang(int x ,int y){
        this.x=x;
        this.y=y;
        active=true;
    }
    public void draw(Graphics2D g2D){
        if(active){
            g2D.drawImage(bufferedImage,x,y,width,height,null);
        }
    }

}
