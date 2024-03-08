package main;

import entity.Entity;
import object.ObjectHeart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font, congratsFont;
    BufferedImage heartFull, heartHalf, heartEmpty;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    int fps;

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);

        Entity heart = new ObjectHeart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;

    }

    public void drawMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.WHITE);

        fps = gp.FPS;
        g2.drawString("FPS: " + fps, gp.screenWidth - 150, 90);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerHealth();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogState) {
            drawPlayerHealth();
            drawDialogScreen();
        }

    }

    private void drawPlayerHealth() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxHealth/2) {
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while(i < gp.player.currentHealth) {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if(i < gp.player.currentHealth) {
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 120F));
            String text = "Adventures of Zarusio";
            int x = getXForCenteredString(text);
            int y = gp.tileSize * 2;

            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);


            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.mainTitleScreenImage, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 40F));
            text = "NEW GAME";
            x = getXForCenteredString(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);

            }

            text = "LOAD GAME";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);

            }

            text = "QUIT";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);

            }

        }
        else if (titleScreenState == 1){

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 40F));

            String text = "Select your class!";
            int x = getXForCenteredString(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Mage";
            x = getXForCenteredString(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Druid";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Cancel";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }
    }

    public void drawDialogScreen() {

        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 40));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialog.split("\n")){
            g2.drawString(line, x, y);
            y += 40;

        }





    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color cBlack = new Color(0,0,0,190);
        Color cWhite = new Color(255,255,255);
        g2.setColor(cBlack);
        g2.fillRoundRect(x, y, width, height, 30, 30);
        g2.setColor(cWhite);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76));
        String text = "PAUSED";
        int x = getXForCenteredString(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

        }

        public int getXForCenteredString(String text) {
            int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            return gp.screenWidth/ 2 - length / 2;
        }
    }


