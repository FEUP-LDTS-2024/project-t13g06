package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Element;
import com.t13g06.project.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private int ballCountdown; // Countdown in seconds
    private long lastUpdateTime; // Tracks the last time countdown was updated

    public GameViewer(Arena arena) {
        super(arena);
        this.ballCountdown = 5; // Start countdown at 5 seconds
        this.lastUpdateTime = System.currentTimeMillis(); // Initialize to current time
    }

    @Override
    public void drawElements(GUI gui) {
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
        gui.drawText(
                new Position(4, 2),
                "Lives: " + "?".repeat(getModel().getPlayer_1().getLives()),
                "#FFD700" // Gold color
        );

        // Update countdown based on elapsed time
        updateCountdown();
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    /**
     * Updates the countdown timer based on elapsed time.
     */
    private void updateCountdown() {
        long currentTime = System.currentTimeMillis(); // Get current time
        if (currentTime - lastUpdateTime >= 1000) { // Check if 1 second has passed
            ballCountdown--; // Decrement countdown
            lastUpdateTime = currentTime; // Reset last update time
        }

        if (ballCountdown < 1) {
            ballCountdown = 5; // Reset countdown to 5 seconds
        }
    }
}
