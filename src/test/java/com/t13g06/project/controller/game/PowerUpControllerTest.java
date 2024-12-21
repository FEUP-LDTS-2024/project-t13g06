package com.t13g06.project.controller.game;

import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.model.game.elements.PowerUps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerUpControllerTest {
    private Arena arena;
    private PowerUpController powerUpController;
    private Player player;
    private Ball ball;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        powerUpController = new PowerUpController(arena);
        player = mock(Player.class);
        ball = mock(Ball.class);
    }

    @Test
    void testCollectPowerUpFreeze() {
        PowerUps powerUp = new PowerUps(5, 5, "freeze");
        List<PowerUps> powerUps = new ArrayList<>();
        powerUps.add(powerUp);

        when(arena.getPowerUp()).thenReturn(powerUps);
        when(arena.getBalls()).thenReturn(new ArrayList<>());

        powerUpController.collectPowerUp(powerUp, player);

        assertFalse(powerUps.contains(powerUp));
        verify(arena).getPowerUp();
    }

    @Test
    void testCollectPowerUpSpeedUpBall() {
        PowerUps powerUp = new PowerUps(5, 5, "speedUpBall");
        List<PowerUps> powerUps = new ArrayList<>();
        powerUps.add(powerUp);

        when(arena.getPowerUp()).thenReturn(powerUps);
        when(arena.getBalls()).thenReturn(new ArrayList<>());

        powerUpController.collectPowerUp(powerUp, player);

        assertFalse(powerUps.contains(powerUp));
        verify(arena).getPowerUp();
    }

    @Test
    void testCollectPowerUpSlowDownEnemy() {
        PowerUps powerUp = new PowerUps(5, 5, "slowDownEnemy");
        List<PowerUps> powerUps = new ArrayList<>();
        powerUps.add(powerUp);

        when(arena.getPowerUp()).thenReturn(powerUps);
        when(arena.getBalls()).thenReturn(new ArrayList<>());

        powerUpController.collectPowerUp(powerUp, player);

        assertFalse(powerUps.contains(powerUp));
        verify(arena).getPowerUp();
    }

    @Test
    void testCollectPowerUpStrongerBall() {
        PowerUps powerUp = new PowerUps(5, 5, "strongerBall");
        List<PowerUps> powerUps = new ArrayList<>();
        powerUps.add(powerUp);

        when(arena.getPowerUp()).thenReturn(powerUps);
        when(arena.getBalls()).thenReturn(new ArrayList<>());

        powerUpController.collectPowerUp(powerUp, player);

        assertFalse(powerUps.contains(powerUp));
        verify(arena).getPowerUp();
    }

    @Test
    void testCollectPowerUpJumpBoost() {
        PowerUps powerUp = new PowerUps(5, 5, "jumpBoost");
        List<PowerUps> powerUps = new ArrayList<>();
        powerUps.add(powerUp);

        when(arena.getPowerUp()).thenReturn(powerUps);
        when(arena.getBalls()).thenReturn(new ArrayList<>());

        powerUpController.collectPowerUp(powerUp, player);

        assertFalse(powerUps.contains(powerUp));
        verify(arena).getPowerUp();
    }

}
