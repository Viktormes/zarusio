package object;

import entity.Entity;
import main.GamePanel;

public class ObjectChest extends Entity {


    public ObjectChest(GamePanel gp) {
        super(gp);

        name = "chest";
        front1 = setup("/res/items/chest",gp.tileSize,gp.tileSize);
    }
}
