package com.t13g06.project.states;

import com.t13g06.project.Game;
import com.t13g06.project.controller.game.ArenaController;
import com.t13g06.project.controller.game.GameController;
import com.t13g06.project.controller.menu.MenuController;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.viewer.game.GameViewer;
import com.t13g06.project.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {
    Arena arena;
    GameState gameState;

    @BeforeEach
    void setup() {
        arena = Mockito.mock(Arena.class);
        gameState = new GameState(arena);

    }

    @Test
    void MainContent() {
        assertEquals(arena, gameState.getModel());
        assertEquals(GameViewer.class, gameState.getViewer().getClass());
        assertEquals(ArenaController.class, gameState.getController().getClass());
    }
}
