package object;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class ObjectRock extends Projectile {

    GamePanel gp;

    public ObjectRock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 8;
        maxHealth = 80;
        currentHealth = maxHealth;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {

        back1 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        back2 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        front1 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        front2 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        left1 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        left2 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        right1 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
        right2 = setup("/res/projectiles/rock",gp.tileSize,gp.tileSize);
    }

    public Color getParticleColor() {
        Color color = new Color(80,50,0);
        return color;
    }
    public int getParticleSize() {
        int size = 10;
        return size;
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxHealth() {
        int maxHealth = 20;
        return maxHealth;
    }
}
