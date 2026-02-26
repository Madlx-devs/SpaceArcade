package Scenes;

import Assets.Bullet;
import Assets.EnemyShip;
import Assets.Health;
import Assets.PlayerShip;
import utils.BulletHandling;
import utils.CollisionDetection;
import utils.PlayerKeyHandler;
import utils.RestartHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements CollisionDetection,Runnable {
    public static  final int WIDTH =1080;
    public static final int HEIGHT =560;



    //game panel object
    private static GamePanel gamePanel;
    Thread gameThread;
    PlayerShip playerShip ;
    EnemyShip enemyShip;
    Bullet bullet;
    RestartHandler restartHandler;
    BulletHandling bulletHandling;
    private BufferedImage backgroundImage;
    PlayerKeyHandler keyHandler;
    Health health;
    int score = 0;



    private GamePanel(){
        //
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

        //keyHandling && mouseHandling
        keyHandler= new PlayerKeyHandler();
        this.addKeyListener(keyHandler);
        restartHandler=new RestartHandler();
        this.addKeyListener(restartHandler);
        this.bulletHandling=new BulletHandling();
        this.addMouseListener(bulletHandling);

        //added assets
        enemyShip = new EnemyShip();
        playerShip=new PlayerShip(this.keyHandler);
        bullet = new Bullet(bulletHandling);
        health=new Health();
        loadImage();

        //game thread
        gameThread=new Thread(this);
        gameThread.start();
    }


     void loadImage(){
         try {
             backgroundImage= ImageIO.read(
                     Objects.requireNonNull(getClass().getResourceAsStream("/images/scene.png")));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

     //painting the components
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)(g);
        g2.drawImage(backgroundImage,0,0,WIDTH, HEIGHT,null);
        render(g2);
    }
   /*
    Static factory method
    returns a singleton instance of the GamePanel class
    follows singleton design pattern
    */
    public static GamePanel getGamePanel() {
        if(gamePanel==null){
            gamePanel= new GamePanel();
        }
        return gamePanel;
    }

    @Override
    public void run() {

        int FPS = 60;
        final double NS_PER_UPDATE = 1_000_000_000.0 / FPS; //maximum time a frame should take to render
        long lastTime = System.nanoTime();
        double delta = 0;

        while (gameThread!=null){

            long now = System.nanoTime();
            delta += (now - lastTime) / NS_PER_UPDATE;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;

            }

//            while(detectCollision(playerShip,enemyShip)) {
//                Health.updateHealth();
//                repaint();
//            }
            repaint();
        }
    }

    public void update(){
        enemyShip.update();
        playerShip.update();
        bullet.update();
    }

    public void render(Graphics2D g2){
        playerShip.draw(g2);
        enemyShip.draw(g2);
        bullet.draw(g2);
        health.draw(g2);

    }

    void updateScore(){
      if(detectCollision( bullet , enemyShip )){
          score+=10;
      }
    }

    void restart(){
        score =0;
     playerShip.setDefaultValues();
     gameThread = new Thread(this);
    }
}
