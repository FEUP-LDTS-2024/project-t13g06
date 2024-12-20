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
    private int ballCountdown = 5;
    private long lastUpdateTime;
    private int iterations = 1;
    private long gameStartTime;

    public GameViewer(Arena arena) {
        super(arena);
        this.lastUpdateTime = System.currentTimeMillis();
        this.gameStartTime = System.currentTimeMillis();
    }

    // Draws all elements of the arena, including the background, player, walls, and other game objects.
    @Override
    public void drawElements(GUI gui) throws IOException {
        String filePath = "images/GameBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;
            int scaledHeight = 21;
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }

        // Draw arena elements
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getPowerUp(), new PowerUpsViewer());
        drawElement(gui, getModel().getPlayer_1(), new PlayerViewer());

        // Draw all balls
        for (Ball ball : getModel().getBalls()) {
            drawElement(gui, ball, new BallViewer());
        }

        // Display countdown for new ball spawn
        gui.drawText(
                new Position(38, 2),
                "New ball in " + ballCountdown,
                "#FFFFFF"
        );

        // Display player lives
        gui.drawText(new Position(4, 2), "Lives: ", "#FFFFFF");
        gui.drawText(new Position(11, 2), "?".repeat(getModel().getPlayer_1().getLives()), "#FF0000");

        // Update countdown timers
        updateCountdown(gui);
    }

    // Draws a list of elements using the provided viewer.
    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements) {
            drawElement(gui, element, viewer);
        }
    }

    // Draws a single element using the provided viewer.
    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    // Updates countdown timers for ball spawning and game time display.
    private void updateCountdown(GUI gui) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= 1000) {
            ballCountdown--;
            lastUpdateTime = currentTime;
        }

        if (ballCountdown < 0) {
            ballCountdown = 5 + iterations;
            iterations++;
        }

        // Calculate and format elapsed game time
        long elapsedTimeInSeconds = (currentTime - gameStartTime) / 1000;
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        // Display the game time
        gui.drawText(
                new Position(23, 2),
                timeFormatted,
                "#FFFFFF"
        );
    }
}
