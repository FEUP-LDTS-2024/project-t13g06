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

    public Position getRandomValidPosition() {
        int width = getWidth();
        int height = getHeight();
        int maxAttempts = 100;
        int padding = 3; // Set the padding to 3

        // Ensure the valid range for spawning the power-up is adjusted with the padding
        for (int i = 0; i < maxAttempts; i++) {
            // Random position within the boundaries, excluding padding
            Position pos = new Position(random.nextInt(width - 2 * padding) + padding,
                    random.nextInt(height - 2 * padding) + padding);

            // Check if the position is empty and does not overlap with players, balls, or power-ups
            boolean positionIsValid = isEmpty(pos)
                    && !pos.equals(getPlayer_1().getPosition()) // Ensure the position is not the player's position
                    && getBalls().stream().noneMatch(ball -> ball.getPosition().equals(pos)) // Check against all balls
                    && getPowerUp().stream().noneMatch(powerUp -> powerUp.getPosition().equals(pos)); // Check against existing power-ups

            if (positionIsValid) {
                return pos;
            }
        }
        return null; // If no valid position is found after multiple attempts
    }






}
