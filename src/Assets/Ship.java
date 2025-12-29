package Assets;

import utils.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public  class Ship implements Collidable {
    protected int x, y,height=60,width=60,shootRate,speed=4;

    BufferedImage bufferedImage;

    void setDefaultValues(){
        //TODO SET DEFAULT VALUES
    }
     protected void getImage(){
        //TODO LOAD THE IMAGE FOR THE ENTITY
    }

    void update(){
        //TODO UPDATE THE LOCATION OF THE ENTITY
    }
    public void draw(Graphics2D g2D){
        // TODO DRAW THE  2D ENTITY
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
