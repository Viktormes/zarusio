package main;

import enemy.PlantMonster;
import entity.NPC_Devil;
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

        gp.itemObject[4] = new ObjectPowerSocks(gp);
        gp.itemObject[4].worldX = 24 * gp.tileSize;
        gp.itemObject[4].worldY = 40 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Devil(gp);
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 20 * gp.tileSize;

    }

    public void setEnemy() {


        int i = 0;

        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 10 * gp.tileSize;
        gp.enemy[i].worldY = 46 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 23 * gp.tileSize;
        gp.enemy[i].worldY = 38 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 23 * gp.tileSize;
        gp.enemy[i].worldY = 40 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 43 * gp.tileSize;
        gp.enemy[i].worldY = 10 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 23 * gp.tileSize;
        gp.enemy[i].worldY = 39 * gp.tileSize;


    }
}
