package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.LeaderBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaderBoardViewerTest {
    private LeaderBoard leaderBoard;
    private GUI gui;
    private LeaderBoardViewer viewer;

    @BeforeEach
    void setup() {
        leaderBoard = mock(LeaderBoard.class);
        gui = mock(GUI.class);
        viewer = new LeaderBoardViewer(leaderBoard);
    }

    @Test
    void testDrawBackground_imageFound() throws Exception {
        InputStream mockStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("images/LeaderBoardMenuBackground.png")).thenReturn(mockStream);
        viewer = new LeaderBoardViewer(leaderBoard) {
        };

        viewer.drawElements(gui);

        // Use an ArgumentMatcher to match any InputStream
        verify(gui).drawCharacterImage(eq(new Position(2, 0)), any(InputStream.class), eq(51), eq(21));
    }

    @Test
    void testDrawMenuEntries() throws IOException {
        when(leaderBoard.getNumberEntries()).thenReturn(3);
        when(leaderBoard.getEntry(0)).thenReturn("Start Game");
        when(leaderBoard.isSelected(0)).thenReturn(true);
        when(leaderBoard.getEntry(1)).thenReturn("Options");
        when(leaderBoard.isSelected(1)).thenReturn(false);

        viewer.drawElements(gui);

        verify(gui).drawText(new Position(45, 19), "Start Game", "#FFD700");
        verify(gui).drawText(new Position(45, 20), "Options", "#FFFFFF");
    }
}
