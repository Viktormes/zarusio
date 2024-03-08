package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage mainTitleScreenImage, front1, front2, back1, back2, left1, left2, right1, right2;
    public String direction ="down";

    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 70, 70);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    String[] dialogues = new String[20];
    int dialogIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int type;

    public int maxHealth;
    public int currentHealth;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void speak() {
        if(dialogues[dialogIndex] == null){
            dialogIndex = 0;
        }
        gp.ui.currentDialog = dialogues[dialogIndex];
        dialogIndex++;

        switch (gp.player.direction){
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this,false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.enemy);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer) {
            if(!gp.player.invincible) {
                gp.player.currentHealth -= 1;
                gp.player.invincible = true;
            }

        }

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage bufferedImage = null;
        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                switch (direction) {
                    case "up":
                        if (spriteNumber == 1) {
                            bufferedImage = back1;
                        }
                        if (spriteNumber == 2) {
                            bufferedImage = back2;
                        }
                        break;
                    case "down":
                        if (spriteNumber == 1) {
                            bufferedImage = front1;
                        }
                        if (spriteNumber == 2) {
                            bufferedImage = front2;
                        }
                        break;
                    case "left":
                        if (spriteNumber == 1) {
                            bufferedImage = left1;
                        }
                        if (spriteNumber == 2) {
                            bufferedImage = left2;
                        }
                        break;
                    case "right":
                        if (spriteNumber == 1) {
                            bufferedImage = right1;
                        }
                        if (spriteNumber == 2) {
                            bufferedImage = right2;
                        }
                        break;
                }

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (gp.player.screenX > gp.player.worldX) {
                    screenX = worldX;
                }
                if (gp.player.screenY > gp.player.worldY) {
                    screenY = worldY;
                }
                int rightOffSet = gp.screenWidth - gp.player.screenX;
                if (rightOffSet > gp.worldWidth - gp.player.worldX) {
                    screenX = gp.screenWidth - (gp.worldWidth - worldX);
                }
                int bottomOffSet = gp.screenHeight - gp.player.screenY;
                if (bottomOffSet > gp.worldHeight - gp.player.worldY) {
                    screenY = gp.screenHeight - (gp.worldHeight - worldY);
                } else if (gp.player.screenX > gp.player.worldX ||
                        gp.player.screenY > gp.player.worldY ||
                        rightOffSet > gp.worldWidth - gp.player.worldX ||
                        bottomOffSet > gp.worldHeight - gp.player.worldY) {
                    g2.drawImage(bufferedImage, screenX, screenY, null);
                }

                g2.drawImage(bufferedImage, screenX, screenY, null);
            }
        }
    }

    public BufferedImage setup(String imagePath) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
