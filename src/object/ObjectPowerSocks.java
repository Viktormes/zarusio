package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectPowerSocks extends SuperObject{

    GamePanel gp;

public ObjectPowerSocks(GamePanel gp) {

        name = "superSocks";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/powersocks.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
