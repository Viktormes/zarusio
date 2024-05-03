package main;

import java.awt.*;

public class FloatingText {
    public String text;
    public int x;
    public int y;
    public long startTime;
    public Color color;

    public FloatingText(String text, int x, int y, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.startTime = System.currentTimeMillis();
        this.color = color;
    }

    public void draw(Graphics2D g2, Font font) {
        long timePassed = System.currentTimeMillis() - this.startTime;
        if (timePassed < 1200) {
            g2.setFont(font);
            g2.setColor(this.color);
            g2.drawString(this.text, this.x, this.y - (int)(timePassed / 60.0));
        }
    }
}

