package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
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

    // Handle power-up collection and apply its effect
    public void collectPowerUp(PowerUps powerUp, Player player) {
        applyPowerUpEffect(powerUp.getPower(), player);
        arena.getPowerUp().remove(powerUp); // Temporarily remove the power-up

        // Respawn power-up after 15 seconds
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

        // Ensure the position is valid and has a wall below
        while (randomPosition == null || !isWallBelow(randomPosition)) {
            randomPosition = arena.getRandomValidPosition();
        }

        if (randomPosition != null) {
            String[] powerUpTypes = {"freeze", "speedUpBall", "slowDownEnemy", "strongerBall", "jumpBoost"};
            String randomPowerType = powerUpTypes[random.nextInt(powerUpTypes.length)];
            PowerUps newPowerUp = new PowerUps(randomPosition.getX(), randomPosition.getY(), randomPowerType);
            arena.getPowerUp().add(newPowerUp);
        }
    }

    // Check if there is a wall below the given position
    private boolean isWallBelow(Position position) {
        Position positionBelow = new Position(position.getX(), position.getY() + 1);
        return arena.isWall(positionBelow);
    }

    // Apply the effect of a power-up to the player or arena
    private void applyPowerUpEffect(String type, Player player) {
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

    // Freeze all balls for 4 seconds
    private void freezeBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.freeze(4000);
        }
    }

    // Make all balls stronger for 4 seconds
    private void strongerBall() {
        for (Ball ball : arena.getBalls()) {
            ball.makeStronger();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.makeNormal();
                }
            }, 4000);
        }
    }

    // Speed up all balls for 8 seconds
    private void speedUpBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.increaseSpeed();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.decreaseSpeed();
                }
            }, 8000);
        }
    }

    // Slow down all balls for 4 seconds
    private void slowDownBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.decreaseSpeed();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.increaseSpeed();
                }
            }, 4000);
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        // No specific action is taken during step for now
    }
}
