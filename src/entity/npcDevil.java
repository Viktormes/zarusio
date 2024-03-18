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

        dialogues[0] = "I am the devil";
        dialogues[1] = "Welcome to my domain! MOHAHA \n*cough* *cough* HAHAA";
        dialogues[2] = "So you've come here to DIE?!";
        dialogues[3] = "You will never leave this place alive!";
    }

    public void setAction() {


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

    public void speak() {

        super.speak();

    }

}