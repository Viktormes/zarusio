package object;

import entity.Entity;
import main.GamePanel;

public class ObjectCoin extends Entity {

    GamePanel gp;

    public ObjectCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickUp;
        name = "Gold Coin";
        value = 1;
        front1 = setup("/res/items/coin",gp.tileSize,gp.tileSize);
    }

    public void use(Entity entity) {
        gp.playSound(9);
        gp.ui.addMessage("Gold + " + value);
        gp.player.gold += value;
    }
}
