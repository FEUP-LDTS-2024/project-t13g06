package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EndViewer extends Viewer<End> {

    private long gameDuration;

    // Constructor initializes the EndViewer with the model and game duration.
    public EndViewer(End end, long gameDuration) {
        super(end);
        this.gameDuration = getModel().getGameTime();
    }

    // Draws elements of the end screen, including the background, game duration, and menu options.
    @Override
    public void drawElements(GUI gui) throws IOException {
        String filePath = "images/EndMenuBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;
            int scaledHeight = 21;
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }

        // Format and display game duration
        long elapsedTimeInSeconds = gameDuration;
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;
        String timeFormatted = String.format("Time Survived: %02d:%02d", minutes, seconds);
        gui.drawText(new Position(26, 8), timeFormatted, "#FFFFFF");

        if (!getModel().isSubmitted()) {
            // Display the player's name input and menu options
            gui.drawText(new Position(38, 13), getModel().getPlayerName(), "#FFFFFF");
            gui.drawText(new Position(22, 13), getModel().getEntry(0), getModel().isSelected(0) ? "#FFD700" : "#FFFFFF");
            gui.drawText(new Position(22, 14), getModel().getEntry(1), getModel().isSelected(1) ? "#FFD700" : "#FFFFFF");
        } else {
            // Show the saved name, score, and submission confirmation
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String currentDate = dateFormat.format(new Date());
            String savedEntry = String.format("%-6s %02d:%02d %s", getModel().getPlayerName(), minutes, seconds, currentDate);
            gui.drawText(new Position(22, 11), "Score Saved!", "#00FF00");
            gui.drawText(new Position(22, 12), savedEntry, "#FFFFFF");
        }

        // Always display the "Exit" option
        gui.drawText(
                new Position(22, 15),
                getModel().getEntry(2),
                getModel().isSelected(2) ? "#FFD700" : "#FFFFFF"
        );
    }
}
