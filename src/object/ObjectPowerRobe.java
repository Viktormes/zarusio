package object;


import entity.Entity;
import main.GamePanel;

public class ObjectPowerRobe extends Entity {
    public ObjectPowerRobe(GamePanel gp) {
        super(gp);
        type = typeRobe;
        name = "Power Robe";
        front1 = setup("/res/items/powerRobe", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        itemDescription = "[Power Robe]\nA robe. It's powerful.";
        price = 50;
    }
}
