package main;

import enemy.Ghost;
import enemy.PlantMonster;
import entity.Player;
import entity.npcDevil;
import entity.npcMerchant;
import object.*;

public class AssetManager {

    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum = gp.currentMap;
        int i = 0;
        gp.itemObject[mapNum][i] = new ObjectPowerSocks(gp);
        gp.itemObject[mapNum][i].worldX = 85 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 88 * gp.tileSize;
        i++;
        gp.itemObject[mapNum][i] = new ObjectRedPear(gp);
        gp.itemObject[mapNum][i].worldX = 49 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 7 * gp.tileSize;
        i++;
        gp.itemObject[mapNum][i] = new ObjectRedPear(gp);
        gp.itemObject[mapNum][i].worldX = 51 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 7 * gp.tileSize;
        i++;
        gp.itemObject[mapNum][i] = new ObjectRedPear(gp);
        gp.itemObject[mapNum][i].worldX = 49 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.itemObject[mapNum][i] = new ObjectRedPear(gp);
        gp.itemObject[mapNum][i].worldX = 52 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        gp.itemObject[mapNum][i] = new ObjectHeart(gp);
        gp.itemObject[mapNum][i].worldX = 20 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 38 * gp.tileSize;

        mapNum = 1;
        i = 0;
        gp.itemObject[mapNum][i] = new ObjectAxe(gp);
        gp.itemObject[mapNum][i].worldX = 47 * gp.tileSize;
        gp.itemObject[mapNum][i].worldY = 33 * gp.tileSize;
    }

    public void setNPC() {

        int mapNum = gp.currentMap;
        int i = 0;

        gp.npc[mapNum][i] = new npcDevil(gp);
        gp.npc[mapNum][i].worldX = 23 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new npcMerchant(gp);
        gp.npc[mapNum][i].worldX = 15 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 17 * gp.tileSize;
        i++;


    }

    public void setEnemy() {

        int mapNum = 0;
        int i = 0;

        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 24 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 56 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 58 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 27 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 58 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 29 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 55 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 38 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 40 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 39 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 10 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 23 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 39 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 71 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 43 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new Ghost(gp);
        gp.enemy[mapNum][i].worldX = 75 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 46 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new Ghost(gp);
        gp.enemy[mapNum][i].worldX = 74 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 43 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new Ghost(gp);
        gp.enemy[mapNum][i].worldX = 72 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 43 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 55 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 74 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 68 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 75 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 53 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 83 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 85 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 80 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 84 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 81 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 84 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 80 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 84 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 83 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 87 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 86 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 88 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 86 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 86 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 89 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new PlantMonster(gp);
        gp.enemy[mapNum][i].worldX = 86 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 88 * gp.tileSize;
        i++;

        mapNum = 1;
        i = 0;
        gp.enemy[mapNum][i] = new Ghost(gp);
        gp.enemy[mapNum][i].worldX = 47 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 33 * gp.tileSize;
        i++;
        gp.enemy[mapNum][i] = new Ghost(gp);
        gp.enemy[mapNum][i].worldX = 45 * gp.tileSize;
        gp.enemy[mapNum][i].worldY = 35 * gp.tileSize;

    }
}
