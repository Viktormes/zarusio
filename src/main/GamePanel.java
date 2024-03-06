package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 6;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    public TileManager tm = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetManager assetManager = new AssetManager(this);
    public UI ui = new UI(this);

    Thread gameThread;

    public Player player = new Player(this, keyH);
    public SuperObject[] itemObject = new SuperObject[10];

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        assetManager.setObject();

       // playMusic(1);
        gameState = playState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();


    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {

        if(gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            //pause menu
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        long drawStartTime = 0;
        if(keyH.tPressed) {
            drawStartTime = System.nanoTime();
        }


        tm.draw(g2d);

        for (int i = 0; i < itemObject.length; i++) {
            if (itemObject[i] != null) {
                itemObject[i].draw(g2d, this);
            }
        }

        player.draw(g2d);

        ui.draw(g2d);


        if(keyH.tPressed) {
            long drawEndTime = System.nanoTime();
            long passed = drawEndTime - drawStartTime;
            g2d.setColor(Color.WHITE);
            g2d.drawString("Draw Time: " + passed , 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2d.dispose();


    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSound(int i) {

        soundEffect.setFile(i);
        soundEffect.play();
    }
}
