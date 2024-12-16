package com.t13g06.project.gui;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t13g06.project.model.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private KeyEvent key;
    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);

        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                System.out.println(e);
                try {
                    getNextAction();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                key = e;
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

    public ACTION getNextAction() throws IOException {
        if (key == null) return ACTION.NONE;
        //KeyStroke keyStroke = screen.pollInput();
        int keyStroke = key.getKeyCode();
        key = null;

        if (keyStroke == 81) return ACTION.QUIT; //q

        if (keyStroke == 37) return ACTION.LEFT; //arrow left
        if (keyStroke == 38) return ACTION.UP; //arrow up
        if (keyStroke == 39) return ACTION.RIGHT; //arrow right
        if (keyStroke == 40) return ACTION.DOWN; //arrow down

        if (keyStroke == 87) return ACTION.UP_2; // w
        if (keyStroke == 68) return ACTION.RIGHT_2; // d
        if (keyStroke == 83) return ACTION.DOWN_2; // s
        if (keyStroke == 65) return ACTION.LEFT_2; // a

        //if (keyStroke == KeyType.Enter) return ACTION.SELECT;

        return ACTION.NONE;
    }

    @Override
    public void drawPlayer_1(Position position) {
        drawCharacter(position.getX(), position.getY(), 'À', "#FFD700");

    }
    @Override
    public void drawPlayer_2(Position position) {
        drawCharacter(position.getX(), position.getY(), 'Ò', "#FFD777");
    }

    @Override
    public void drawBall(Position position) {
        drawCharacter(position.getX(), position.getY(), '*', "#8B0000");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position.getX(), position.getY(), ' ', "#3333FF");
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
                color = "#FF0000"; // Red
                break;
            case "strongerBall":
                icon = '>'; // Icon for increasing ball strength
                color = "#FFFF00"; // Yellow
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
        tg.putString(position.getX(), position.getY(), text);
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        if (c == 'Ò' || c == 'T' || c == '⚾') tg.setForegroundColor(TextColor.Factory.fromString(color));
        if(c == ' ') tg.setBackgroundColor(TextColor.Factory.fromString(color));
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
}
