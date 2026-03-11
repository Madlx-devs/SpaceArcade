package Assets.bullet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BulletHandling implements KeyListener  {
    private boolean shooting;

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code= e.getKeyCode();
        if(code==KeyEvent.VK_SPACE){
            setShooting(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_SPACE){
            setShooting(false);
        }
    }
}
