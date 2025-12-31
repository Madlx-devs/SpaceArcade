package Scenes;

import Assets.Bullet;
import Assets.EnemyShip;
import Assets.HealthBar;
import Assets.PlayerShip;
import utils.BulletHandling;
import utils.CollisionDetection;
import utils.PlayerKeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public  class GamePanel extends JPanel implements CollisionDetection,Runnable {
    public static  final int WIDTH =1080;
    public static final int HEIGHT =560;
    private static GamePanel gamePanel=null;
    Thread gameThread;
    PlayerShip playerShip ;
    EnemyShip enemyShip;
    Bullet bullet;
    BulletHandling bulletHandling;
    private BufferedImage backgroundImage;
    PlayerKeyHandler keyHandler;
     int hitCount =0;



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
        this.bulletHandling=new BulletHandling();
        this.addMouseListener(bulletHandling);

        //added assets
        add(OverlayScreen.getLayeredPanel());
        enemyShip = new EnemyShip();
        playerShip=new PlayerShip(this.keyHandler);
        bullet=new Bullet(bulletHandling);
        bullet.setX(playerShip.getX()+bullet.getWidth());
        bullet.setY(playerShip.getY()/2);
        loadImage();

        //game thread
        gameThread=new Thread(this);
        gameThread.start();
    }


     void loadImage(){
      try{
         backgroundImage= ImageIO.read(
                 Objects.requireNonNull(getClass().getResourceAsStream("/images/scene.png")));
      } catch (IOException e) {
          e.printStackTrace();
      }
     }
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

        while (gameThread != null) {

            long now = System.nanoTime();
            delta += (now - lastTime) / NS_PER_UPDATE;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;

            }
            if(detectCollision(playerShip,enemyShip)) {
                if(hitCount==0){
                    HealthBar.updateHealth();
                    System.out.println(HealthBar.HEALTH);
                    hitCount++;
                }
                else{
                    hitCount=0;
                }

            }

            repaint();
        }
        System.out.println(HealthBar.HEALTH);
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
    }
}
