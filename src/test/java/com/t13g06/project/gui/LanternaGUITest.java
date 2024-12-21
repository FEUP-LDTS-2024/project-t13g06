package com.t13g06.project.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t13g06.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGUITest {

    private LanternaGUI gui;
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;

    @BeforeEach
    void setUp() throws IOException, FontFormatException, URISyntaxException {
        mockScreen = mock(Screen.class);
        mockTextGraphics = mock(TextGraphics.class);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        gui = new LanternaGUI(mockScreen);
    }

    @Test
    void testGetActionSetInitiallyEmpty() {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        assertTrue(actions.isEmpty());
    }

    @Test
    void testDrawPlayer() {
        Position position = new Position(5, 5);
        gui.drawPlayer(position);
        assertDoesNotThrow(() -> gui.refresh());
    }

    @Test
    void testDrawBall() {
        Position position = new Position(3, 3);
        gui.drawBall(position);
        assertDoesNotThrow(() -> gui.refresh());
    }

    @Test
    void testDrawWall() {
        Position position = new Position(2, 2);
        gui.drawWall(position);
        assertDoesNotThrow(() -> gui.refresh());
    }

    @Test
    void testDrawPowerUp() {
        Position position = new Position(4, 4);
        gui.drawPowerUp(position, "freeze");
        assertDoesNotThrow(() -> gui.refresh());
    }

    @Test
    void testDrawText() {
        Position position = new Position(0, 0);
        gui.drawText(position, "Test", "#FFFFFF");
        assertDoesNotThrow(() -> gui.refresh());
    }

    @Test
    void testClear() throws IOException {
        gui.clear();
        verify(mockScreen, times(1)).clear();
    }

    @Test
    void testRefresh() throws IOException {
        gui.refresh();
        verify(mockScreen, times(1)).refresh();
    }

    @Test
    void testClose() throws IOException {
        gui.close();
        verify(mockScreen, times(1)).close();
    }

    @Test
    void testGetLastCharacterPressed() {
        assertEquals("", gui.getLastCharacterPressed());
        assertEquals("", gui.getLastCharacterPressed());
    }

    @Test
    void testGetNextActionEmptySet() throws IOException {
        GUI.ACTION action = gui.getNextAction();
        assertEquals(GUI.ACTION.NONE, action);
    }

    @Test
    void testGetNextActionQuit() throws IOException {
        gui.getActionSet().add(GUI.ACTION.QUIT);
        GUI.ACTION action = gui.getNextAction();
        assertEquals(GUI.ACTION.QUIT, action);
    }
}
