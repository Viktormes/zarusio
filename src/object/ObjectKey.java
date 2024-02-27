package object;

import javax.imageio.ImageIO;

public class ObjectKey extends SuperObject{

    public ObjectKey() {

        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/items/key.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
