package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;

    private Player_1 player_1;

    private List<Ball> balls;

    private List<PowerUps> powerUp;
    private List<Wall> walls;

    private Random random = new Random();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player_1 getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(Player_1 player_1_) {
        this.player_1 = player_1_;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls_) {
        this.balls = balls_;
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    public List<PowerUps> getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(List<PowerUps> powerUp_) {
        this.powerUp = powerUp_;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }


    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isEmpty(Position position) {
        return !isWall(position);
    }

    public boolean isPowerUp(Position position) {
        for (PowerUps powerUp : powerUp)
            if (powerUp.getPosition().equals(position))
                return true;
        return false;
    }



    // Find a random valid position for spawning power-ups
    public Position getRandomValidPosition() {
        int width = getWidth();
        int height = getHeight();
        int maxAttempts = 100;

        for (int i = 0; i < maxAttempts; i++) {
            Position pos = new Position(random.nextInt(width), random.nextInt(height));

            // Check if position is empty and does not overlap with players or balls
            boolean positionIsValid = isEmpty(pos)
                    && !pos.equals(getPlayer_1().getPosition())
                    && getBalls().stream().noneMatch(ball -> ball.getPosition().equals(pos)); // Check against all balls

            if (positionIsValid) {
                return pos;
            }
        }
        return null; // If no valid position is found after multiple attempts
    }


}
