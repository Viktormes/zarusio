package object;

import entity.Entity;
import main.GamePanel;

public class ObjectAxe extends Entity {

        public ObjectAxe(GamePanel gp) {
            super(gp);
            type = typeAxe;
            name = "Woodcutting Axe";
            front1 = setup("/res/items/axe", gp.tileSize, gp.tileSize);
            attackValue = 2;
            itemDescription = "[Woodcutting Axe]\nAn axe. It cuts wood.";
            price = 50;
            knockBackPower = 10;
        }
}
