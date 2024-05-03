package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class ObjectHeart extends Entity {

    GamePanel gp;

    public ObjectHeart(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typePickUp;
        name = "heart";
        value = 2;
        front1 = setup("/res/items/fullHeart", gp.tileSize, gp.tileSize);
        image = setup("/res/items/fullHeart", gp.tileSize, gp.tileSize);
        image2 = setup("/res/items/halfHeart", gp.tileSize, gp.tileSize);
        image3 = setup("/res/items/emptyHeart", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {

        gp.ui.addFloatingText(String.valueOf(value), gp.player.screenX + 40, gp.player.screenY + 20, Color.GREEN);
        entity.currentHealth += value;

    }
}
