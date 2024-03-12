package object;

import entity.Entity;
import main.GamePanel;

public class ObjectDefaultWhip extends Entity {

    public ObjectDefaultWhip(GamePanel gp) {
        super(gp);

        type = typeWhip;
        name = "Default Whip";
        front1 = setup("/res/items/whip",gp.tileSize,gp.tileSize);
        itemDescription = "[" + name + "]\nIt's not much, but it's a whip.";
        attackValue = 1;
    }

}
