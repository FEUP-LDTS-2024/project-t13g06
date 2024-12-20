package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.model.game.elements.PowerUps;

import java.util.Set;

public class PlayerController extends GameController {
    private final PowerUpController powerUpController;

    // Constructor to initialize the player controller and power-up controller
    public PlayerController(Arena arena) {
        super(arena);
        this.powerUpController = new PowerUpController(arena);
    }

    // Applies gravity to the player, moving them down if there's no wall below
    public void applyGravity() {
        Position currentPos = getModel().getPlayer_1().getPosition();
        Position below = currentPos.getDown();
        Player player = getModel().getPlayer_1();

        if (!player.isJumping() && getModel().isEmpty(below)) {
            player.setVerticalSpeed(player.getVerticalSpeed() + 0.5);
            moveVertically((int) (-1 * Math.min(player.getVerticalSpeed(), 1)));
        } else {
            player.setVerticalSpeed(0);
        }
    }

    // Handles the player's jumping motion, moving them upward
    public void jump() {
        Player player = getModel().getPlayer_1();
        if (player.isJumping() && player.getJumpProgress() < player.getJumpHeight()) {
            moveVertically(1);
            player.setJumpProgress(player.getJumpProgress() + 1);
        } else {
            player.resetJump();
        }
    }

    // Starts a jump if the player is standing on a surface
    public void startJump() {
        Player player = getModel().getPlayer_1();
        Position currentPos = player.getPosition();

        if (!getModel().isEmpty(currentPos.getDown())) {
            player.setJumping(true);
            player.setJumpProgress(0);
        }
    }

    // Moves the player to the left if the space is empty
    public void moveHeroLeft() {
        Position currentPos = getModel().getPlayer_1().getPosition();
        Position left = currentPos.getLeft();

        if (getModel().isEmpty(left)) {
            getModel().getPlayer_1().setPosition(left);
        }
    }

    // Moves the player to the right if the space is empty
    public void moveHeroRight() {
        Position currentPos = getModel().getPlayer_1().getPosition();
        Position right = currentPos.getRight();

        if (getModel().isEmpty(right)) {
            getModel().getPlayer_1().setPosition(right);
        }
    }

    // Moves the player vertically based on the direction (up or down)
    private void moveVertically(int direction) {
        Position currentPos = getModel().getPlayer_1().getPosition();
        Position targetPos = direction > 0 ? currentPos.getUp() : currentPos.getDown();

        if (getModel().isEmpty(targetPos)) {
            getModel().getPlayer_1().setPosition(targetPos);
        }
    }

    // Main game loop for the player controller to process actions and movement
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        applyGravity();

        if (actionSet.contains(GUI.ACTION.LEFT)) moveHeroLeft();
        if (actionSet.contains(GUI.ACTION.RIGHT)) moveHeroRight();
        if (actionSet.contains(GUI.ACTION.UP) && !getModel().getPlayer_1().isJumping()) startJump();

        if (getModel().getPlayer_1().isJumping()) jump();

        Position playerPos = getModel().getPlayer_1().getPosition();
        for (PowerUps powerUp : getModel().getPowerUp()) {
            if (powerUp.getPosition().equals(playerPos)) {
                powerUpController.collectPowerUp(powerUp, getModel().getPlayer_1());
                break;
            }
        }
    }
}
