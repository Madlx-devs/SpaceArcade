package Scenes;

import assets.*;
import assets.bullet.Bullet;
import assets.bullet.BulletManager;
import assets.bullet.BulletHandling;
import utils.PlayerKeyHandler;
import utils.RestartHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static utils.CollisionDetection.detectCollision;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 560;


    //game panel object
    private static GamePanel gamePanel;

    private Thread gameThread;
    private final PlayerShip playerShip;
    private EnemyShip enemyShip;
    private Bullet bullet;

    //restart handler and bullet handling
    private RestartHandler restartHandler;
    private BulletHandling bulletHandling;
    private PlayerKeyHandler keyHandler;

    //image
    private static final BufferedImage backgroundImage;
    private final Health health;

    private final BulletManager bulletManager;
    private final EnemyManager enemyManager;
    private volatile boolean isGameOver = false;
    private volatile boolean isPaused = false;

    int score = 0;

    //constructor
    private GamePanel() {
        //window settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

        //keyHandling && mouseHandling  initialization
        keyHandler = new PlayerKeyHandler();
        restartHandler = new RestartHandler();
        bulletHandling = new BulletHandling();

        //add listeners
        this.addKeyListener(keyHandler);
        this.addKeyListener(restartHandler);
        this.addKeyListener(bulletHandling);

        //game assets
        playerShip = new PlayerShip(this.keyHandler);

        //health initialization
        health = new Health();

        enemyManager = new EnemyManager();
        bulletManager = new BulletManager(playerShip.getX(), playerShip.getY(), enemyShip != null ? enemyShip.getX() : 0, enemyShip != null ? enemyShip.getY() : 0);

        //game thread
        gameThread = new Thread(this);
        gameThread.start();
    }


    static {
        try {
            backgroundImage = ImageIO.read(
                    Objects.requireNonNull(GamePanel.class.getResourceAsStream("/images/scene.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //painting the components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) (g);
        g2.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);
        render(g2);
    }

    //singleton pattern for game panel
    public static GamePanel getGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;

    }

    //game loop
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
                repaint();

            }

        }
    }

    public void update() {
        if (!isGameOver || !isPaused) {
            playerShip.update();
            bulletManager.update(bulletHandling.isShooting());
            enemyManager.update();
        }
    }

    public void render(Graphics2D g2) {
        playerShip.draw(g2);
        bulletManager.draw(g2);
        health.draw(g2);
        enemyManager.draw(g2);
        for (int i = enemyManager.getEntities().size(); i > 0; i--) {
            EnemyShip enemy = enemyManager.getEntities().get(i - 1);
            if (detectCollision(playerShip, enemy)) {
                Bang bang = new Bang(playerShip.getX(), playerShip.getY());
                bang.draw(g2);
                enemy.setIsActive(false);
                gameThread = null;
            }

            for (int j = bulletManager.getEntities().size(); j > 0; j--) {
                Bullet bullet = bulletManager.getEntities().get(j - 1);
                if (detectCollision(bullet, enemy)) {
                    Bang bang = new Bang(enemy.getX(), enemy.getY());
                    bang.draw(g2);
                    updateScore();
                    enemy.setIsActive(false);
                    enemyManager.remove(enemy);
                    bullet.setActive(false);
                    bulletManager.getEntities().remove(bullet);
                }
            }
        }
        g2.drawString("score:" + score, WIDTH - 100, 20);
    }

    //score update
    void updateScore() {
        score++;

    }

    //restart the game
    void restart() {
        score = 0;
        playerShip.setDefaultValues();
        gameThread = new Thread(this);
    }

    public void pauseGame() {
       isPaused=true;
    }
}
