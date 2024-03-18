package main;

import entity.Entity;
import object.ObjectHeart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage heartFull, heartHalf, heartEmpty, mana, manaBlank;
    ArrayList<FloatingText> floatingTexts = new ArrayList<>();
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;


    int fps;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/fonts/Avenixel-Regular.ttf"));
            customFont = customFont.deriveFont(40F);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        Entity heart = new ObjectHeart(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
        Entity manaCrystal = new object.ObjectMana(gp);
        mana = manaCrystal.image;
        manaBlank = manaCrystal.image2;

    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void addFloatingText(String text, int x, int y, Color color) {
        if (text.equals("0")) {
            return;
        }
        floatingTexts.add(new FloatingText(text, x, y, color));
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font.deriveFont(40F));
        g2.setColor(Color.WHITE);

        fps = gp.FPS;
        g2.drawString("FPS: " + fps, gp.screenWidth - 150, 90);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerHealth();
            drawMessage();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogState) {
            drawPlayerHealth();
            drawDialogScreen();
        }
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        for (FloatingText floatingText : floatingTexts) {
            floatingText.draw(g2, font);
        }

    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0,0,0,200));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(font.deriveFont(Font.BOLD,120f));

        text = "YOU DIED";
        g2.setColor(Color.black);
        x = getXForCenteredString(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y );
        g2.setColor(new Color(190, 8, 8));
        g2.drawString(text, x - 4, y - 4);

        g2.setFont(font.deriveFont(60f));
        g2.setColor(Color.WHITE);
        text = "Retry";
        x = getXForCenteredString(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenteredString(text);
        y += 60;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }


    }

    public void drawCharacterScreen() {

        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 4;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(font);

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 70;

        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP", textX, textY);
        textY += lineHeight;
        g2.drawString("Health", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP to level up", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold", textX, textY);
        textY += lineHeight + 25;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Armor", textX, textY);
        textY += lineHeight;

        int tailX = (frameX + frameWidth) - 40;

        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.experience);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.currentHealth + "/" + gp.player.maxHealth);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.currentMana + "/" + gp.player.maxMana);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExperience - gp.player.experience);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.gold);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.front1, tailX - gp.tileSize, textY - 34, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentRobe.front1, tailX - gp.tileSize, textY - 34, null);
        textY += gp.tileSize;


    }

    public void drawInventory() {

        int frameX = gp.tileSize * 12;
        int frameY = gp.tileSize;
        double frameWidth = gp.tileSize * 5.5;
        double frameHeight = gp.tileSize * 4.5;
        drawSubWindow(frameX, frameY, (int) frameWidth, (int) frameHeight);

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        for (int i = 0; i < gp.player.inventory.size(); i++) {

            if (gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentRobe) {
                g2.setColor(new Color(240, 190, 220));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }


            g2.drawImage(gp.player.inventory.get(i).front1, slotX, slotY, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        int cursorX = slotXStart + (slotCol * slotSize);
        int cursorY = slotYStart + (slotRow * slotSize);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


        int dFrameX = frameX;
        int dFrameY = frameY + (int) frameHeight;
        int dFrameWidth = (int) frameWidth;
        int dFrameHeight = gp.tileSize * 3;


        int textX = dFrameX + 20;
        int textY = dFrameY + 40;
        g2.setFont(font.deriveFont(40f));


        int itemIndex = findItemIndex();

        if (itemIndex < gp.player.inventory.size()) {

            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for (String line : gp.player.inventory.get(itemIndex).itemDescription.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public void drawOptionsScreen() {

        g2.setColor(Color.WHITE);
        g2.setFont(font.deriveFont(50f));

        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                optionsFullScreenNotification(frameX, frameY);
                break;
            case 2:
                optionsControls(frameX, frameY);
                break;
            case 3:
                optionsEndgame(frameX, frameY);
                break;

        }

        gp.keyH.enterPressed = false;

    }

    public void optionsEndgame(int frameX, int frameY) {

            int textX = frameX + gp.tileSize;
            int textY = frameY + gp.tileSize * 3;

            currentDialog = "Are you sure you want to quit \nand return to the title screen?";
            for (String line : currentDialog.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 40;
            }

            String text = "Yes";
            textX = getXForCenteredString(text);
            textY += gp.tileSize * 3;
            g2.drawString(text, textX, textY);
            if (commandNum == 0) {
                g2.drawString(">", textX - gp.tileSize, textY);
                if (gp.keyH.enterPressed) {
                    subState = 0;
                    gp.gameState = gp.titleState;
                    titleScreenState = 0;
                    gp.stopMusic();

                }
            }

            text = "No";
            textX = getXForCenteredString(text);
            textY += gp.tileSize;
            g2.drawString(text, textX, textY);
            if (commandNum == 1) {
                g2.drawString(">", textX - gp.tileSize, textY);
                if (gp.keyH.enterPressed) {
                    subState = 0;
                    commandNum = 4;
                }
            }

    }

    public void optionsTop(int frameX, int frameY) {

        int textX;
        int textY;

        String text = "Options";
        textX = getXForCenteredString(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if (gp.keyH.enterPressed) {
                if (!gp.fullScreenOn) {
                    gp.fullScreenOn = true;
                } else {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }

        }

        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
        }

        textY += gp.tileSize;
        g2.drawString("Sound Effects", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
        }

        textY += gp.tileSize;
        g2.drawString("Controls", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if (gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        textY += gp.tileSize;
        g2.drawString("Exit Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if(gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
            }
        }

        textX = frameX + gp.tileSize * 5;
        textY = frameY + gp.tileSize * 2 + 60;
        g2.drawRect(textX, textY, gp.tileSize / 2, gp.tileSize / 2);

        if (gp.fullScreenOn) {
            g2.fillRect(textX, textY, gp.tileSize / 2, gp.tileSize / 2);
        }

        textY += gp.tileSize;
        g2.drawRect(textX, textY, 200, gp.tileSize / 2);
        int volumeWidth = 40 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize / 2);

        textY += gp.tileSize;
        g2.drawRect(textX, textY, 200, gp.tileSize / 2);
        volumeWidth = 40 * gp.soundEffect.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize / 2);

        gp.config.saveConfig();

    }

    public int findItemIndex() {
        return slotCol + (slotRow * 5);
    }

    public int getXForAlignToRight(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }

    private void drawPlayerHealth() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxHealth / 2) {
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.currentHealth) {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gp.player.currentHealth) {
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize + 20;
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(manaBlank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize + 20;
        i = 0;
        while (i < gp.player.currentMana) {
            g2.drawImage(mana, x, y, null);
            i++;
            x += gp.tileSize;
        }


    }

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(font.deriveFont(40f));

        for (int i = 0; i < message.size(); i++) {

            if (message.get(i) != null) {

                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);

                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(font.deriveFont(80F));
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

            g2.setFont(font.deriveFont(40F));
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

        } else if (titleScreenState == 1) {

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setColor(Color.WHITE);
            g2.setFont(font.deriveFont(40F));

            String text = "Select your class!";
            int x = getXForCenteredString(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Mage";
            x = getXForCenteredString(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Druid";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Cancel";
            x = getXForCenteredString(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }
    }

    public void optionsFullScreenNotification(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialog = "You must restart \nthe game for these changes \nto take effect.";
        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }

    }

    public void optionsControls(int frameX, int frameY) {

        int textX;
        int textY;

        String text = "Controls";
        textX = getXForCenteredString(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, gp.tileSize);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Attack", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Cast", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;


        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Enter", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Space", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize / 2, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }


    }

    public void drawDialogScreen() {

        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(font.deriveFont(40F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;

        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color cBlack = new Color(0, 0, 0, 190);
        Color cWhite = new Color(255, 255, 255);
        g2.setColor(cBlack);
        g2.fillRoundRect(x, y, width, height, 30, 30);
        g2.setColor(cWhite);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

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
        return gp.screenWidth / 2 - length / 2;
    }
}


