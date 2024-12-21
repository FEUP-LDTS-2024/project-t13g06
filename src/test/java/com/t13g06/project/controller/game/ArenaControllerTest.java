package com.t13g06.project.controller.game;

import com.t13g06.project.controller.game.ArenaController;
import com.t13g06.project.controller.game.PlayerController;
import com.t13g06.project.controller.game.BallController;
import com.t13g06.project.controller.game.PowerUpController;
import com.t13g06.project.model.game.arena.Arena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaControllerTest {

    private ArenaController arenaController;
    private Arena mockArena;

    @BeforeEach
    void setUp() {
        mockArena = Mockito.mock(Arena.class);
        arenaController = new ArenaController(mockArena);
    }

    @Test
    void testConstructor() {
        assertNotNull(arenaController);
        long currentTime = System.currentTimeMillis();
        assertEquals(5, arenaController.getCountdown());
        assertEquals(1, arenaController.getIterations());
        assertTrue(Math.abs(arenaController.getGameStartTime() - currentTime) < 1000);
        assertNotNull(arenaController.getPlayer_1_controller());
        assertNotNull(arenaController.getBallController());
        assertNotNull(arenaController.getPowerUpController());
    }
}
