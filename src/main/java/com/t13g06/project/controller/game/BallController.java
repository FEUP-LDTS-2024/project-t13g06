package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player_1;

import java.util.List;

public class BallController extends GameController {
    public BallController(Arena arena) {
        super(arena);
    }

    public void moveBalls() {
        List<Ball> balls = getModel().getBalls();
        Player_1 player = getModel().getPlayer_1();

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball.isFrozen()) continue;

            // Calculate the ball's movement based on its speed
            int speed = ball.getSpeed();
            Position currentPosition = ball.getPosition();
            Position nextPosition = currentPosition;

            for (int step = 0; step < speed; step++) { // Move the ball 'speed' steps
                nextPosition = calculateNextPosition(ball, nextPosition);

                // Check collision with walls
                if (getModel().isWall(new Position(nextPosition.getX(), currentPosition.getY()))) {
                    ball.setXDirection(-ball.getXDirection());
                    nextPosition = calculateNextPosition(ball, currentPosition);
                }
                if (getModel().isWall(new Position(currentPosition.getX(), nextPosition.getY()))) {
                    ball.setYDirection(-ball.getYDirection());
                    nextPosition = calculateNextPosition(ball, currentPosition);
                }

                // Check collision with player
                if (nextPosition.equals(player.getPosition())) {
                    // Player is hit
                    int livesLost = ball.isStronger() ? 2 : 1; // If the ball is stronger, take 2 lives
                    player.loseLife(livesLost); // Decrease life by 1 or 2

                    System.out.println("Player hit! Lives remaining: " + player.getLives());

                    balls.remove(i);
                    i--; // Adjust the index after removing a ball

                    if (player.getLives() <= 0) {
                        System.out.println("Game Over! Player has no lives left.");
                        System.exit(0);
                    }
                    break; // Exit the step loop if the ball hits the player
                }

                // Check ball-to-ball collisions
                for (int j = i + 1; j < balls.size(); j++) {
                    Ball otherBall = balls.get(j);
                    if (nextPosition.equals(otherBall.getPosition())) {
                        handleBallCollision(ball, otherBall);
                    }
                }
            }

            // Update ball's position after all calculations
            ball.setPosition(nextPosition);
        }
    }

    private Position calculateNextPosition(Ball ball, Position currentPosition) {
        return new Position(
                currentPosition.getX() + ball.getXDirection(),
                currentPosition.getY() + ball.getYDirection()
        );
    }

    private void handleBallCollision(Ball ball1, Ball ball2) {
        // Swap directions between the two balls
        int tempXDirection = ball1.getXDirection();
        int tempYDirection = ball1.getYDirection();

        ball1.setXDirection(ball2.getXDirection());
        ball1.setYDirection(ball2.getYDirection());

        ball2.setXDirection(tempXDirection);
        ball2.setYDirection(tempYDirection);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        moveBalls();
    }
}
