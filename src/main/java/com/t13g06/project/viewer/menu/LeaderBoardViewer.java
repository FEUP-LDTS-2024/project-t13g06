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

            List<String> leaderboardEntries = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    leaderboardEntries.add(line);
                }
            }

            for (int i = 0; i < leaderboardEntries.size() - 1; i++) {
                for (int j = 0; j < leaderboardEntries.size() - i - 1; j++) {

                    String parts1 = leaderboardEntries.get(j).split(" ", 2)[1].split(" ",2)[0];
                    String parts2 = leaderboardEntries.get(j + 1).split(" ", 2)[1].split(" ",2)[0];

                    try {
                        int time1 = parseTimeToSeconds(parts1);
                        int time2 = parseTimeToSeconds(parts2);

                        if (time1 < time2) {
                            String temp = leaderboardEntries.get(j);
                            leaderboardEntries.set(j, leaderboardEntries.get(j + 1));
                            leaderboardEntries.set(j + 1, temp);
                        }
                    } catch (Exception e) {
                        // If parsing fails, skip this comparison
                    }
                }
            }

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
                        "#FFFFFF"
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int parseTimeToSeconds(String timeString) throws IllegalArgumentException {
        String[] timeParts = timeString.split(":");
        if (timeParts.length != 2) {
            throw new IllegalArgumentException("Invalid time format: " + timeString);
        }

        int minutes = Integer.parseInt(timeParts[0].trim());
        int seconds = Integer.parseInt(timeParts[1].trim());
        return (minutes * 60) + seconds;
    }
}