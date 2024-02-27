package object;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{

        public ObjectDoor() {

            name = "door";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/items/door.png"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            collision = true;
        }
}
