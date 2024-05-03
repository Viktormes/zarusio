package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class ObjectArrow extends Projectile {

    GamePanel gp;

    public ObjectArrow(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Arrow";
        speed = 12;
        maxHealth = 100;
        currentHealth = maxHealth;
        useCost = 0;
        attack = 2 + gp.player.dexterity /3;
        alive = false;
        knockBackPower = 2;
        getImage();
    }

    public void getImage() {
        back1 = setup("/res/projectiles/arrowUp",gp.tileSize,gp.tileSize);
        back2 = setup("/res/projectiles/arrowUp",gp.tileSize,gp.tileSize);
        front1 = setup("/res/projectiles/arrowDown",gp.tileSize,gp.tileSize);
        front2 = setup("/res/projectiles/arrowDown",gp.tileSize,gp.tileSize);
        left1 = setup("/res/projectiles/arrowLeft",gp.tileSize,gp.tileSize);
        left2 = setup("/res/projectiles/arrowLeft",gp.tileSize,gp.tileSize);
        right1 = setup("/res/projectiles/arrowRight",gp.tileSize,gp.tileSize);
        right2 = setup("/res/projectiles/arrowRight",gp.tileSize,gp.tileSize);
    }
    @Override
    public Color getParticleColor() {
        Color color = new Color(80,50,0);
        return color;
    }
    @Override
    public int getParticleSize() {
        int size = 10;
        return size;
    }
    @Override
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    @Override
    public int getParticleMaxHealth() {
        int maxHealth = 20;
        return maxHealth;
    }

    @Override
    public boolean hasResource(Entity user) {
        return true;
    }
}
