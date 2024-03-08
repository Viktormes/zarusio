package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public TileManager tm = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetManager assetManager = new AssetManager(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);

    Thread gameThread;

    public Player player = new Player(this, keyH);
    public Entity[] itemObject = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] enemy = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;

    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        assetManager.setObject();
        assetManager.setNPC();
        assetManager.setEnemy();
       // playMusic(1);
        gameState = titleState;
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
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }

            for (Entity entity : enemy) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if (gameState == pauseState) {
            //pause menu
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStartTime = 0;
        if(keyH.tPressed) {
            drawStartTime = System.nanoTime();
        }

        if (gameState == titleState) {
            ui.draw(g2);
        }

        else {

            tm.draw(g2);

            entityList.add(player);
            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : itemObject) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : enemy) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            for(int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

                    ui.draw(g2);

        }

        if(keyH.tPressed) {
            long drawEndTime = System.nanoTime();
            long passed = drawEndTime - drawStartTime;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed , 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();

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
