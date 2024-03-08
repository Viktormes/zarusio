package enemy;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class PlantMonster extends Entity {

    public PlantMonster(GamePanel gp) {
        super(gp);

        type = 2;
        name = "plantMonster";
        speed = 1;
        maxHealth = 4;
        currentHealth = maxHealth;



        getImage();
    }

    public void getImage() {

        front1 = setup("/res/enemy/plantMonsterFront1");
        front2 = setup("/res/enemy/plantMonsterFront2");
        back1 = setup("/res/enemy/plantMonsterFront1");
        back2 = setup("/res/enemy/plantMonsterFront2");
        left1 = setup("/res/enemy/plantMonsterFront1");
        left2 = setup("/res/enemy/plantMonsterFront2");
        right1 = setup("/res/enemy/plantMonsterFront1");
        right2 = setup("/res/enemy/plantMonsterFront2");
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

}

