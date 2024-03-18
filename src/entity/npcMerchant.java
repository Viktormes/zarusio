package entity;

import main.GamePanel;
import object.ObjectAxe;
import object.ObjectHealthPotion;
import object.ObjectKey;
import object.ObjectPowerRobe;


public class npcMerchant extends Entity {

    public npcMerchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;


        getNpcImage();
        setDialog();
        setItems();
    }
    public void getNpcImage() {

        front1 = setup("/res/NPC/merchantNpc1",gp.tileSize,gp.tileSize);
        front2 = setup("/res/NPC/merchantNpc2",gp.tileSize,gp.tileSize);
        back1 = setup("/res/NPC/merchantNpc1",gp.tileSize,gp.tileSize);
        back2 = setup("/res/NPC/merchantNpc2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/NPC/merchantNpc1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/NPC/merchantNpc2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/NPC/merchantNpc1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/NPC/merchantNpc2",gp.tileSize,gp.tileSize);

    }

    public void setDialog() {

        dialogues[0] = "Welcome to my shop! \nI have many items for sale!";
    }

    public void setItems() {

        inventory.add(new ObjectHealthPotion(gp));
        inventory.add(new ObjectHealthPotion(gp));
        inventory.add(new ObjectAxe(gp));
        inventory.add(new ObjectPowerRobe(gp));
        inventory.add(new ObjectKey(gp));
        inventory.add(new ObjectKey(gp));

    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.merchant = this;
    }
}
