package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;

import java.io.IOException;
import java.util.Set;

public class ArenaController extends GameController {
    private final PlayerController player_1_controller;
    private final BallController ballController;
    private final PowerUpController powerUpController;

    private long lastSpawnTime;
    private int countdown = 5;
    private int iterations = 1;
    private long gameStartTime;

    // Constructor to initialize the arena controller and sub-controllers
    public ArenaController(Arena arena) {
        super(arena);
        this.gameStartTime = System.currentTimeMillis();
        this.player_1_controller = new PlayerController(arena);
        this.ballController = new BallController(arena, gameStartTime);
        this.powerUpController = new PowerUpController(arena);
        this.lastSpawnTime = System.currentTimeMillis();
    }

    // Returns the game start time
    public long getGameStartTime() {
        return gameStartTime;
    }

    // Handles the logic for spawning new balls based on elapsed time
    private void handleBallSpawning() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastSpawnTime) / 1000;

        int waitTime = 5 + iterations;
        countdown = waitTime - (int) elapsedTime;

        if (elapsedTime >= waitTime) {
            spawnNewBall();
            lastSpawnTime = currentTime;
            iterations++;
        }
    }

    // Spawns a new ball at a random valid position in the arena
    private void spawnNewBall() {
        Arena arena = getModel();
        Position newBallPosition = arena.getRandomValidPosition();
        if (newBallPosition != null) {
            Ball newBall = new Ball(newBallPosition.getX(), newBallPosition.getY());
            arena.addBall(newBall);
        }
    }

    // Returns the current countdown for the next ball spawn
    public int getCountdown() {
        return countdown;
    }

    // Handles the main game logic for the arena in each step
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        player_1_controller.step(game, actionSet, time);
        ballController.step(game, actionSet, time);
        powerUpController.step(game, actionSet, time);
        handleBallSpawning();
    }

    public BallController getBallController() {
        return ballController;
    }

    public PlayerController getPlayer_1_controller() {
        return player_1_controller;
    }

    public PowerUpController getPowerUpController() {
        return powerUpController;
    }

    public long getLastSpawnTime() {
        return lastSpawnTime;
    }

    public int getIterations() {
        return iterations;
    }
}
