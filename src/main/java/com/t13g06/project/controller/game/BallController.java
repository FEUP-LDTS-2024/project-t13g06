package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player_1;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.states.EndState;
import com.t13g06.project.states.MenuState;

import java.util.List;
import java.util.Set;

public class BallController extends GameController {
    private Boolean lostGame = false;
    private long gameStartTime; // Add a field for gameStartTime

    public BallController(Arena arena, long gameStartTime) {
        super(arena);
        this.gameStartTime = gameStartTime; // Initialize it
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
                // Check collision with walls
                if (getModel().isWall(new Position(nextPosition.getX(), nextPosition.getY()))) {
                    ball.setXDirection(-ball.getXDirection());
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
                        this.lostGame = true;
                    }
                    break; // Exit the step loop if the ball hits the player
                }

                // Check ball-to-ball collisions
                for (int j = i + 1; j < balls.size(); j++) {
                    Ball otherBall = balls.get(j);
                    if (nextPosition.equals(calculateNextPosition(otherBall,otherBall.getPosition()))) {
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
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        moveBalls(); // Simply move the balls regardless of actions
        if (lostGame) {
            long elapsedTime = (System.currentTimeMillis() - gameStartTime) / 1000; // Calculate elapsed time in seconds
            game.setState(new EndState(new End(), elapsedTime)); // Pass the elapsed time to EndState
        }
    }

}
