package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectPowerSocks extends Entity {



public ObjectPowerSocks(GamePanel gp) {

    super(gp);
    name = "superSocks";
    front1 = setup("/res/items/powersocks",gp.tileSize,gp.tileSize);
    collision = true;

}
}
