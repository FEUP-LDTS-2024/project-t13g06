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

    private final long gameDuration; // Duration of the game in milliseconds

    public EndViewer(End end, long gameDuration) {
        super(end);
        this.gameDuration = gameDuration; // Pass game duration from EndState
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        String filePath = "images/EndMenuBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;  // Target width in text cells
            int scaledHeight = 21; // Target height in text cells
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }

        // Display the game duration
        long elapsedTimeInSeconds = gameDuration / 1000;
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;
        String timeFormatted = String.format("Time Survived: %02d:%02d", minutes, seconds);

        gui.drawText(new Position(12, 8), timeFormatted, "#FFFFFF"); // Slightly moved up

        if (!getModel().isSubmitted()) {
            // Show the input field for the name
            gui.drawText(new Position(25, 13), getModel().getPlayerName(), "#FFFFFF");

            gui.drawText(new Position(8, 13), getModel().getEntry(0), getModel().isSelected(0) ? "#FFD700" : "#FFFFFF");
            gui.drawText(new Position(8, 14), getModel().getEntry(1), getModel().isSelected(1) ? "#FFD700" : "#FFFFFF");
        } else {
            // Display the submitted name and score
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String currentDate = dateFormat.format(new Date());
            String savedEntry = String.format("%-6s %02d:%02d %s", getModel().getPlayerName(), minutes, seconds, currentDate);
            gui.drawText(new Position(8, 11), "Score Saved!", "#00FF00"); // Adjusted to fit better
            gui.drawText(new Position(8, 12), savedEntry, "#FFFFFF");
        }


        gui.drawText(
                new Position(8, 15),
                getModel().getEntry(2),
                getModel().isSelected(2) ? "#FFD700" : "#FFFFFF"
        );
    }
}
