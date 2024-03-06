package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject{

    GamePanel gp;
    public ObjectChest(GamePanel gp) {

        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/chest.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
