package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.states.EndState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BallControllerTest {

    private Arena mockArena;
    private Game mockGame;
    private Player mockPlayer;
    private BallController ballController;
    private List<Ball> balls;
    private long gameStartTime;

    @BeforeEach
    public void setUp() {
        mockArena = mock(Arena.class);
        mockGame = mock(Game.class);
        mockPlayer = mock(Player.class);
        balls = new ArrayList<>();

        when(mockArena.getBalls()).thenReturn(balls);
        when(mockArena.getPlayer_1()).thenReturn(mockPlayer);

        gameStartTime = System.currentTimeMillis();
        ballController = new BallController(mockArena, gameStartTime);
    }

    @Test
    public void testWallCollision() {
        Ball ball = new Ball(1, 1);
        balls.add(ball);

        when(mockArena.isWall(new Position(2, 1))).thenReturn(true);

        ballController.moveBalls();

        assertEquals(-1, ball.getXDirection());
        assertEquals(0, ball.getPosition().getX());
    }

    @Test
    public void testBallFrozen() {
        Ball ball = mock(Ball.class);
        balls.add(ball);

        when(ball.isFrozen()).thenReturn(true);

        ballController.moveBalls();
        verify(ball, never()).setPosition(any(Position.class));
    }

    @Test
    public void testBallCollision() {
        Ball mockBall1 = mock(Ball.class);
        Ball mockBall2 = mock(Ball.class);
        balls.add(mockBall1);
        balls.add(mockBall2);

        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        when(mockBall1.getPosition()).thenReturn(pos1);
        when(mockBall2.getPosition()).thenReturn(pos2);
        when(mockBall1.getXDirection()).thenReturn(1);
        when(mockBall2.getXDirection()).thenReturn(-1);
        when(mockArena.isWall(any(Position.class))).thenReturn(false);

        ballController.moveBalls();

        verify(mockBall1, atLeastOnce()).setPosition(any(Position.class));
        verify(mockBall2, atLeastOnce()).setPosition(any(Position.class));
    }



    @Test
    public void testStep_NoGameOver() {
        Ball ball = new Ball(1, 1);
        balls.add(ball);

        when(mockPlayer.getPosition()).thenReturn(new Position(10, 10));
        when(mockPlayer.getLives()).thenReturn(3);

        ballController.step(mockGame, new HashSet<>(), System.currentTimeMillis());

        verify(mockGame, never()).setState(any(EndState.class));
    }
}
