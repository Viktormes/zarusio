package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 6;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 20;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int maxMap = 10;
    public int currentMap = 0;


    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;


    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public TileManager tm = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Sound music = new Sound();
    public Sound soundEffect = new Sound();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetManager assetManager = new AssetManager(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);

    Config config = new Config(this);
    Thread gameThread;

    public Player player = new Player(this, keyH);
    public Entity[][] itemObject = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] enemy = new Entity[maxMap][20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;

    public GamePanel() {
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
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();


        if(fullScreenOn) {
            setFullScreen();
        }

    }

    public void retry () {

        player.setDefaultPositions();
        player.restoreLifeAndMana();
        assetManager.setNPC();
        assetManager.setEnemy();
    }

    public void restart() {

        player.setDefaultValues();
        player.setItems();
        assetManager.setObject();
        assetManager.setNPC();
        assetManager.setEnemy();

    }
    public void setFullScreen() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();



    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
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
                drawToTempScreen();
                drawToScreen();
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

        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            for (int i = 0; i < enemy[1].length; i++) {
                if (enemy[currentMap][i] != null) {
                    if(enemy[currentMap][i].alive && !enemy[currentMap][i].dying){
                        enemy[currentMap][i].update();
                    }
                    if(!enemy[currentMap][i].alive) {
                        enemy[currentMap][i].checkDrop();
                        enemy[currentMap][i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState) {
            //pause menu
        }

        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                if(projectileList.get(i).alive){
                    projectileList.get(i).update();
                }
                if(!projectileList.get(i).alive) {
                    projectileList.remove(i);
                }
            }
        }

        for (int i = 0; i < particleList.size(); i++) {
            if (particleList.get(i) != null) {
                if(particleList.get(i).alive){
                    particleList.get(i).update();
                }
                if(!particleList.get(i).alive) {
                    particleList.remove(i);
                }
            }
        }

    }

    public void drawToTempScreen() {

        long drawStartTime = 0;
        if (keyH.tPressed) {
            drawStartTime = System.nanoTime();
        }

        if (gameState == titleState) {
            ui.draw(g2);
        } else {

            tm.draw(g2);

            entityList.add(player);
            if (player != null) {
                entityList.add(player);
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null && npc[i][currentMap] != null) {
                    entityList.add(npc[i][currentMap]);
                }
            }
            for (int i = 0; i < itemObject.length; i++) {
                if (itemObject[i] != null && itemObject[i][currentMap] != null) {
                    entityList.add(itemObject[i][currentMap]);
                }
            }
            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null && enemy[i][currentMap] != null) {
                    entityList.add(enemy[i][currentMap]);
                }
            }
            for (Entity entity : projectileList) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : particleList) {
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
            entityList.clear();

            ui.draw(g2);

        }

        if (keyH.tPressed) {
            long drawEndTime = System.nanoTime();
            long passed = drawEndTime - drawStartTime;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            g2.drawString("Player X: " + player.worldX / tileSize + ", Player Y: " + player.worldY / tileSize, 10, 420);
            System.out.println("Draw Time: " + passed);
        }

    }

    public void drawToScreen(){

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();

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
