package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{

    GamePanel gp;
        public ObjectDoor(GamePanel gp) {

            name = "door";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/items/door.png"));
                image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
            collision = true;
        }
}
