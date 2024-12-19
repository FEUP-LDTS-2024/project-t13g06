package com.t13g06.project.viewer;


import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.*;
import com.t13g06.project.viewer.game.GameViewer;
import com.t13g06.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

class ArenaViewerTest {
    private GUI gui;
    private GameViewer viewer;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(arena);

        arena.setWalls(Arrays.asList(new Wall(1, 2), new Wall(2, 3), new Wall(3, 4)));
        arena.setPowerUp(Arrays.asList(new PowerUps(4, 5, "freeze"), new PowerUps(5, 6, "jumpBoost")));
        arena.setPlayer_1(new Player_1(5, 8));
        arena.setPlayer_2(new Player_2(5,12));
        //TODO DAR FIX arena.setBalls(new Ball(10,7));
    }


    @Test
    void drawWalls() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawPowerups() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(new Position(4, 5), "freeze");
        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(new Position(5, 6), "jumpBoost");
        Mockito.verify(gui, Mockito.times(2)).drawPowerUp(Mockito.any(Position.class), Mockito.anyString());
    }

    @Test
    void drawPlayer1() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawPlayer_1(new Position(5, 8));
        Mockito.verify(gui, Mockito.times(1)).drawPlayer_1(Mockito.any(Position.class));
    }
    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}
