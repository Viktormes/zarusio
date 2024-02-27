package main;

import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;
import object.ObjectPowerSocks;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.itemObject[0] = new ObjectKey();
        gp.itemObject[0].worldX = 23 * gp.tileSize;
        gp.itemObject[0].worldY = 7 * gp.tileSize;

        gp.itemObject[1] = new ObjectKey();
        gp.itemObject[1].worldX = 23 * gp.tileSize;
        gp.itemObject[1].worldY = 40 * gp.tileSize;

        gp.itemObject[2] = new ObjectChest();
        gp.itemObject[2].worldX = 7 * gp.tileSize;
        gp.itemObject[2].worldY = 11 * gp.tileSize;

        gp.itemObject[3] = new ObjectDoor();
        gp.itemObject[3].worldX = 10 * gp.tileSize;
        gp.itemObject[3].worldY = 12 * gp.tileSize;

        gp.itemObject[4] = new ObjectPowerSocks();
        gp.itemObject[4].worldX = 24 * gp.tileSize;
        gp.itemObject[4].worldY = 44 * gp.tileSize;

    }
}
