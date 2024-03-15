package object;

import entity.Entity;
import main.GamePanel;

public class ObjectPlatinumCoin extends Entity {

    GamePanel gp;

    public ObjectPlatinumCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickUp;
        name = "Platinum Coin";
        value = 5;
        front1 = setup("/res/items/platinumCoin", gp.tileSize, gp.tileSize);

    }
    public void use(Entity entity) {
        gp.playSound(9);
        gp.ui.addMessage("Gold + " + value);
        gp.player.gold += value;
    }
}
