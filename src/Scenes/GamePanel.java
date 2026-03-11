package Scenes;

import Assets.bullet.Bullet;
import Assets.EnemyShip;
import Assets.Health;
import Assets.PlayerShip;
import Assets.bullet.BulletManager;
import Assets.bullet.BulletHandling;
import Assets.pools.EnemyManager;
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
    private Thread gameThread;
    private PlayerShip playerShip ;
    private EnemyShip enemyShip;
    private Bullet bullet;
    private RestartHandler restartHandler;
    private BulletHandling bulletHandling;
    private BufferedImage backgroundImage;
    private PlayerKeyHandler keyHandler;
    private final Health health;
    int score = 0;
    private final BulletManager bulletManager;
    private EnemyManager enemyManager;




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
        this.bulletManager= new BulletManager(playerShip,enemyShip);
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
                repaint();

            }

//            while(detectCollision(playerShip,enemyShip)) {
//                Health.updateHealth();
//                repaint();
//            }
           // repaint();
        }
    }

    public void update(){
        enemyShip.update();
        playerShip.update();
        bulletManager.updateBullets(bulletHandling.isShooting());
    }

    public void render(Graphics2D g2){
        playerShip.draw(g2);
        enemyShip.draw(g2);
        bulletManager.drawBullets(g2);
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
