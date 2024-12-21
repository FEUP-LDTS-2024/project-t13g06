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
    public void testMoveBalls_BallHitsWall() {
        Ball ball = new Ball(1, 1);
        balls.add(ball);

        when(mockArena.isWall(new Position(2, 1))).thenReturn(true);

        ballController.moveBalls();

        assertEquals(-1, ball.getXDirection());
        assertEquals(0, ball.getPosition().getX());
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
