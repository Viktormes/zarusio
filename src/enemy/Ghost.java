package enemy;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class Ghost extends Entity {

    GamePanel gp;

    public Ghost(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeEnemy;
        name = "Ghost";
        speed = 3;
        maxHealth = 10;
        currentHealth = maxHealth;
        attack = 4;
        defense = 1;
        experience = 5;

        getImage();

    }
    public void getImage() {

        front1 = setup("/res/enemy/ghost1",gp.tileSize,gp.tileSize);
        front2 = setup("/res/enemy/ghost2",gp.tileSize,gp.tileSize);
        back1 = setup("/res/enemy/ghost1",gp.tileSize,gp.tileSize);
        back2 = setup("/res/enemy/ghost2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/enemy/ghost1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/enemy/ghost2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/enemy/ghost1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/enemy/ghost2",gp.tileSize,gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 240) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
