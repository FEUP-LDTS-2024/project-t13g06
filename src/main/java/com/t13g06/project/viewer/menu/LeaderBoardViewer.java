package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.viewer.Viewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LeaderBoardViewer extends Viewer<LeaderBoard> {

    // Constructor initializes the LeaderBoardViewer with the model.
    public LeaderBoardViewer(LeaderBoard leaderBoard) {
        super(leaderBoard);
    }

    // Draws the leaderboard menu, including background and leaderboard entries.
    @Override
    public void drawElements(GUI gui) throws IOException {
        drawBackground(gui);
        drawMenuEntries(gui);
        drawLeaderboard(gui);
    }

    // Draws the background image for the leaderboard screen.
    private void drawBackground(GUI gui) throws IOException {
        String filePath = "images/LeaderBoardMenuBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;
            int scaledHeight = 21;
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }
    }

    // Draws selectable menu entries from the model.
    private void drawMenuEntries(GUI gui) {
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(45, 19 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF" // Highlight selected entry
            );
        }
    }

    // Reads, sorts, and displays the top 10 leaderboard entries from a log file.
    private void drawLeaderboard(GUI gui) {
        String logFilePath = "leaderboard/gameLog.txt";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(logFilePath)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + logFilePath);
            }

            // Read all lines into a list
            List<String> leaderboardEntries = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    leaderboardEntries.add(line);
                }
            }

            // Sort the entries in descending order based on the score (assumed to be in the second part of the line)
            leaderboardEntries.sort((entry1, entry2) -> {
                String[] parts1 = entry1.split(" ", 2);
                String[] parts2 = entry2.split(" ", 2);
                try {
                    // Extract and compare the scores (assuming the score is the second part of the line)
                    int score1 = Integer.parseInt(parts1[1].trim());
                    int score2 = Integer.parseInt(parts2[1].trim());
                    return Integer.compare(score2, score1); // Sort in descending order
                } catch (NumberFormatException e) {
                    return 0; // If there is a parsing error, do not change the order
                }
            });

            // Display the top 10 entries
            for (int i = 0; i < Math.min(10, leaderboardEntries.size()); i++) {
                String line = leaderboardEntries.get(i);
                String[] parts = line.split(" ", 2);
                String name = parts[0];
                String rest = parts.length > 1 ? parts[1] : "";

                String formattedName = String.format("%-6s", name);

                gui.drawText(
                        new Position(28, 5 + i),
                        formattedName + " " + rest,
                        "#FFFFFF" // White text for leaderboard entries
                );
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log file reading errors
        }
    }

}
