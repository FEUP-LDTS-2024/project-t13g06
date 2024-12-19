package com.t13g06.project;

import com.t13g06.project.audio.MusicPlayer;
import com.t13g06.project.gui.LanternaGUI;

import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.states.MenuState;
import com.t13g06.project.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    private final LanternaGUI gui;
    private State state;
    private final MusicPlayer musicPlayer; // Add MusicPlayer instance


    public Game() throws FontFormatException, IOException, URISyntaxException {
        // Initialize the GUI
        this.gui = new LanternaGUI(53, 25);

        // Initialize the MusicPlayer
        this.musicPlayer = new MusicPlayer();

        // Start playing the background music
        musicPlayer.playBackgroundMusic();

        // Initialize the game state (menu or any other state)
        this.state = new MenuState(new Menu());
    }



    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException {
        int FPS = 10;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            // Step the game state and process all current actions
            state.step(this, gui, startTime);


            // Calculate elapsed time and sleep for the remaining frame duration
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        gui.close();
    }

    public String getLastCharacterPressed() {
        return gui.getLastCharacterPressed();
    }
}
