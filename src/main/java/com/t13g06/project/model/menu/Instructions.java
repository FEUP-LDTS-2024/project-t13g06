package com.t13g06.project.model.menu;

import java.util.Arrays;
import java.util.List;

public class Instructions {

    private final List<String> entries;
    private int currentEntry = 0;

    public Instructions() {
        this.entries = Arrays.asList("Go Back");
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) { return currentEntry == i; }

    public boolean isSelectedSettings() {
        return isSelected(0);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
