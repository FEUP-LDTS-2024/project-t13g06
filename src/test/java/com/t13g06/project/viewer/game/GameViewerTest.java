package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class GameViewerTest {
    private GUI guiMock;
    private Arena arenaMock;
    private GameViewer gameViewer;

    @BeforeEach
    void setUp() {
        guiMock = mock(GUI.class);
        arenaMock = mock(Arena.class);
        gameViewer = new GameViewer(arenaMock);
    }

    @Test
    void testDrawElements() throws IOException {
        Player playerMock = mock(Player.class);
        when(arenaMock.getPlayer_1()).thenReturn(playerMock);

        List<Ball> balls = new ArrayList<>();
        Ball ballMock = mock(Ball.class);
        balls.add(ballMock);
        when(arenaMock.getBalls()).thenReturn(balls);

        gameViewer.drawElements(guiMock);

        verify(guiMock, atLeastOnce()).drawCharacterImage(any(), any(), anyInt(), anyInt());
        verify(guiMock, atLeastOnce()).drawText(any(), any(), any());
        verify(guiMock, atLeastOnce()).drawCharacterImage(any(), any(), anyInt(), anyInt());
        verify(guiMock, atLeastOnce()).drawText(any(), any(), any());
    }
}
