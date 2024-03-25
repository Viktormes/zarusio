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
        defaultSpeed = 1;
        speed = defaultSpeed;
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

            speed = 3;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow,1);

            int i = new Random().nextInt(200) + 1;
            if (i > 197 && !projectile.alive && shotAvailableCounter == 60) {
                projectile.set(worldX, worldY, direction, true, this);

                for(int j = 0; j < gp.projectile[gp.currentMap].length; j++) {
                    if(gp.projectile[gp.currentMap][j] == null) {
                        gp.projectile[gp.currentMap][j] = projectile;
                        break;
                    }
                }


                shotAvailableCounter = 0;
            }

        }
        else {
            speed = defaultSpeed;
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
            if (i >= 50 && i < 75) {
                dropItem(new ObjectHeart(gp));
            }
            if (i >= 75 && i < 100) {
                dropItem(new ObjectMana(gp));
            }
        }
    }



