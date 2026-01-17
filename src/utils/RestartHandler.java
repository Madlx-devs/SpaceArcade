package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RestartHandler implements KeyListener {

     public boolean restartPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
        if(code==KeyEvent.VK_SPACE){

            restartPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
