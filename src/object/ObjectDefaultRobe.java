package object;

import entity.Entity;
import main.GamePanel;

public class ObjectDefaultRobe extends Entity {

    public ObjectDefaultRobe(GamePanel gp) {
        super(gp);

        type = typeRobe;
        name = "Default Robe";
        front1 = setup("/res/items/robe",gp.tileSize,gp.tileSize);
        itemDescription = "[" + name + "]\nIt's a robe.";
        defenseValue = 1;
    }
}
