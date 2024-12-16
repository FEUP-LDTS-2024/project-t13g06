package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import java.util.Random;

public class BallController extends GameController {
    private int xDirection; // Horizontal movement direction (-1 or 1)
    private int yDirection; // Vertical movement direction (-1 or 1)

    public BallController(Arena arena) {
        super(arena);
        initializeBallDirection();
    }

    // Initialize ball movement in a random diagonal direction
    private void initializeBallDirection() {
        Random random = new Random();
        xDirection = random.nextBoolean() ? 1 : -1; // Randomly choose left or right
        yDirection = random.nextBoolean() ? 1 : -1; // Randomly choose up or down
    }

    public void moveBall() {
        Position currentPosition = getModel().getBall().getPosition();
        Position nextPosition = new Position(
                currentPosition.getX() + xDirection,
                currentPosition.getY() + yDirection
        );

        boolean hitWallX = getModel().isWall(new Position(nextPosition.getX(), currentPosition.getY()));
        boolean hitWallY = getModel().isWall(new Position(currentPosition.getX(), nextPosition.getY()));

        // Reflect directions based on wall collisions
        if (hitWallX) {
            xDirection *= -1; // Reflect horizontal direction
        }
        if (hitWallY) {
            yDirection *= -1; // Reflect vertical direction
        }

        // If both directions are blocked (corner case), reverse both directions
        if (hitWallX && hitWallY) {
            xDirection *= -1;
            yDirection *= -1;
        }

        // Recalculate next position after reflecting if needed
        nextPosition = new Position(
                currentPosition.getX() + xDirection,
                currentPosition.getY() + yDirection
        );

        // If the recalculated position is still a wall, re-initialize ball direction
        if (getModel().isWall(nextPosition)) {
            initializeBallDirection();
        } else {
            // Move the ball if the next position is not a wall
            getModel().getBall().setPosition(nextPosition);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        // Move the ball on every step
        moveBall();
    }
}
