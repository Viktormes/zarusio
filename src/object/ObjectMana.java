package object;

import entity.Entity;
import main.GamePanel;

public class ObjectMana extends Entity {

    GamePanel gp;

    public ObjectMana(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Mana";

        image = setup("/res/items/mana",gp.tileSize,gp.tileSize);
        image2 = setup("/res/items/blankMana",gp.tileSize,gp.tileSize);


    }
}
