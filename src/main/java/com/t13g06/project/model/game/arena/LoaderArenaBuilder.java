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
    private final int level;
    private final List<String> lines;

    public LoaderArenaBuilder(int level) throws IOException {
        this.level = level;

        URL resource = LoaderArenaBuilder.class.getResource("/arenas/arena" + level + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }

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

    @Override
    protected List<PowerUps> createPowerUp() {
        List<PowerUps> powerUps = new ArrayList<>();

        // Define a map to associate icons with power-up types
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

    @Override
    protected Player_1 createPlayer_1() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'O') return new Player_1(x, y);
        }
        return null;
    }
    @Override
    protected Player_2 createPlayer_2() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'T') return new Player_2(x, y);
        }
        return null;
    }

    @Override
    protected List<Ball> createBall() {
        List<Ball> balls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '⚾') { // Check for the ball character
                    balls.add(new Ball(x, y)); // Create and add the ball
                }
            }
        }

        return balls; // Return the list of balls
    }



}