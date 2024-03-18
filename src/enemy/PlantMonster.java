package enemy;

import entity.Entity;
import main.GamePanel;
import object.ObjectCoin;
import object.ObjectHeart;
import object.ObjectMana;
import object.ObjectRock;

import java.util.Random;

public class PlantMonster extends Entity {

    GamePanel gp;

    public PlantMonster(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeEnemy;
        name = "Plant Monster";
        speed = 1;
        maxHealth = 4;
        currentHealth = maxHealth;
        attack = 3;
        defense = 0;
        experience = 3;
        projectile = new ObjectRock(gp);


        getImage();
    }

    public void getImage() {

        front1 = setup("/res/enemy/plantMonsterFront1",gp.tileSize,gp.tileSize);
        front2 = setup("/res/enemy/plantMonsterFront2",gp.tileSize,gp.tileSize);
        back1 = setup("/res/enemy/plantMonsterFront1",gp.tileSize,gp.tileSize);
        back2 = setup("/res/enemy/plantMonsterFront2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/enemy/plantMonsterFront1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/enemy/plantMonsterFront2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/enemy/plantMonsterFront1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/enemy/plantMonsterFront2",gp.tileSize,gp.tileSize);
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

        int i = new Random().nextInt(100) + 1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 60) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
        }

        public void checkDrop() {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i < 50) {
                dropItem(new ObjectCoin(gp));
            }
            if (i >= 50 && i < 75) {
                dropItem(new ObjectHeart(gp));
            }
            if (i >= 75 && i < 100) {
                dropItem(new ObjectMana(gp));
            }
        }
    }



