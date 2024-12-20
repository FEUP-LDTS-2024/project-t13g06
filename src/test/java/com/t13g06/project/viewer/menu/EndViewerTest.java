package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.End;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;

class EndViewerTest {

    private GUI mockGui;
    private End mockEndModel;
    private EndViewer endViewer;

    @BeforeEach
    void setUp() {
        mockGui = mock(GUI.class);
        mockEndModel = mock(End.class);

        when(mockEndModel.getGameTime()).thenReturn(125L);
        when(mockEndModel.getPlayerName()).thenReturn("Player1");
        when(mockEndModel.getEntry(0)).thenReturn("Option 1");
        when(mockEndModel.getEntry(1)).thenReturn("Option 2");
        when(mockEndModel.getEntry(2)).thenReturn("Exit");
        when(mockEndModel.isSelected(0)).thenReturn(false);
        when(mockEndModel.isSelected(1)).thenReturn(true);
        when(mockEndModel.isSelected(2)).thenReturn(false);
        when(mockEndModel.isSubmitted()).thenReturn(false);

        endViewer = new EndViewer(mockEndModel, 125L);
    }

    @Test
    void testDrawElements_NotSubmitted() throws IOException {
        endViewer.drawElements(mockGui);

        verify(mockGui).drawText(new Position(26, 8), "Time Survived: 02:05", "#FFFFFF");
        verify(mockGui).drawText(new Position(38, 13), "Player1", "#FFFFFF");
        verify(mockGui).drawText(new Position(22, 13), "Option 1", "#FFFFFF");
        verify(mockGui).drawText(new Position(22, 14), "Option 2", "#FFD700");
        verify(mockGui).drawText(new Position(22, 15), "Exit", "#FFFFFF");
    }

    @Test
    void testDrawElements_Submitted() throws IOException {
        when(mockEndModel.isSubmitted()).thenReturn(true);

        endViewer.drawElements(mockGui);

        verify(mockGui).drawText(new Position(22, 11), "Score Saved!", "#00FF00");
        verify(mockGui).drawText(new Position(22, 12), "Player1 02:05 2024/12/20", "#FFFFFF");
    }
}
