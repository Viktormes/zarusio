package main;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;

    int previousX, previousY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

        public void checkEvent () {

            int xDistance = Math.abs(gp.player.worldX - previousX);
            int yDistance = Math.abs(gp.player.worldY - previousY);
            int distance = Math.max(xDistance, yDistance);
            if (distance > gp.tileSize) {
                canTouchEvent = true;
            }

            if (canTouchEvent) {

                if (hit(0,28, 21, "any")) {
                    damagePit( gp.dialogState);
                }
                else if (hit(0,23, 8, "up")) {
                    healingPool( gp.dialogState);
                }
                else if(hit(0, 40, 40, "any")) {
                    teleport(1,17,16);
                }
                else if(hit(1, 17, 19, "any")) {
                    teleport(0,39,40);
                }
            }
        }

        public boolean hit (int map, int col, int row, String requiredDirection){

            boolean hit = false;

            if (map == gp.currentMap) {
                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

                if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                    if (gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
                        hit = true;

                        previousX = gp.player.worldX;
                        previousY = gp.player.worldY;
                    }
                }

                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

            }

            return hit;
        }
        public void damagePit (int gameState){

            gp.gameState = gameState;

            gp.ui.currentDialog = "OUCH! You stepped on a trap!";
            gp.playSound(4);

            gp.player.currentHealth -= 1;

            canTouchEvent = false;
        }

        public void teleport(int map, int col, int row) {

        gp.currentMap = map;
        gp.player.worldX = col * gp.tileSize;
        gp.player.worldY = row * gp.tileSize;
        previousX = gp.player.worldX;
        previousY = gp.player.worldY;
        canTouchEvent = false;

        if(map == 0) {
            gp.playSound(13);
        }
        if(map == 1) {
            gp.playSound(12);
        }

        }
        public void healingPool (int gameState){

            if (gp.keyH.enterPressed) {
                if (gp.player.currentHealth == gp.player.maxHealth && gp.player.currentMana == gp.player.maxMana) {
                    gp.gameState = gameState;
                    gp.ui.currentDialog = "You are already at full health and mana.";
                } else if (gp.player.currentHealth < gp.player.maxHealth || gp.player.currentMana < gp.player.maxMana) {
                    gp.playSound(6);
                    gp.gameState = gameState;
                    gp.ui.currentDialog = "You healed in the pool. \nHealth restored and mana restored.";
                    gp.player.currentHealth = gp.player.maxHealth;
                    gp.player.currentMana = gp.player.maxMana;
                }
                gp.assetManager.setEnemy();
            }

        }
    }
