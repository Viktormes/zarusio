package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

            if(gp.fullScreenOn){
                writer.write("fullScreenOn=true");
            } else {
                writer.write("fullScreenOn=false");
            }
            writer.newLine();

            writer.write(String.valueOf(gp.music.volumeScale));
            writer.newLine();

            writer.write(String.valueOf(gp.soundEffect.volumeScale));
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadConfig() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));

            String s = reader.readLine();

            if(s.equals("fullScreenOn=true")){
                gp.fullScreenOn = true;
            }
            if(s.equals("fullScreenOn=false")){
                gp.fullScreenOn = false;
            }

            s = reader.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            s = reader.readLine();
            gp.soundEffect.volumeScale = Integer.parseInt(s);

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
