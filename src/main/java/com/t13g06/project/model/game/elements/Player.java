package com.t13g06.project.model.game.elements;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends Element {
    private int lives;
    private boolean isJumping;
    private int jumpProgress;
    private int jumpHeight;
    private double verticalSpeed;

    // Constructor: Initializes player position, lives, and jump properties
    public Player(int x, int y) {
        super(x, y);
        this.lives = 3;
        this.isJumping = false;
        this.jumpProgress = 0;
        this.jumpHeight = 5;
        this.verticalSpeed = 0.0;
    }

    // Gets the number of remaining lives
    public int getLives() {
        return lives;
    }

    // Reduces the player's lives by a specified amount
    public void loseLife(int amount) {
        this.lives -= amount;
        if (this.lives < 0) this.lives = 0;
    }

    // Checks if the player is currently jumping
    public boolean isJumping() {
        return isJumping;
    }

    // Sets the player's jumping state
    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    // Gets the player's current jump progress
    public int getJumpProgress() {
        return jumpProgress;
    }

    // Sets the player's current jump progress
    public void setJumpProgress(int jumpProgress) {
        this.jumpProgress = jumpProgress;
    }

    // Gets the player's jump height
    public int getJumpHeight() {
        return jumpHeight;
    }

    // Resets the player's jump height to its default value
    public void setJumpHeightStart() {
        this.jumpHeight = 6;
    }

    // Gets the player's vertical speed for gravity effects
    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    // Sets the player's vertical speed for gravity effects
    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    // Resets the player's jump state and progress
    public void resetJump() {
        this.isJumping = false;
        this.jumpProgress = 0;
    }

    // Activates a temporary jump boost, doubling the jump height
    public void activateJumpBoost() {
        this.jumpHeight += 3;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setJumpHeightStart();
            }
        }, 4000); // Reverts jump height to default after 4 seconds
    }
}
