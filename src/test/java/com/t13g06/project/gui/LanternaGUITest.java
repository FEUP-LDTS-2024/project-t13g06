package com.t13g06.project.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.t13g06.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    void testGetNextAction_containsQuit() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.QUIT);
        assertEquals(GUI.ACTION.QUIT, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsUp() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.UP);
        assertEquals(GUI.ACTION.UP, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsDown() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.DOWN);
        assertEquals(GUI.ACTION.DOWN, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsLeft() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.LEFT);
        assertEquals(GUI.ACTION.LEFT, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsRight() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.RIGHT);
        assertEquals(GUI.ACTION.RIGHT, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsSelect() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.SELECT);
        assertEquals(GUI.ACTION.SELECT, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsBackspace() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.BACKSPACE);
        assertEquals(GUI.ACTION.BACKSPACE, gui.getNextAction());
    }

    @Test
    void testGetNextAction_containsType() throws IOException {
        Set<GUI.ACTION> actions = gui.getActionSet();
        assertNotNull(actions);
        actions.add(GUI.ACTION.TYPE);
        assertEquals(GUI.ACTION.TYPE, gui.getNextAction());
    }

    @Test
    void testGetNextActionEmptySet() throws IOException {
        GUI.ACTION actions = gui.getNextAction();
        assertNotNull(actions);
        assertEquals(GUI.ACTION.NONE, actions);
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
        String characterPressed = "A";
        gui.setLastCharacterPressed(characterPressed);
        assertEquals("A", gui.getLastCharacterPressed());
        assertEquals("", gui.getLastCharacterPressed());
    }


    @Test
    void testDrawCharacterImage() throws IOException {
        Position position = new Position(10, 10);

        String imagePath = "src/test/resources/images/GameBackground.png";

        File imageFile = new File(imagePath);
        BufferedImage image = ImageIO.read(imageFile);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", byteArrayOutputStream);
        InputStream imageStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        int targetWidth = 50;
        int targetHeight = 50;

        gui.drawCharacterImage(position, imageStream, targetWidth, targetHeight);
        assertDoesNotThrow(() -> gui.refresh());
    }
}
