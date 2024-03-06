package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Devil extends Entity{

    public NPC_Devil(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;


        getNpcImage();

    }

    public void getNpcImage() {

        front1 = setup("/res/NPC/NPCfront1");
        front2 = setup("/res/NPC/NPCfront2");
        back1 = setup("/res/NPC/NPCback1");
        back2 = setup("/res/NPC/NPCback2");
        right1 = setup("/res/NPC/NPCfront1");
        right2 = setup("/res/NPC/NPCfront2");
        left1 = setup("/res/NPC/NPCback1");
        left2 = setup("/res/NPC/NPCback2");

    }

    public void setAction() {


        actionLockCounter++;

        if (actionLockCounter == 120) {
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
