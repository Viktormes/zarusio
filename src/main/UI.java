package main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font font, congratsFont;
    public boolean messageOn = false;
    public String message = "";

    int fps;

    public UI(GamePanel gp) {
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);
        congratsFont = new Font("Arial", Font.BOLD, 60);

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

        if (gp.gameState == gp.playState) {
            // do playstate things
        }
        if (gp.gameState == gp.pauseState) {

            drawPauseScreen();
        }

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


