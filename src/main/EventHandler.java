package main;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;

    int previousX, previousY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
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

                if (hit(28, 21, "any")) {
                    damagePit(28, 21, gp.dialogState);
                }
                if (hit(23, 8, "up")) {
                    healingPool(23, 8, gp.dialogState);
                }
            }
        }

        public boolean hit (int col, int row, String requiredDirection){

            boolean hit = false;

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
            eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

            if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
                if (gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
                    hit = true;

                    previousX = gp.player.worldX;
                    previousY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
            eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

            return hit;
        }
        public void damagePit (int col, int row, int gameState){

            gp.gameState = gameState;

            gp.ui.currentDialog = "OUCH! You stepped on a trap!";
            gp.playSound(4);

            gp.player.currentHealth -= 1;

            canTouchEvent = false;
        }

        public void healingPool (int col, int row, int gameState){

            if (gp.keyH.enterPressed) {
                if (gp.player.currentHealth == gp.player.maxHealth) {
                    gp.gameState = gameState;
                    gp.ui.currentDialog = "You are already at full health.";
                } else if (gp.player.currentHealth < gp.player.maxHealth) {
                    gp.playSound(6);
                    gp.gameState = gameState;
                    gp.ui.currentDialog = "You healed in the pool. \nHealth restored.";
                    gp.player.currentHealth = gp.player.maxHealth;
                }
                gp.assetManager.setEnemy();
            }

        }
    }
