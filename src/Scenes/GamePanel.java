package Scenes;

import Assets.Bullet;
import Assets.EnemyShip;
import Assets.Health;
import Assets.PlayerShip;
import Assets.pools.BulletPool;
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

public class GamePanel extends JPanel implements Runnable {
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
    BulletPool bulletPool= new BulletPool(10);



    private GamePanel(){
        //window settings
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
        this.addKeyListener(bulletHandling);

        //game assets
        enemyShip = new EnemyShip();
        playerShip=new PlayerShip(this.keyHandler);
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
            if(bulletHandling.isShooting()){
               // if shooting is true take a bullet from the pool inject the dependencies and spawn it
                //this will  be called 60 times per second if the   player presses the shoot button
                // but in pool there are only 10 bullets so if the player shoots more than 10 times in a short period of time the pool will create new bullets but if the player releases the shoot button and shoots again the pool will reuse the bullets that are not in use anymore
                bullet=bulletPool.acquireObject();

                bullet.injectDependencies(playerShip, bulletPool, enemyShip);
                if(bullet!=null){
                    bullet.spawn();
                }
            }

            for(Bullet b : bulletPool.getInUse()){
                b.update();
            }
    }

    public void render(Graphics2D g2){
        playerShip.draw(g2);
        enemyShip.draw(g2);
        for(Bullet bullet: bulletPool.getInUse()){
            bullet.draw(g2);
        }
        health.draw(g2);

    }

    void updateScore(){
        score++;
    }

    void restart(){
        score =0;
     playerShip.setDefaultValues();
     gameThread = new Thread(this);
    }
}
