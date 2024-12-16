package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Element;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.util.List;

public class GameViewer extends Viewer<Arena> {
    public GameViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getPowerUp(), new PowerUpsViewer());
        drawElement(gui, getModel().getPlayer_1(), new Player_1_Viewer());
        drawElement(gui, getModel().getPlayer_2(), new Player_2_Viewer());
        drawElement(gui, getModel().getBall(), new BallViewer());

        gui.drawText(
                new Position(4, 2),
                "Player 1: " + "?".repeat(getModel().getPlayer_1().getLives()),
                "#FFD700"
        );


        gui.drawText(new Position(34, 2), "Player 2: " + getModel().getPlayer_2().getLives() + " lives", "#FFD700");
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
