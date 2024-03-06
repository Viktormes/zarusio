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
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/map/worldmap");

    }

    public void getTileImage() {

        setup(0, "grass2", false);
        setup(1, "otherTile", false);
        setup(2, "reallybloody", false);
        setup(3, "stonewithsomegrass", false);
        setup(4, "coolstone", false);
        setup(5, "grass2", false);
        setup(6, "greentree", true);
        setup(7, "water", true);

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

    public void loadMap(String filePath) {

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

                   mapTileNum[col][row] = num;
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
                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.drawImage(tile[tileNum].image, screenX, screenY ,null);
            }
        }

    }

}