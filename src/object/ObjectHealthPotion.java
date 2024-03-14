package object;

import entity.Entity;
import main.GamePanel;

public class ObjectHealthPotion extends Entity {

    GamePanel gp;
    int value = 3;

    public ObjectHealthPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeConsumable;
        name = "Health Potion";
        front1 = setup("/res/items/healthPotion",gp.tileSize,gp.tileSize);
        itemDescription = "[Health Potion]\nRestores "+ value + " health.";

    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogState;
        gp.ui.currentDialog = "You used a " + name + "!\n"
        + "You gained " + value + " health.";

        entity.currentHealth += value;
        if (gp.player.currentHealth > gp.player.maxHealth) {
            gp.player.currentHealth = gp.player.maxHealth;
        }
        gp.playSound(6);

    }
}
