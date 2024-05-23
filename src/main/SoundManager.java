package main;

import javax.sound.sampled.Clip;
import java.util.LinkedList;
import java.util.Queue;

public class SoundManager {
    private static final int MAX_SOUNDS = 10;
    private final Queue<Clip> sounds;

    public SoundManager() {
        sounds = new LinkedList<>();
    }

    public void playSound(Clip clip) {
        try {
            if (sounds.size() >= MAX_SOUNDS) {
                Clip oldestSound = sounds.poll();
                if (oldestSound != null) {
                    oldestSound.stop();
                    oldestSound.close();
                }
            }
            clip.start();
            sounds.add(clip);
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
}