package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    boolean drawPath = true;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        loadMap("/map/worldMap",0);
        loadMap("/map/holeMap",1);

    }

    public void getTileImage() {

        setup(0, "grass2", false);
        setup(1, "otherTile", false);
        setup(2, "stoneTileOne", false);
        setup(3, "stoneTileTwo", false);
        setup(4, "coolstone", true);
        setup(5, "stoneTileThree", false);
        setup(6, "greentree", true);
        setup(7, "water", true);
        setup(8,"sandstone1",false);
        setup(9,"stoneTileFour",false);
        setup(10,"hole",false);
        setup(11,"darkness",true);
        setup(12,"dirtTile",false);
        setup(13,"portal", false);
        setup(14,"coolstone",true);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath, int map) {

       try {
           InputStream is = getClass().getResourceAsStream(filePath);

           BufferedReader br = new BufferedReader(new InputStreamReader(is));

           int col = 0;
           int row = 0;

           while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

               String line = br.readLine();

               while (col < gp.maxWorldCol) {

                   String[] numbers = line.split(" ");

                   int num = Integer.parseInt(numbers[col]);

                   mapTileNum[map][col][row] = num;
                   col++;
               }
               if (col == gp.maxWorldCol) {
                   col = 0;
                   row++;
               }
           }
           br.close();

       }catch (Exception e) {
        e.printStackTrace();
       }

    }
    public void draw(Graphics2D g2) {
        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
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
                    g2.drawImage(tile[tileNum].image, screenX, screenY ,null);
                }

                g2.drawImage(tile[tileNum].image, screenX, screenY ,null);
            }
        }


        if(drawPath) {
            g2.setColor(new Color(255,0,0,70));

            for(int i = 0; i < gp.pathFinder.pathList.size(); i++){

                int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX,screenY,gp.tileSize,gp.tileSize);
            }
        }
    }

}