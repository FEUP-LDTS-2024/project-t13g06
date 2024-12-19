package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Element;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private int ballCountdown = 5; // Countdown in seconds for the ball spawn
    private long lastUpdateTime; // Tracks the last time countdown was updated
    private int iterations = 1;
    private long gameStartTime; // Tracks the start time of the game

    public GameViewer(Arena arena) {
        super(arena);
        this.lastUpdateTime = System.currentTimeMillis(); // Initialize to current time
        this.gameStartTime = System.currentTimeMillis(); // Initialize game start time
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        String filePath = "images/GameBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;  // Target width in text cells
            int scaledHeight = 21   ; // Target height in text cells
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }

        // Draw all elements
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getPowerUp(), new PowerUpsViewer());
        drawElement(gui, getModel().getPlayer_1(), new Player_1_Viewer());

        // Iterate through all balls and draw each one
        for (Ball ball : getModel().getBalls()) {
            drawElement(gui, ball, new BallViewer());
        }

        // Draw the "New ball in X" text
        gui.drawText(
                new Position(38, 2), // Top-left corner
                "New ball in " + ballCountdown,
                "#FFFFFF" // White color
        );

        // Draw player lives
        gui.drawText(new Position(4, 2), "Lives: ", "#FFFFFF");
        gui.drawText(new Position(11, 2), "?".repeat(getModel().getPlayer_1().getLives()), "#FF0000");

        // Update countdown based on elapsed time
        updateCountdown(gui);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements) {
            drawElement(gui, element, viewer);
        }
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    /**
     * Updates the countdown timers (ball spawn and game time).
     */
    private void updateCountdown(GUI gui) {
        long currentTime = System.currentTimeMillis(); // Get current time
        if (currentTime - lastUpdateTime >= 1000) { // Check if 1 second has passed
            ballCountdown--; // Decrement ball countdown
            lastUpdateTime = currentTime; // Reset last update time
        }

        if (ballCountdown < 0) {
            ballCountdown = 5 + iterations;
            iterations++;
        }

        // Calculate and display the game time in mm:ss format
        long elapsedTimeInSeconds = (currentTime - gameStartTime) / 1000;
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        // Draw the formatted time on the screen
        gui.drawText(
                new Position(23, 2), // Position where the timer will be shown
                timeFormatted,
                "#FFFFFF" // White color
        );
    }
}
