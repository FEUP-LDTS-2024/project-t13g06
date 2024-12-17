package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.controller.game.GameController;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player_1;
import com.t13g06.project.model.game.elements.PowerUps;

import java.util.Random;
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
        Position randomPosition = arena.getRandomValidPosition();
        if (randomPosition != null) {
            String[] powerUpTypes = {"freeze", "speedUpBall", "slowDownEnemy", "strongerBall", "jumpBoost"};
            String randomPowerType = powerUpTypes[random.nextInt(powerUpTypes.length)];
            PowerUps newPowerUp = new PowerUps(randomPosition.getX(), randomPosition.getY(), randomPowerType);
            arena.getPowerUp().add(newPowerUp);
        }
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
                // You can implement logic for stronger ball later
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

    // Apply "speed up" power-up to all balls
    private void speedUpBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.increaseSpeed();
        }
    }

    // Apply "slow down" power-up to all balls
    private void slowDownBalls() {
        for (Ball ball : arena.getBalls()) {
            ball.decreaseSpeed();
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        // NOTHING
    }
}
