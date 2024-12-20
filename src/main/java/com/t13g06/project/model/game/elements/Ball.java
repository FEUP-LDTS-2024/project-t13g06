package com.t13g06.project.model.game.elements;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Element {
    private boolean frozen = false;
    private boolean isStronger = false;
    private int speed = 1;
    private int xDirection;
    private int yDirection;
    private boolean isHit = false;
    private int blinkCount = 0;
    private long blinkCooldown = 300;
    private long lastBlinkTime = 0;
    private String color = "white";

    // Constructor: Initializes the ball's position and directions
    public Ball(int x, int y) {
        super(x, y);
        initializeBallDirection();
    }

    // Initializes the ball's random movement direction
    public void initializeBallDirection() {
        Random random = new Random();
        this.xDirection = random.nextBoolean() ? 1 : -1;
        this.yDirection = random.nextBoolean() ? 1 : -1;
    }

    // Freezes the ball for a specified duration
    public void freeze(int duration) {
        frozen = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                frozen = false;
            }
        }, duration);
    }

    // Handles the ball hit logic
    public void handleHit() {
        this.isHit = true;
        this.blinkCount = 0;
        this.lastBlinkTime = System.currentTimeMillis();
        this.color = "red";
    }

    // Updates the blinking logic for the ball
    public boolean updateBlinking() {
        if (!isHit) return false;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBlinkTime >= blinkCooldown) {
            toggleColor();
            lastBlinkTime = currentTime;
            blinkCount++;
        }

        return blinkCount >= 4;
    }

    // Toggles the ball's color between red and white
    private void toggleColor() {
        this.color = this.color.equals("red") ? "white" : "red";
    }

    // Makes the ball stronger
    public void makeStronger() {
        this.isStronger = true;
    }

    // Reverts the ball to its normal state
    public void makeNormal() {
        this.isStronger = false;
    }

    // Increases the ball's speed
    public void increaseSpeed() {
        speed = Math.min(speed + 1, 10);
    }

    // Decreases the ball's speed
    public void decreaseSpeed() {
        speed = Math.max(speed - 1, 1);
    }

    // Resets the direction of the ball to a random value
    public void resetDirection() {
        initializeBallDirection();
    }

    // Getter methods
    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isStronger() {
        return this.isStronger;
    }

    public boolean isHit() {
        return isHit;
    }

    public String getColor() {
        return color;
    }

    // Setter methods
    public void setXDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }
}
