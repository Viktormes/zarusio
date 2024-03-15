package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class ObjectFrostBolt extends Projectile {

    GamePanel gp;

    public ObjectFrostBolt(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Frost Bolt";
        speed = 8;
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

}
