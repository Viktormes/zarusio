package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectDoor extends Entity {

        public ObjectDoor(GamePanel gp) {

            super(gp);

            name = "door";
            front1 = setup("/res/items/door",gp.tileSize,gp.tileSize);
            collision = true;

        }
}
