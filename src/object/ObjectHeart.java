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
        front1 = setup("/res/items/fullHeart",gp.tileSize,gp.tileSize);
        image = setup("/res/items/fullHeart",gp.tileSize,gp.tileSize);
        image2 = setup("/res/items/halfHeart",gp.tileSize,gp.tileSize);
        image3 = setup("/res/items/emptyHeart",gp.tileSize,gp.tileSize);
    }

    public void use(Entity entity) {

        gp.ui.addMessage("Life +" + value);
        entity.currentHealth += value;
        if(entity.currentHealth > entity.maxHealth){
            entity.currentHealth = entity.maxHealth;
        }

    }
}
