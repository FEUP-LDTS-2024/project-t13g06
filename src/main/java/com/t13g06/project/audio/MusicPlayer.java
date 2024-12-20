package com.t13g06.project.audio;

import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;

    // Constructor to initialize the music player
    public MusicPlayer() {
        this.clip = null;
    }

    // Plays background music in a continuous loop
    public void playBackgroundMusic() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/audio/backgroundMusic.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Stops the currently playing background music
    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
