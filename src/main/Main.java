package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {

       // System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.d3d", "false");
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Zarusio");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn){
            window.setUndecorated(true);
        }

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
