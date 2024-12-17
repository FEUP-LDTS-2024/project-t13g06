package com.t13g06.project.model.game.elements;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Element {
    private boolean frozen = false;
    private int speed = 1; // Default speed multiplier

    // Direction of the ball
    private int xDirection; // Horizontal movement direction (-1 or 1)
    private int yDirection; // Vertical movement direction (-1 or 1)

    // Constructor to initialize the ball's position and directions
    public Ball(int x, int y) {
        super(x, y);
        initializeBallDirection(); // Set random initial direction for the ball
    }

    // Method to initialize the ball's random movement direction
    public void initializeBallDirection() {
        Random random = new Random();
        this.xDirection = random.nextBoolean() ? 1 : -1; // Randomly choose left or right
        this.yDirection = random.nextBoolean() ? 1 : -1; // Randomly choose up or down
    }

    // Freeze the ball for a specified duration
    public void freeze(int duration) {
        frozen = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                frozen = false;
            }
        }, duration);
    }

    // Check if the ball is frozen
    public boolean isFrozen() {
        return frozen;
    }

    // Increase ball speed
    public void increaseSpeed() {
        speed = Math.min(speed + 1, 10); // Cap speed multiplier at 10x
    }

    // Decrease ball speed
    public void decreaseSpeed() {
        speed = Math.max(speed - 1, 1); // Minimum speed multiplier is 1x
    }

    // Get the ball's speed multiplier
    public int getSpeed() {
        return speed;
    }

    // Getters and setters for x and y direction
    public int getXDirection() {
        return xDirection;
    }

    public void setXDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    // Optional: Reset the direction to a random value
    public void resetDirection() {
        initializeBallDirection(); // Reset to new random direction
    }
}
