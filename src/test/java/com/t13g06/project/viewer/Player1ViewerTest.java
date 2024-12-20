package com.t13g06.project.viewer;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.viewer.game.PlayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Player1ViewerTest {
    private Player player1;
    private PlayerViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        player1 = new Player(10, 10);
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(player1, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player1.getPosition());
    }
}
