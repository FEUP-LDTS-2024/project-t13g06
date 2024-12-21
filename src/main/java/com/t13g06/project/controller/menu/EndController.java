package com.t13g06.project.controller.menu;

import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.states.MenuState;
import com.t13g06.project.viewer.menu.EndViewer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class EndController extends Controller<End> {

    private final EndViewer endViewer;
    private final long gameDuration;

    // Constructor: Initializes the controller with the End model, viewer, and game duration.
    public EndController(End end, EndViewer endViewer, long gameDuration) {
        super(end);
        this.endViewer = endViewer;
        this.gameDuration = gameDuration;
    }

    // Handles user actions and updates the game state accordingly.
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (actionSet == null || actionSet.isEmpty()) return;

        for (GUI.ACTION action : actionSet) {
            switch (action) {
                case UP:
                    getModel().previousEntry();
                    break;

                case DOWN:
                    getModel().nextEntry();
                    break;

                case SELECT:
                    if (getModel().isSelectedSubmit()) {
                        getModel().setSubmitted();
                        saveScore(getModel().getPlayerName(), getModel().getGameTime());
                    }

                    if (getModel().isSelectedEnd()) game.setState(new MenuState(new Menu()));

                case TYPE:
                    if (getModel().isSelectedNameField()) {
                        getModel().updatePlayerName(game.getLastCharacterPressed());
                    }
                    break;

                case BACKSPACE:
                    if (getModel().isSelectedNameField()) {
                        getModel().removeLastChar();
                    }
                    break;

                default:
                    break;
            }
        }
    }

    // Saves the player's score to a leaderboard file.
    private void saveScore(String playerName, long gameDuration) {
        long elapsedTimeInSeconds = gameDuration;
        long minutes = elapsedTimeInSeconds / 60;
        long seconds = elapsedTimeInSeconds % 60;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(new Date());

        String formattedScore = String.format("%s %02d:%02d %s", playerName, minutes, seconds, currentDate);
        System.out.println("Writing to file: " + formattedScore);

        String logFilePath = "src/main/resources/leaderboard/gameLog.txt";

        try {
            File file = new File(logFilePath);
            System.out.println("Resolved file path: " + file.getAbsolutePath());

            if (!file.exists()) {
                System.err.println("File does not exist. Creating new file: " + file.getAbsolutePath());
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            if (!file.canWrite()) {
                System.err.println("Write permission denied for file: " + file.getAbsolutePath());
                throw new IOException("Cannot write to file: " + file.getAbsolutePath());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(formattedScore);
                writer.newLine();
                System.out.println("Successfully wrote to file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("An error occurred while accessing or writing to the file.");
            e.printStackTrace();
        }
    }
}
