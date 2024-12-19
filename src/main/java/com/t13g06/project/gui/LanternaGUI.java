package com.t13g06.project.gui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t13g06.project.model.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.HashSet;
import java.util.Set;


public class LanternaGUI implements GUI {
    private final Screen screen;

    private String lastCharacterPressed = "";

    private ACTION lastActionPlayer1 = ACTION.NONE;
    private final Set<ACTION> actionSet = new HashSet<>();

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);

        ((AWTTerminalFrame) terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ACTION action = mapKeyToAction(e.getKeyCode());
                if (action != ACTION.NONE) {
                    actionSet.add(action);

                    char typedChar = e.getKeyChar();
                    if (typedChar != KeyEvent.CHAR_UNDEFINED && isValidCharacter(typedChar)) {

                        lastCharacterPressed = String.valueOf(typedChar);
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ACTION action = mapKeyToAction(e.getKeyCode());
                if (action != ACTION.NONE) {
                    actionSet.remove(action);
                }
            }
        });

        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/RetroLeague.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }
    
    public Set<ACTION> getActionSet() {
        return actionSet;
    }
    
    public ACTION getNextAction() throws IOException {
        if (actionSet.isEmpty()) return ACTION.NONE; // If no keys are pressed, return NONE

        // Process the ActionSet to determine the current action
        if (actionSet.contains(ACTION.QUIT)) return ACTION.QUIT;
        if (actionSet.contains(ACTION.LEFT)) return ACTION.LEFT;
        if (actionSet.contains(ACTION.RIGHT)) return ACTION.RIGHT;
        if (actionSet.contains(ACTION.UP)) return ACTION.UP;
        if (actionSet.contains(ACTION.DOWN)) return ACTION.DOWN;
        if (actionSet.contains(ACTION.SELECT)) return ACTION.SELECT;

        if (actionSet.contains(ACTION.BACKSPACE)) return ACTION.BACKSPACE;
        if (actionSet.contains(ACTION.TYPE)) return ACTION.TYPE;

        return ACTION.NONE;
    }

    private ACTION mapKeyToAction(int keyCode) {
        ACTION action = ACTION.NONE;
        switch (keyCode) {
            case 81: action = ACTION.QUIT; break; // Q
            case 37: action = ACTION.LEFT; break; // Left Arrow
            case 38: action = ACTION.UP; break;   // Up Arrow
            case 39: action = ACTION.RIGHT; break; // Right Arrow
            case 40: action = ACTION.DOWN; break; // Down Arrow
            case 10: action = ACTION.SELECT; break; // Enter
            case 8: action = ACTION.BACKSPACE; break; // Backspace
            default:
                if ((keyCode >= 32 && keyCode <= 126) || (keyCode >= 128 && keyCode <= 255)) {
                    action = ACTION.TYPE; // All printable ASCII or extended characters
                }
        }

        if (action != ACTION.NONE) {
            lastActionPlayer1 = action; // Update the last action here
        }

        return action;
    }


    @Override
    public void drawPlayer_1(Position position) {
        char playerChar;
        switch (lastActionPlayer1) {
            case LEFT:
                playerChar = 'Ó'; // Walking left
                break;
            case RIGHT:
                playerChar = 'Á'; // Walking right
                break;
            case UP:
                playerChar = (lastActionPlayer1 == ACTION.RIGHT) ? 'Â' : 'Ô'; // Jumping
                break;
            default:
                playerChar = (lastActionPlayer1 == ACTION.LEFT) ? 'Ò' : 'À'; // Idle (default direction)
        }

        drawCharacter(position.getX(), position.getY(), playerChar, "#FFFFFF ");
    }

    @Override
    public void drawBall(Position position) {
        drawCharacter(position.getX(), position.getY(), '*', "#FDDA0D");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position.getX(), position.getY(), ' ', "#9B0081");
    }

    @Override
    public void drawPowerUp(Position position, String power) {
        char icon = '*';
        String color = "00CCFF";

        switch (power) {
            case "freeze":
                icon = '%'; // Icon for freezing the other player
                color = "#00CCFF"; // Light blue
                break;
            case "speedUpBall":
                icon = '$'; // Icon for increasing ball speed
                color = "#FF9900"; // Orange
                break;
            case "slowDownEnemy":
                icon = '&'; // Icon for slowing down opponents
                color = "#808080"; // Red
                break;
            case "strongerBall":
                icon = '>'; // Icon for increasing ball strength
                color = "#FF0000"; // Yellow
                break;
            case "jumpBoost":
                icon = '+'; // Icon for jump boost
                color = "#00FF00"; // Green
                break;
            default:
                break;
        }

        drawCharacter(position.getX(), position.getY(), icon, color);
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(new TextColor.RGB(9, 0, 60)); // RGB values

        tg.putString(position.getX(), position.getY(), text);
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(new TextColor.RGB(9, 0, 60));

        if (c == ' ') tg.setBackgroundColor(TextColor.Factory.fromString(color));
        else tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, "" + c);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    private boolean isValidCharacter(char character) {
        return (character >= 'A' && character <= 'Z') || // Uppercase letters
                (character >= 'a' && character <= 'z');  // Lowercase letters
    }


    /**
     * Get the last character typed by the user.
     * Returns '\0' if no valid character has been typed since the last check.
     */
    public String getLastCharacterPressed() {
        String temp = String.valueOf(lastCharacterPressed); // Store the current character
        lastCharacterPressed = ""; // Reset to null character
        return temp; // Return the stored character
    }
    public void drawCharacterImage(Position position, InputStream imageStream, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageStream);

        // Resize the image
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        TextGraphics graphics = screen.newTextGraphics();

        for (int x = 0; x < resizedImage.getWidth(); x++) {
            for (int y = 0; y < resizedImage.getHeight(); y++) {
                int pixelColor = resizedImage.getRGB(x, y);
                int alpha = (pixelColor >> 24) & 0xFF;

                if (alpha == 0) continue;

                int r = (pixelColor >> 16) & 0xFF;
                int g = (pixelColor >> 8) & 0xFF;
                int b = pixelColor & 0xFF;

                graphics.setCharacter(position.getX() + x, position.getY() + y,
                        new TextCharacter(' ', new TextColor.RGB(r, g, b), new TextColor.RGB(r, g, b)));
            }
        }
    }
}
