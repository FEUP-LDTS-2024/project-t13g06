package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.io.InputStream;

public class InstructionsViewer extends Viewer<Instructions> {

    public InstructionsViewer(Instructions settings) {
        super(settings);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        // Load the background image
        String filePath = "images/InstructionsMenuBackground.png";
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (imageStream != null) {
            int scaledWidth = 51;  // Target width in text cells
            int scaledHeight = 21; // Target height in text cells
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: " + filePath);
        }

        // Explanation
        gui.drawText(new Position(4, 8), "WELCOME TO RETRO LEAGUE", "#FFFFFF");
        gui.drawText(new Position(4, 10), "Survive as long as possible;", "#FFFFFF");
        gui.drawText(new Position(4, 11), "AVOID GETTING HIT BY THE BALLs;", "#FFFFFF");
        gui.drawText(new Position(4, 12), "Be agile and SMART WHEN CHOOSING A POWER", "#FFFFFF");
        // Power-Ups icons
        gui.drawText(new Position(4, 14), "LIST OF POWER UPS:", "#FF00FF");

        gui.drawText(new Position(5, 15), "%", "#00CCFF");
        gui.drawText(new Position(5, 16), "$", "#FF9900");
        gui.drawText(new Position(5, 17), "&", "#808080");
        gui.drawText(new Position(5, 18), "+", "#FF0000");
        gui.drawText(new Position(5, 19), ">", "#00FF00");
        // Power-ups explanation
        gui.drawText(new Position(6, 15), " : BALL FREEZE", "#FFFFFF");
        gui.drawText(new Position(6, 16), " : BALL SPEED-UP", "#FFFFFF");
        gui.drawText(new Position(6, 17), " : BALL SLOWNESS", "#FFFFFF");
        gui.drawText(new Position(6, 18), " : JUMP BOOST", "#FFFFFF");
        gui.drawText(new Position(6, 19), " : STRONGER BALL", "#FFFFFF");

        // Controls
        gui.drawText(new Position(26, 14), "LIST OF CONTROLS:", "#FF00FF");
        gui.drawText(new Position(26, 16), "USE UR ARROW KEYS TO MOVE", "#FFFFFF");
        gui.drawText(new Position(26, 17), "PRESS P TO PAUSE", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(45, 19 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF"
            );
        }
    }

}
