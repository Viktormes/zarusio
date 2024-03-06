package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectKey extends SuperObject{

    GamePanel gp;
    public ObjectKey(GamePanel gp) {

        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/key.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
