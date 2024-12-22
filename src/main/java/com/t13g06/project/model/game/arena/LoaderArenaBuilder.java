package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.game.elements.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoaderArenaBuilder extends ArenaBuilder {
    private final int level; // The level to load
    private final List<String> lines; // Lines read from the level file

    public LoaderArenaBuilder(int level) throws IOException {
        this.level = level;

        URL resource = LoaderArenaBuilder.class.getResource("/arenas/arena" + level + ".lvl");
        if (resource == null) {
            throw new IOException("Resource not found");
        }
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    // Reads all lines from the file
    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    // Gets the width of the arena based on the longest line
    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    // Gets the height of the arena based on the number of lines
    @Override
    protected int getHeight() {
        return lines.size();
    }

    // Creates walls based on the '#' characters in the file
    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x, y));
        }

        return walls;
    }

    // Creates power-ups based on specific characters in the file
    @Override
    protected List<PowerUps> createPowerUp() {
        List<PowerUps> powerUps = new ArrayList<>();
        Map<Character, String> powerUpTypes = Map.of(
                '*', "freeze",
                '>', "speedUpBall",
                '<', "slowDownEnemy",
                '^', "strongerBall",
                'J', "jumpBoost"
        );

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char currentChar = line.charAt(x);

                if (powerUpTypes.containsKey(currentChar)) {
                    String powerType = powerUpTypes.get(currentChar);
                    powerUps.add(new PowerUps(x, y, powerType));
                }
            }
        }

        return powerUps;
    }

    // Creates the player based on the 'O' character in the file
    @Override
    protected Player createPlayer_1() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'O') return new Player(x, y);
        }
        return null;
    }

    // Creates balls based on the '⚾' character in the file
    @Override
    protected List<Ball> createBall() {
        List<Ball> balls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '⚾') {
                    balls.add(new Ball(x, y));
                }
            }
        }

        return balls;
    }
}
