package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gp) {

        super(gp);
        name = "heart";
        image = setup("/res/items/fullHeart",gp.tileSize,gp.tileSize);
        image2 = setup("/res/items/halfHeart",gp.tileSize,gp.tileSize);
        image3 = setup("/res/items/emptyHeart",gp.tileSize,gp.tileSize);
    }
}
