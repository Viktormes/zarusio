package enemy;

import entity.Entity;
import main.GamePanel;
import object.ObjectCoin;
import object.ObjectHeart;
import object.ObjectMana;
import object.ObjectPlatinumCoin;

import java.util.Random;

public class Ghost extends Entity {

    GamePanel gp;

    public Ghost(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeEnemy;
        name = "Ghost";
        speed = 3;
        maxHealth = 15;
        currentHealth = maxHealth;
        attack = 6;
        defense = 1;
        experience = 10;

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

    public void update(){

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (!onPath && tileDistance < 5) {

            int i = new Random().nextInt(100) + 1;
            if (i > 60) {
                onPath = true;
            }
        }
        if(onPath && tileDistance > 20) {
            onPath = false;
        }
    }

    public void setAction() {

        if(onPath){

            speed = 4;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);
        }
        else {
            speed = 3;
            actionLockCounter++;

            if (actionLockCounter == 480) {
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
    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop() {
        Random random = new Random();
        int i = random.nextInt(100) + 1;

        if (i < 50) {
            dropItem(new ObjectCoin(gp));
        }
        if (i > 50 && i < 75) {
            dropItem(new ObjectPlatinumCoin(gp));
        }
    }
}
