package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class ObjectFrostBolt extends Projectile {

    GamePanel gp;

    public ObjectFrostBolt(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Frost Bolt";
        speed = 12;
        maxHealth = 80;
        currentHealth = maxHealth;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {

        back1 = setup("/res/spells/frostBoltUp",gp.tileSize,gp.tileSize);
        back2 = setup("/res/spells/frostBoltUp",gp.tileSize,gp.tileSize);
        front1 = setup("/res/spells/frostBoltDown",gp.tileSize,gp.tileSize);
        front2 = setup("/res/spells/frostBoltDown",gp.tileSize,gp.tileSize);
        left1 = setup("/res/spells/frostBoltLeft",gp.tileSize,gp.tileSize);
        left2 = setup("/res/spells/frostBoltLeft",gp.tileSize,gp.tileSize);
        right1 = setup("/res/spells/frostBoltRight",gp.tileSize,gp.tileSize);
        right2 = setup("/res/spells/frostBoltRight",gp.tileSize,gp.tileSize);

    }

    public boolean hasResource(Entity user) {

        boolean hasResource = false;
        if (user.currentMana >= useCost) {
            hasResource = true;
        } else {
            hasResource = false;
        }
        return hasResource;
    }

    public void useResource(Entity user) {
        user.currentMana -= useCost;
    }

    public Color getParticleColor() {
        Color color = Color.CYAN;
        return color;
    }
    public int getParticleSize() {
        int size = 12;
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
