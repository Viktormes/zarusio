package main;

import enemy.Ghost;
import enemy.PlantMonster;
import entity.NPC_Devil;
import object.*;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int i = 0;
        gp.itemObject[i] = new ObjectPowerSocks(gp);
        gp.itemObject[i].worldX = 24 * gp.tileSize;
        gp.itemObject[i].worldY = 40 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectRedPear(gp);
        gp.itemObject[i].worldX = 49 * gp.tileSize;
        gp.itemObject[i].worldY = 7 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectRedPear(gp);
        gp.itemObject[i].worldX = 51 * gp.tileSize;
        gp.itemObject[i].worldY = 7 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectRedPear(gp);
        gp.itemObject[i].worldX = 49 * gp.tileSize;
        gp.itemObject[i].worldY = 8 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectRedPear(gp);
        gp.itemObject[i].worldX = 52 * gp.tileSize;
        gp.itemObject[i].worldY = 8 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectAxe(gp);
        gp.itemObject[i].worldX = 23 * gp.tileSize;
        gp.itemObject[i].worldY = 36 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectPowerRobe(gp);
        gp.itemObject[i].worldX = 23 * gp.tileSize;
        gp.itemObject[i].worldY = 37 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectCoin(gp);
        gp.itemObject[i].worldX = 20 * gp.tileSize;
        gp.itemObject[i].worldY = 37 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectPlatinumCoin(gp);
        gp.itemObject[i].worldX = 21 * gp.tileSize;
        gp.itemObject[i].worldY = 37 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectMana(gp);
        gp.itemObject[i].worldX = 21 * gp.tileSize;
        gp.itemObject[i].worldY = 38 * gp.tileSize;
        i++;
        gp.itemObject[i] = new ObjectHeart(gp);
        gp.itemObject[i].worldX = 20 * gp.tileSize;
        gp.itemObject[i].worldY = 38 * gp.tileSize;
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
        gp.enemy[i].worldX = 10 * gp.tileSize;
        gp.enemy[i].worldY = 44 * gp.tileSize;
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
        gp.enemy[i].worldX = 39 * gp.tileSize;
        gp.enemy[i].worldY = 10 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 23 * gp.tileSize;
        gp.enemy[i].worldY = 39 * gp.tileSize;
        i++;
        gp.enemy[i] = new PlantMonster(gp);
        gp.enemy[i].worldX = 71 * gp.tileSize;
        gp.enemy[i].worldY = 43 * gp.tileSize;
        i++;
        gp.enemy[i] = new Ghost(gp);
        gp.enemy[i].worldX = 75 * gp.tileSize;
        gp.enemy[i].worldY = 46 * gp.tileSize;
        i++;
        gp.enemy[i] = new Ghost(gp);
        gp.enemy[i].worldX = 74 * gp.tileSize;
        gp.enemy[i].worldY = 43 * gp.tileSize;
        i++;
        gp.enemy[i] = new Ghost(gp);
        gp.enemy[i].worldX = 72 * gp.tileSize;
        gp.enemy[i].worldY = 43 * gp.tileSize;


    }
}
