package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage mainTitleScreenImage, front1, front2, back1, back2, left1, left2, right1, right2;
    public BufferedImage attackFront1, attackFront2, attackBack1, attackBack2,
            attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";

    public BufferedImage image, image2, image3;
    public String name;
    public int type;
    public final int typePlayer = 0;
    public final int typeNPC = 1;
    public final int typeEnemy = 2;
    public final int typeWhip = 3;
    public final int typeAxe = 4;
    public final int typeRobe = 5;
    public final int typeConsumable = 6;
    public final int typePickUp = 7;

    public int value;
    public int attackValue;
    public int defenseValue;
    public String itemDescription = "";

    public int spriteNumber = 1;
    String[] dialogues = new String[20];
    int dialogIndex = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 70, 70);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;


    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;


    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;


    public int maxHealth;
    public int currentHealth;
    public int maxMana;
    public int currentMana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int experience;
    public int nextLevelExperience;
    public int gold;
    public Entity currentWeapon;
    public Entity currentRobe;
    public Projectile projectile;

    public int useCost;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void damageReaction() {}

    public void speak() {
        if (dialogues[dialogIndex] == null) {
            dialogIndex = 0;
        }
        gp.ui.currentDialog = dialogues[dialogIndex];
        dialogIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void use(Entity entity) {}
    public void update() {

        setAction();

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.enemy);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == typeEnemy && contactPlayer) {
            damagePlayer(attack);
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

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 60) {
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack){

        if (!gp.player.invincible) {

            int damage = attack - gp.player.defense;
            if (damage < 0) {
                damage = 0;
            }
            gp.player.currentHealth -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage bufferedImage = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
            }
        }
        if (type == 2 && hpBarOn) {

            double oneScale = (double) gp.tileSize / maxHealth;
            double hpBarValue = oneScale * currentHealth;

            g2.setColor(Color.BLACK);
            g2.fillRoundRect(screenX - 1, screenY - 16, gp.tileSize + 2, 19, 5, 5);
            g2.setColor(new Color(190, 8, 8));
            g2.fillRoundRect(screenX, screenY - 15, (int) hpBarValue, 17, 5, 5);

            hpBarCounter++;

            if (hpBarCounter > 600) {
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }
        if (invincible) {
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }
        if (dying) {
            dyingAnimation(g2);
        }

        g2.drawImage(bufferedImage, screenX, screenY, null);

        changeAlpha(g2, 1f);
    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }

        if (dyingCounter > i * 8) {

            alive = false;
        }

    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
