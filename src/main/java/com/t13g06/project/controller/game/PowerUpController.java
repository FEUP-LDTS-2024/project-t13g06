package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.controller.game.GameController;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player_1;
import com.t13g06.project.model.game.elements.PowerUps;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUpController extends GameController {
    private Random random = new Random();
    private Arena arena;

    public PowerUpController(Arena arena) {
        super(arena);
        this.arena = arena;
    }

    // Handle power-up collection

    public void collectPowerUp(PowerUps powerUp, Player_1 player) {
        // Activate the effect of the power-up
        applyPowerUpEffect(powerUp.getPower(), player);

        // Remove power-up temporarily
        arena.getPowerUp().remove(powerUp);

        // Respawn after 15 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                spawnRandomPowerUp();
            }
        }, 15000);
    }

    // Spawn a new random power-up at a valid position

    private void spawnRandomPowerUp() {
        Position randomPosition = null;

        while (randomPosition == null || !isWallBelow(randomPosition)) {
                // Get a random valid position
            randomPosition = arena.getRandomValidPosition();
        }

        // If a valid position was found, spawn the power-up
        if (randomPosition != null) {
            // Define possible power-up types
            String[] powerUpTypes = {"freeze", "speedUpBall", "slowDownEnemy", "strongerBall", "jumpBoost"};
            String randomPowerType = powerUpTypes[random.nextInt(powerUpTypes.length)];

            // Create a new power-up at the valid position
            PowerUps newPowerUp = new PowerUps(randomPosition.getX(), randomPosition.getY(), randomPowerType);

            // Add the new power-up to the arena
            arena.getPowerUp().add(newPowerUp);
        }
    }
    // Helper method to check if there is a wall below the given position

    private boolean isWallBelow(Position position) {
        int x = position.getX();
        int y = position.getY();

        // Check if the position directly below (y + 1) is a wall
        Position positionBelow = new Position(x, y + 1);
        return arena.isWall(positionBelow); // Assuming arena has a method isWall(Position)
    }

    // Apply the effect of the power-up

    private void applyPowerUpEffect(String type, Player_1 player) {
        switch (type) {
            case "freeze":
                freezeBalls();
                break;
            case "speedUpBall":
                speedUpBalls();
                break;
            case "slowDownEnemy":
                slowDownBalls();
                break;
            case "strongerBall":
                strongerBall();
                break;
            case "jumpBoost":
                player.activateJumpBoost();
                break;
            default:
                break;
        }
    }
    // Apply "freeze" power-up to all balls

    private void freezeBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.freeze(4000); // Freeze each ball for 4 seconds
        }
    }
    private void strongerBall() {
        for (Ball ball : arena.getBalls()) {
            ball.makeStronger(); // Make the ball stronger
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.makeNormal(); // Revert the ball to normal after 4 seconds
                }
            }, 4000); // 4000ms = 4 seconds
        }
    }

    // Apply "speed up" power-up to all balls (with a 4-second timer)
    private void speedUpBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.increaseSpeed(); // Speed up the ball
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.decreaseSpeed(); // Revert the speed after 4 seconds
                }
            }, 8000); // 8000ms = 8 seconds
        }
    }
    // Apply "slow down" power-up to all balls (with a 4-second timer)

    private void slowDownBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.decreaseSpeed(); // Slow down the ball
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.increaseSpeed(); // Revert the speed after 4 seconds
                }
            }, 4000); // 4000ms = 4 seconds
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        // NOTHING
    }
}
