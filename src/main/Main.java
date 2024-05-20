package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {

        System.setProperty("sun.java2d.noddraw", "True");
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(false);
        window.setResizable(true);
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
