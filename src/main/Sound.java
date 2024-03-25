package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;

    URL[] soundURL = new URL[20];
    FloatControl floatControl;
    int volumeScale = 3;
    float volume;
    private SoundManager soundManager;

    public Sound() {

        soundManager = new SoundManager();

        soundURL[0] = getClass().getResource("/res/sound/hit.wav");
        soundURL[1] = getClass().getResource("/res/sound/titleSong.mid");
        soundURL[2] = getClass().getResource("/res/sound/pickUpSound.mid");
        soundURL[3] = getClass().getResource("/res/sound/hitMonster.wav");
        soundURL[4] = getClass().getResource("/res/sound/receiveDamage.wav");
        soundURL[5] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[6] = getClass().getResource("/res/sound/healingSound.wav");
        soundURL[7] = getClass().getResource("/res/sound/whipSwoosh.wav");
        soundURL[8] = getClass().getResource("/res/sound/axeSwoosh.wav");
        soundURL[9] = getClass().getResource("/res/sound/coin.wav");
        soundURL[10] = getClass().getResource("/res/sound/cursor.wav");
        soundURL[11] = getClass().getResource("/res/sound/gameOver.wav");
        soundURL[12] = getClass().getResource("/res/sound/fall.wav");
        soundURL[13] = getClass().getResource("/res/sound/teleport.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            clip = AudioSystem.getClip();
            clip.open(ais);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void play() {
            soundManager.playSound(clip);

    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }

    public void checkVolume() {

        switch (volumeScale) {
            case 0:
                volume = -80.0f;
                break;
            case 1:
                volume = -20.0f;
                break;
            case 2:
                volume = -12.0f;
                break;
            case 3:
                volume = -5.0f;
                break;
            case 4:
                volume = 0.0f;
                break;
            case 5:
                volume = 6.0f;
                break;
        }
        floatControl.setValue(volume);

    }
}
