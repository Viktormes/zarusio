package object;

import entity.Entity;
import main.GamePanel;

public class ObjectRedPear extends Entity {

    public ObjectRedPear(GamePanel gp) {
        super(gp);
        name = "Red Pear";
        front1 = setup("/res/items/redPear",gp.tileSize,gp.tileSize);
    }
}
