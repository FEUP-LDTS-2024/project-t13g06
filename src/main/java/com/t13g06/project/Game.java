package com.t13g06.project;

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

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(53, 25);
        this.state = new MenuState(new Menu());

        //this.state = new GameState(new LoaderArenaBuilder(1).createArena());
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
