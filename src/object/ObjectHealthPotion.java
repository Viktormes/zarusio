package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class ObjectHealthPotion extends Entity {

    GamePanel gp;


    public ObjectHealthPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = typeConsumable;
        name = "Health Potion";
        value = 3;
        front1 = setup("/res/items/healthPotion", gp.tileSize, gp.tileSize);
        itemDescription = "[Health Potion]\nRestores " + value + " health.";

    }

    public void use(Entity entity) {

        gp.gameState = gp.playState;
        gp.ui.addFloatingText(String.valueOf(value), gp.player.screenX + 40, gp.player.screenY + 20, Color.GREEN);
        entity.currentHealth += value;

    }
}
