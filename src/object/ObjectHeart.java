package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gp) {

        super(gp);
        name = "heart";
        image = setup("/res/items/fullHeart");
        image2 = setup("/res/items/halfHeart");
        image3 = setup("/res/items/emptyHeart");
    }
}
