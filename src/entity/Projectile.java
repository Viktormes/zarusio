package entity;

import main.GamePanel;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gp) {

        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.currentHealth = maxHealth;
    }

    public void update() {


        if (user == gp.player) {
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
            if(monsterIndex != 999) {
                gp.player.damageEnemy(monsterIndex, attack);
                alive = false;
            }

        }
        if (user != gp.player){

        }


        switch(direction) {
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

        currentHealth--;
        if (currentHealth <= 0) {
            alive = false;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if(spriteNumber == 1){
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
}

