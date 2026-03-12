package Assets;

import utils.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

public  class Ship implements Collidable {
    private int x, y,height=60,width=60;
    public  final int speed=4;
    protected boolean isActive;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    BufferedImage bufferedImage;

    public void setDefaultValues(){
        //TODO SET DEFAULT VALUES
    }
     protected void getImage(){
        //TODO LOAD THE IMAGE FOR THE ENTITY
    }

    public void update(){
        //TODO UPDATE THE LOCATION OF THE ENTITY
    }
    public void draw(Graphics2D g2D){
        // TODO DRAW THE  2D ENTITY
    }

    @Override
    public  int getY() {
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
