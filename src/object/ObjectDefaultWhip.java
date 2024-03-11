package object;

import entity.Entity;
import main.GamePanel;

public class ObjectDefaultWhip extends Entity {

    public ObjectDefaultWhip(GamePanel gp) {
        super(gp);

        name = "Default Whip";
        front1 = setup("/res/items/whip",gp.tileSize,gp.tileSize);

        attackValue = 1;
    }

}
