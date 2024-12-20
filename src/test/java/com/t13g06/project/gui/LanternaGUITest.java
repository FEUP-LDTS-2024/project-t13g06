package com.t13g06.project.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.t13g06.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LanternaGUITest {
    private Screen screenMock;
    private TextGraphics textGraphicsMock;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        screenMock = mock(Screen.class);
        textGraphicsMock = mock(TextGraphics.class);
        when(screenMock.newTextGraphics()).thenReturn(textGraphicsMock);
        gui = new LanternaGUI(screenMock);
    }

    @Test
    void testGetNextAction_upArrow() throws IOException {
        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp, false, false));

        assertEquals(GUI.ACTION.UP, gui.getNextAction());
    }

    @Test
    void testGetNextAction_quitKey() throws IOException {
        when(screenMock.pollInput()).thenReturn(new KeyStroke('q', false, false));

        assertEquals(GUI.ACTION.QUIT, gui.getNextAction());
    }

    @Test
    void testGetNextAction_none() throws IOException {
        when(screenMock.pollInput()).thenReturn(null);

        assertEquals(GUI.ACTION.NONE, gui.getNextAction());
    }

    @Test
    void testDrawPlayer1() {
        Position position = new Position(5, 10);

        gui.drawPlayer(position);

        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        verify(textGraphicsMock).putString(5, 11, "O");
    }

    @Test
    void testDrawPlayer2() {
        Position position = new Position(7, 12);

        gui.drawPlayer_2(position);

        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString("#FFD777"));
        verify(textGraphicsMock).putString(7, 13, "T");
    }

    @Test
    void testDrawBall() {
        Position position = new Position(10, 15);

        gui.drawBall(position);

        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString("#8B0000"));
        verify(textGraphicsMock).putString(10, 16, "⚾");
    }

    @Test
    void testDrawPowerUp_freeze() {
        Position position = new Position(3, 4);

        gui.drawPowerUp(position, "freeze");

        verify(textGraphicsMock).setBackgroundColor(TextColor.Factory.fromString("#00CCFF"));
        verify(textGraphicsMock).putString(3, 5, "*");
    }

    @Test
    void testDrawPowerUp_speedUpBall() {
        Position position = new Position(6, 8);

        gui.drawPowerUp(position, "speedUpBall");

        verify(textGraphicsMock).setBackgroundColor(TextColor.Factory.fromString("#FF9900"));
        verify(textGraphicsMock).putString(6, 9, ">");
    }

    @Test
    void testDrawText() {
        Position position = new Position(20, 25);
        String text = "Game Over!";
        String color = "#FFFFFF";

        gui.drawText(position, text, color);

        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString(color));
        verify(textGraphicsMock).putString(20, 25, text);
    }

    @Test
    void testClear() throws IOException {
        gui.clear();

        verify(screenMock).clear();
    }

    @Test
    void testRefresh() throws IOException {
        gui.refresh();

        verify(screenMock).refresh();
    }

    @Test
    void testClose() throws IOException {
        gui.close();

        verify(screenMock).close();
    }
}
