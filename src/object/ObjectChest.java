package object;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject{

    public ObjectChest() {

        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/chest.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
