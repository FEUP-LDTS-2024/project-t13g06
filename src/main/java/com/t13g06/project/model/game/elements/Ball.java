package com.t13g06.project.model.game.elements;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Element {
    private boolean frozen = false;
    private boolean isStronger = false; // Flag for stronger ball state

    private int speed = 1; // Default speed multiplier

    // Direction of the ball
    private int xDirection; // Horizontal movement direction (-1 or 1)
    private int yDirection; // Vertical movement direction (-1 or 1)

    // New fields for hit, blinking, and disappearance logic
    private boolean isHit = false;       // Ball has hit the player
    private int blinkCount = 0;          // Tracks how many times the ball has blinked
    private long blinkCooldown = 300;    // Cooldown between blinks (in milliseconds)
    private long lastBlinkTime = 0;      // Last time the ball toggled color
    private String color = "white";      // Ball's color (red/white)

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

    // Handle the ball hit logic
    public void handleHit() {
        this.isHit = true;
        this.blinkCount = 0; // Reset blink count
        this.lastBlinkTime = System.currentTimeMillis();
        this.color = "red"; // Start with red color
    }

    // Update the blinking logic
    public boolean updateBlinking() {
        if (!isHit) return false; // Only process blinking when ball is hit

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBlinkTime >= blinkCooldown) {
            toggleColor(); // Switch between red and white
            lastBlinkTime = currentTime;
            blinkCount++;
        }

        // If the ball has blinked 4 times (2 red + 2 white), return true to indicate disappearance
        return blinkCount >= 4;
    }

    // Toggle the ball color
    private void toggleColor() {
        this.color = this.color.equals("red") ? "white" : "red";
    }

    // Check if the ball is hit
    public boolean isHit() {
        return isHit;
    }

    // Get the ball's current color
    public String getColor() {
        return color;
    }
    // Method to make the ball stronger
    public void makeStronger() {
        this.isStronger = true;
    }

    // Method to revert the ball to its normal state
    public void makeNormal() {
        this.isStronger = false;
    }

    // Method to check if the ball is stronger
    public boolean isStronger() {
        return this.isStronger;
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

    // Optional: Reset the direction to a random value
    public void resetDirection() {
        initializeBallDirection(); // Reset to new random direction
    }
}
