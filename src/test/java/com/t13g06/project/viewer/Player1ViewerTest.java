package com.t13g06.project.viewer;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Player_1;
import com.t13g06.project.viewer.game.Player_1_Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Player1ViewerTest {
    private Player_1 player1;
    private Player_1_Viewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        player1 = new Player_1(10, 10);
        viewer = new Player_1_Viewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(player1, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer_1(player1.getPosition());
    }
}
