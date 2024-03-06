package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,70,70);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool utilityTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(gp.player.screenX > gp.player.worldX) {
                    screenX = worldX;
                }
                if(gp.player.screenY > gp.player.worldY) {
                    screenY = worldY;
                }
                int rightOffSet = gp.screenWidth - gp.player.screenX;
                if(rightOffSet > gp.worldWidth - gp.player.worldX) {
                    screenX = gp.screenWidth - (gp.worldWidth - worldX);
                }
                int bottomOffSet = gp.screenHeight - gp.player.screenY;
                if(bottomOffSet > gp.worldHeight - gp.player.worldY) {
                    screenY = gp.screenHeight - (gp.worldHeight - worldY);
                }

                else if(gp.player.screenX > gp.player.worldX ||
                        gp.player.screenY > gp.player.worldY ||
                        rightOffSet > gp.worldWidth - gp.player.worldX ||
                        bottomOffSet > gp.worldHeight - gp.player.worldY) {
                    g2.drawImage(image, screenX, screenY ,null);
                }

                g2.drawImage(image, screenX, screenY ,null);
            }
        }


    }
}
