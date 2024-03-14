package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;

    URL[] soundURL = new URL[30];

    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }

    public Sound() {

        soundURL[0] = getClass().getResource("/res/sound/hit.wav");
        soundURL[1] = getClass().getResource("/res/sound/titleSong.mid");
        soundURL[2] = getClass().getResource("/res/sound/pickUpSound.mid");
        soundURL[3] = getClass().getResource("/res/sound/hitMonster.wav");
        soundURL[4] = getClass().getResource("/res/sound/receiveDamage.wav");
        soundURL[5] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[6] = getClass().getResource("/res/sound/healingSound.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void play() {

        clip.start();

    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }

}
