package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectPowerSocks extends Entity {



public ObjectPowerSocks(GamePanel gp) {

    super(gp);
    name = "Super Socks";
    front1 = setup("/res/items/powersocks",gp.tileSize,gp.tileSize);
    itemDescription = "[" + name + "]\nDamn. So fast!";
    collision = true;

}
}
