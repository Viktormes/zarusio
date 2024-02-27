package object;

import javax.imageio.ImageIO;

public class ObjectPowerSocks extends SuperObject{

public ObjectPowerSocks() {

        name = "superSocks";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/powersocks.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
