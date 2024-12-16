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
        Position randomPosition = getRandomValidPosition();
        if (randomPosition != null) {
            String[] powerUpTypes = {"freeze", "speedUpBall", "slowDownEnemy", "strongerBall", "jumpBoost"};
            String randomPowerType = powerUpTypes[random.nextInt(powerUpTypes.length)];
            PowerUps newPowerUp = new PowerUps(randomPosition.getX(), randomPosition.getY(), randomPowerType);
            arena.getPowerUp().add(newPowerUp);
        }
    }

    // Find a random valid position for spawning power-ups
    private Position getRandomValidPosition() {
        int width = arena.getWidth();
        int height = arena.getHeight();
        int maxAttempts = 100;

        for (int i = 0; i < maxAttempts; i++) {
            Position pos = new Position(random.nextInt(width), random.nextInt(height));
            if (arena.isEmpty(pos) && !pos.equals(arena.getBall().getPosition()) &&
                    !pos.equals(arena.getPlayer_1().getPosition()) &&
                    !pos.equals(arena.getPlayer_2().getPosition())) {
                return pos;
            }
        }
        return null; // If no valid position is found after multiple attempts
    }

    // Apply the effect of the power-up
    private void applyPowerUpEffect(String type, Player_1 player) {
        switch (type) {
            case "freeze":
                freezeBall();
                break;
            case "speedUpBall":
                speedUpBall();
                break;
            case "slowDownEnemy":
                slowDownBall();
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

    private void freezeBall() {
        Ball ball = arena.getBall();
        ball.freeze(4000); // Freeze ball for 4 seconds
    }

    private void speedUpBall() {
        Ball ball = arena.getBall();
        ball.increaseSpeed();
    }

    private void slowDownBall() {
        Ball ball = arena.getBall();
        ball.decreaseSpeed();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        // NOTHING
    }
}
