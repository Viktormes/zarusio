package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


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

        front = setup("zarusio_player_character");
        front1 = setup("front1");
        front2 = setup("front2");
        back1 = setup("back1");
        back2 = setup("back2");
        right1 = setup("right1");
        right2 = setup("right2");
        left1 = setup("left1");
        left2 = setup("left2");

    }

    public BufferedImage setup (String imageName) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
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

            if (objectName.equals("superSocks")) {
                gp.playSound(2);
                speed += 3;
                gp.itemObject[i] = null;
                gp.ui.drawMessage("You found super socks! You can now move faster!");
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
        g2d.drawImage(bufferedImage, screenX, screenY,null);

    }

}
