package main;

import javax.sound.sampled.Clip;
import java.util.LinkedList;
import java.util.Queue;

public class SoundManager {
    private static final int MAX_SOUNDS = 10;
    private Queue<Clip> sounds;

    public SoundManager() {
        sounds = new LinkedList<>();
    }

    public void playSound(Clip clip) {
        if (sounds.size() >= MAX_SOUNDS) {
            Clip oldestSound = sounds.poll();
            oldestSound.stop();
        }
        clip.start();
        sounds.add(clip);
    }
}