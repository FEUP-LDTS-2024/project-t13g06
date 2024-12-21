package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.states.EndState;

import java.util.List;
import java.util.Set;

public class BallController extends GameController {
    private Boolean lostGame = false;
    private long gameStartTime;

    // Constructor to initialize the ball controller with the arena and game start time
    public BallController(Arena arena, long gameStartTime) {
        super(arena);
        this.gameStartTime = gameStartTime;
    }

    // Moves all balls and handles their interactions with the environment and the player
    public void moveBalls() {
        List<Ball> balls = getModel().getBalls();
        Player player = getModel().getPlayer_1();

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball.isFrozen()) continue;

            int speed = ball.getSpeed();
            Position currentPosition = ball.getPosition();
            Position nextPosition = currentPosition;

            for (int step = 0; step < speed; step++) {
                nextPosition = calculateNextPosition(ball, nextPosition);

                if (getModel().isWall(new Position(nextPosition.getX(), currentPosition.getY()))) {
                    ball.setXDirection(-ball.getXDirection());
                    nextPosition = calculateNextPosition(ball, currentPosition);
                }
                if (getModel().isWall(new Position(currentPosition.getX(), nextPosition.getY()))) {
                    ball.setYDirection(-ball.getYDirection());
                    nextPosition = calculateNextPosition(ball, currentPosition);
                }
                if (getModel().isWall(new Position(nextPosition.getX(), nextPosition.getY()))) {
                    ball.setXDirection(-ball.getXDirection());
                    nextPosition = calculateNextPosition(ball, currentPosition);
                }

                if (nextPosition.equals(player.getPosition())) {
                    int livesLost = ball.isStronger() ? 2 : 1;
                    player.loseLife(livesLost);

                    System.out.println("Player hit! Lives remaining: " + player.getLives());

                    balls.remove(i);
                    i--;

                    if (player.getLives() <= 0) {
                        System.out.println("Game Over! Player has no lives left.");
                        this.lostGame = true;
                    }
                    break;
                }

                for (int j = i + 1; j < balls.size(); j++) {
                    Ball otherBall = balls.get(j);
                    if (nextPosition.equals(calculateNextPosition(otherBall, otherBall.getPosition()))) {
                        handleBallCollision(ball, otherBall);
                    }
                }
            }

            ball.setPosition(nextPosition);
        }
    }

    // Calculates the next position of a ball based on its current direction
    private Position calculateNextPosition(Ball ball, Position currentPosition) {
        return new Position(
                currentPosition.getX() + ball.getXDirection(),
                currentPosition.getY() + ball.getYDirection()
        );
    }

    // Handles collisions between two balls, swapping their directions
    private void handleBallCollision(Ball ball1, Ball ball2) {
        int tempXDirection = ball1.getXDirection();
        int tempYDirection = ball1.getYDirection();

        ball1.setXDirection(ball2.getXDirection());
        ball1.setYDirection(ball2.getYDirection());

        ball2.setXDirection(tempXDirection);
        ball2.setYDirection(tempYDirection);
    }

    // Handles the main game logic for the ball controller in each step
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        moveBalls();
        if (lostGame) {
            long elapsedTime = (System.currentTimeMillis() - gameStartTime) / 1000;

            game.setState(new EndState(new End(elapsedTime), elapsedTime));
        }
    }

    public Boolean getLostGame() {
        return lostGame;
    }
}
