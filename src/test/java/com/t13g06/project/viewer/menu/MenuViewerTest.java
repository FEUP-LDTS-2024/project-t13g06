package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuViewerTest {

    private Menu menuMock;
    private GUI guiMock;
    private MenuViewer menuViewer;

    @BeforeEach
    void setUp() {
        menuMock = mock(Menu.class);
        guiMock = mock(GUI.class);
        menuViewer = new MenuViewer(menuMock);
    }

    @Test
    void testDrawElementsDrawsMenuEntries() throws IOException {
        // Arrange
        when(menuMock.getNumberEntries()).thenReturn(3);
        when(menuMock.getEntry(0)).thenReturn("Option 1");
        when(menuMock.getEntry(1)).thenReturn("Option 2");
        when(menuMock.getEntry(2)).thenReturn("Option 3");
        when(menuMock.isSelected(0)).thenReturn(false);
        when(menuMock.isSelected(1)).thenReturn(true);
        when(menuMock.isSelected(2)).thenReturn(false);

        menuViewer.drawElements(guiMock);

        verify(guiMock).drawText(new Position(35, 7), "Option 1", "#FFFFFF");
        verify(guiMock).drawText(new Position(35, 9), "Option 2", "#FFD700");
        verify(guiMock).drawText(new Position(35, 11), "Option 3", "#FFFFFF");
    }

    @Test
    void testDrawElementsHandlesMissingImage() throws IOException {
        menuViewer.drawElements(guiMock);
    }
}
