package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.elements.*;

import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;

    private Player player_1;
    private List<Ball> balls;
    private List<PowerUps> powerUp;
    private List<Wall> walls;

    private Random random = new Random();

    // Constructor: Initializes the arena with the specified width and height
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Returns the width of the arena
    public int getWidth() {
        return width;
    }

    // Returns the height of the arena
    public int getHeight() {
        return height;
    }

    // Returns the player in the arena
    public Player getPlayer_1() {
        return player_1;
    }

    // Sets the player in the arena
    public void setPlayer_1(Player player_1_) {
        this.player_1 = player_1_;
    }

    // Returns the list of balls in the arena
    public List<Ball> getBalls() {
        return balls;
    }

    // Sets the list of balls in the arena
    public void setBalls(List<Ball> balls_) {
        this.balls = balls_;
    }

    // Adds a ball to the arena
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    // Returns the list of power-ups in the arena
    public List<PowerUps> getPowerUp() {
        return powerUp;
    }

    // Sets the list of power-ups in the arena
    public void setPowerUp(List<PowerUps> powerUp_) {
        this.powerUp = powerUp_;
    }

    // Returns the list of walls in the arena
    public List<Wall> getWalls() {
        return walls;
    }

    // Sets the list of walls in the arena
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    // Checks if a position is occupied by a wall
    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    // Checks if a position is empty (not occupied by a wall)
    public boolean isEmpty(Position position) {
        return !isWall(position);
    }

    // Checks if a position is occupied by a power-up
    public boolean isPowerUp(Position position) {
        for (PowerUps powerUp : powerUp)
            if (powerUp.getPosition().equals(position))
                return true;
        return false;
    }

     // Finds a random valid position within the arena that does not overlap with walls, players, balls, or existing power-ups
    public Position getRandomValidPosition() {
        int width = getWidth();
        int height = getHeight();
        int maxAttempts = 100;
        int padding = 3;

        for (int i = 0; i < maxAttempts; i++) {

            Position pos = new Position(random.nextInt(width - 2 * padding) + padding,
                    random.nextInt(height - 2 * padding) + padding);

            boolean positionIsValid = isEmpty(pos)
                    && !pos.equals(getPlayer_1().getPosition()) // Ensure it's not the player's position
                    && getBalls().stream().noneMatch(ball -> ball.getPosition().equals(pos)) // Check balls
                    && getPowerUp().stream().noneMatch(powerUp -> powerUp.getPosition().equals(pos)); // Check power-ups

            if (positionIsValid) {
                return pos;
            }
        }
        return null;
    }
}
