package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.PowerUps;

import java.util.List;
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

    public void moveBalls() {
        List<Ball> balls = getModel().getBalls();

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball.isFrozen()) continue;

            Position currentPosition = ball.getPosition();
            Position nextPosition = new Position(
                    currentPosition.getX() + ball.getXDirection(),
                    currentPosition.getY() + ball.getYDirection()
            );

            boolean hitWallX = getModel().isWall(new Position(nextPosition.getX(), currentPosition.getY()));
            boolean hitWallY = getModel().isWall(new Position(currentPosition.getX(), nextPosition.getY()));

            // Reflect directions based on wall collisions
            if (hitWallX) ball.setXDirection(-ball.getXDirection());
            if (hitWallY) ball.setYDirection(-ball.getYDirection());

            // Handle corner case
            if (hitWallX && hitWallY) {
                ball.setXDirection(-ball.getXDirection());
                ball.setYDirection(-ball.getYDirection());
            }

            // Update the ball's next position after wall reflection
            nextPosition = new Position(
                    currentPosition.getX() + ball.getXDirection(),
                    currentPosition.getY() + ball.getYDirection()
            );

            // Check for collisions with other balls
            for (int j = 0; j < balls.size(); j++) {
                if (i == j) continue; // Skip self

                Ball otherBall = balls.get(j);
                Position otherPosition = otherBall.getPosition();

                // Check if the two balls are colliding
                if (nextPosition.equals(otherPosition)) {
                    handleBallCollision(ball, otherBall); // Handle collision response
                }
            }

            // If recalculated position is invalid, reinitialize direction
            if (getModel().isWall(nextPosition)) {
                ball.initializeBallDirection();
            } else {
                ball.setPosition(nextPosition); // Move ball
            }
        }
    }

    private void handleBallCollision(Ball ball1, Ball ball2) {
        // Simple collision response: Swap directions
        int tempXDirection = ball1.getXDirection();
        int tempYDirection = ball1.getYDirection();

        ball1.setXDirection(ball2.getXDirection());
        ball1.setYDirection(ball2.getYDirection());

        ball2.setXDirection(tempXDirection);
        ball2.setYDirection(tempYDirection);
    }




    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (!getModel().getBalls().get(0).isFrozen()) {
            moveBalls();
        }
    }
}
