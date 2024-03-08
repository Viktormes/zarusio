package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectChest extends Entity {


    public ObjectChest(GamePanel gp) {
        super(gp);

        name = "chest";
        front1 = setup("/res/items/chest");
    }
}
