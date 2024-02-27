package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 70;
        solidArea.height = 70;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 21;
        worldY = gp.tileSize * 23;
        speed = 8;
        direction = "down";

    }

    public void getPlayerImage() {

        try {

            front = ImageIO.read(getClass().getResourceAsStream("/res/player/zarusio_player_character.png"));
            front1 = ImageIO.read(getClass().getResourceAsStream("/res/player/front1.png"));
            front2 = ImageIO.read(getClass().getResourceAsStream("/res/player/front2.png"));
            back1 = ImageIO.read(getClass().getResourceAsStream("/res/player/back1.png"));
            back2 = ImageIO.read(getClass().getResourceAsStream("/res/player/back2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
        }

        catch (Exception e) {
            e.printStackTrace();
            }

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpItem(objIndex);

            if(!collisionOn) {

                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
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
    }

    public void pickUpItem(int i) {

        if(i != 999) {
            String objectName = gp.itemObject[i].name;

            switch (objectName) {
                case "key":
                    gp.playSound(0);
                    hasKey++;
                    gp.itemObject[i] = null;
                    System.out.println("Keys: " + hasKey);
                    break;
                case "door":
                    if(hasKey > 0) {
                        gp.playSound(0);
                        gp.itemObject[i] = null;
                        hasKey--;
                        System.out.println("Keys: " + hasKey);
                    }
                    break;
                    case "superSocks":
                        gp.playSound(0);
                        speed += 1;
                        gp.itemObject[i] = null;
                        break;
            }
        }
    }

    public void draw (Graphics2D g2d) {

        BufferedImage bufferedImage = null;

        switch(direction) {
            case "up":
                if (spriteNumber == 1){
                    bufferedImage = back1;
                }
                if (spriteNumber == 2){
                    bufferedImage = back2;
                }
                break;
            case "down":
                if (spriteNumber == 1){
                    bufferedImage = front1;
                }
                if (spriteNumber == 2){
                    bufferedImage = front2;
                }
                break;
            case "left":
                if (spriteNumber == 1){
                    bufferedImage = left1;
                }
                if (spriteNumber == 2){
                    bufferedImage = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1){
                    bufferedImage = right1;
                }
                if (spriteNumber == 2){
                    bufferedImage = right2;
                }
                break;
        }
        g2d.drawImage(bufferedImage, screenX, screenY, gp.tileSize, gp.tileSize,null);

    }

}
