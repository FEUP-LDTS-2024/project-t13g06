package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    private final int width;
    private final int height;
    private final int numberOfPowerUps;

    public RandomArenaBuilder(int width, int height, int numberOfPowerUps_) {
        this.rng = new Random();

        this.width = width;
        this.height = height;
        this.numberOfPowerUps = numberOfPowerUps_;
    }

    @Override
    protected int getWidth() {
        return width;
    }

    @Override
    protected int getHeight() {
        return height;
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }

        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width - 1, y));
        }

        return walls;
    }

    @Override
    protected List<PowerUps> createPowerUp() {
        List<PowerUps> powerUps = new ArrayList<>();
        List<String> powerUpTypes = List.of("freeze", "speedUpBall", "slowDownEnemy", "strongerBall", "jumpBoost");

        while (powerUps.size() < numberOfPowerUps) {

            int x = rng.nextInt(width - 2) + 1;
            int y = rng.nextInt(height - 2) + 1;

            String powerType = powerUpTypes.get(rng.nextInt(powerUpTypes.size()));

            powerUps.add(new PowerUps(x, y, powerType));
        }

        return powerUps;
    }


    @Override
    protected Player_1 createPlayer_1() {
        return new Player_1(width / 2, height / 2);
    }

    protected Player_2 createPlayer_2() {
        return new Player_2(width / 2, height / 2);
    }

    protected Ball createBall() {
        return new Ball(width / 2, height / 2);
    }
}