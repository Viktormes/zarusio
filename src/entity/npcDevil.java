package entity;

import main.GamePanel;

import java.util.Random;

public class npcDevil extends Entity {

    public npcDevil(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;


        getNpcImage();
        setDialog();
    }

    public void getNpcImage() {

        front1 = setup("/res/NPC/NPCfront1",gp.tileSize,gp.tileSize);
        front2 = setup("/res/NPC/NPCfront2",gp.tileSize,gp.tileSize);
        back1 = setup("/res/NPC/NPCback1",gp.tileSize,gp.tileSize);
        back2 = setup("/res/NPC/NPCback2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/NPC/NPCfront1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/NPC/NPCfront2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/NPC/NPCback1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/NPC/NPCback2",gp.tileSize,gp.tileSize);

    }

    public void setDialog() {

        dialogues[0] = "I am the devil. I will show you where to heal. \nFollow me!";
        dialogues[1] = "Here you can drink the water of life... or death?";
    }

    public void setAction() {


        if(onPath){

            int goalCol = 23;
            int goalRow = 8;
            //int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            //int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol,goalRow);
        }
        else {
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

    public void speak() {

        super.speak();

        onPath = true;
        speed = 3;

    }

}
