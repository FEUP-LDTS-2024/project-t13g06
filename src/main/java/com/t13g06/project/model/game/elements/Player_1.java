package com.t13g06.project.model.game.elements;

public class Player_1 extends Element {
    private int lives;
    private boolean isJumping;
    private int jumpProgress;
    private int jumpHeight;
    private double verticalSpeed; // Current vertical speed for gravity effects

    public Player_1(int x, int y) {
        super(x, y);
        this.lives = 5;
        this.isJumping = false;
        this.jumpProgress = 0;
        this.jumpHeight = 3; // Default jump height
        this.verticalSpeed = 0.0; // Start with no gravity effect
    }

    // Lives management
    public void decreaseLive() {
        this.lives--;
    }

    public int getLives() {
        return lives;
    }

    // Jump state management
    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public int getJumpProgress() {
        return jumpProgress;
    }

    public void setJumpProgress(int jumpProgress) {
        this.jumpProgress = jumpProgress;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public void resetJump() {
        this.isJumping = false;
        this.jumpProgress = 0;
    }

    public void activateJumpBoost() {
        this.jumpHeight *= 3; // Increase jump height temporarily
    }
}
