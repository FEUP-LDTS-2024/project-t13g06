package com.t13g06.project.model.menu;

import java.util.Arrays;
import java.util.List;

public class End {

    private final List<String> entries;
    private int currentEntry = 0;
    private String playerName = "";
    private boolean isSubmitted = false; // Check if the name is submitted
    public long gameTime; // Seconds

    public End(long gameTime) {
        this.entries = Arrays.asList("Enter Your Name:", "Submit", "Go Back");
        this.gameTime = gameTime; // Initialize game time
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedNameField() {
        return isSelected(0);
    }

    public boolean isSelectedSubmit() {
        return isSelected(1);
    }

    public boolean isSelectedEnd() {
        return isSelected(2);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public void updatePlayerName(String character) {
        if (playerName.length() < 6) {
            playerName += character;
        }
    }

    public void removeLastChar() {
        if (!playerName.isEmpty()) {
            playerName = playerName.substring(0, playerName.length() - 1);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted() {
        isSubmitted = !isSubmitted;
    }

    // Getter for game time
    public long getGameTime() {
        return gameTime;
    }

    // Setter for game time
    public void setGameTime(long gameTime_) {

        this.gameTime = gameTime_;
        System.out.println("setted - " + this.gameTime);
    }
}
