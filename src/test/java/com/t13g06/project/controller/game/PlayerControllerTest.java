package com.t13g06.project.controller.game;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.model.game.elements.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class PlayerControllerTest {

    private PlayerController playerController;
    private Arena mockArena;
    private Player mockPlayer;
    private Game mockGame;

    @BeforeEach
    void setUp() {
        mockArena = mock(Arena.class);
        mockPlayer = mock(Player.class);
        mockGame = mock(Game.class);

        Position initialPosition = new Position(5, 5);
        when(mockPlayer.getPosition()).thenReturn(initialPosition);
        when(mockArena.getPlayer_1()).thenReturn(mockPlayer);
        when(mockArena.isEmpty(any(Position.class))).thenReturn(true);

        playerController = spy(new PlayerController(mockArena));  // Spy on PlayerController
    }

    @Test
    void testApplyGravityStopsAtWall() {
        when(mockArena.isEmpty(any(Position.class))).thenReturn(false);
        playerController.applyGravity();
        verify(mockPlayer, never()).setPosition(any());
    }

    @Test
    void testJumpIncrementsPosition() {
        when(mockPlayer.isJumping()).thenReturn(true);
        when(mockPlayer.getJumpProgress()).thenReturn(1);
        when(mockPlayer.getJumpHeight()).thenReturn(3);

        playerController.jump();
        verify(mockPlayer).setPosition(new Position(5, 4));
        verify(mockPlayer).setJumpProgress(2);
    }

    @Test
    void testJumpEnds() {
        when(mockPlayer.isJumping()).thenReturn(true);
        when(mockPlayer.getJumpProgress()).thenReturn(3);
        when(mockPlayer.getJumpHeight()).thenReturn(3);

        playerController.jump();
        verify(mockPlayer).resetJump();
    }

    @Test
    void testStartJump() {
        when(mockArena.isEmpty(any(Position.class))).thenReturn(false);
        playerController.startJump();
        verify(mockPlayer).setJumping(true);
        verify(mockPlayer).setJumpProgress(0);
    }

    @Test
    void testMoveLeft() {
        Position leftPosition = new Position(4, 5);
        when(mockArena.isEmpty(leftPosition)).thenReturn(true);

        playerController.moveHeroLeft();
        verify(mockPlayer).setPosition(leftPosition);
    }

    @Test
    void testMoveLeftBlockedByWall() {
        Position leftPosition = new Position(4, 5);
        when(mockArena.isEmpty(leftPosition)).thenReturn(false);

        playerController.moveHeroLeft();
        verify(mockPlayer, never()).setPosition(leftPosition);
    }

    @Test
    void testMoveRight() {
        Position rightPosition = new Position(6, 5);
        when(mockArena.isEmpty(rightPosition)).thenReturn(true);

        playerController.moveHeroRight();
        verify(mockPlayer).setPosition(rightPosition);
    }

    @Test
    void testMoveRightBlockedByWall() {
        Position rightPosition = new Position(6, 5);
        when(mockArena.isEmpty(rightPosition)).thenReturn(false);

        playerController.moveHeroRight();
        verify(mockPlayer, never()).setPosition(rightPosition);
    }

    @Test
    void testApplyGravityWhenNotJumpingAndEmptyBelow() {
        Position currentPosition = new Position(5, 5);
        Position belowPosition = new Position(5, 6);
        when(mockPlayer.getPosition()).thenReturn(currentPosition);
        when(mockArena.isEmpty(belowPosition)).thenReturn(true);
        when(mockPlayer.isJumping()).thenReturn(false);
        when(mockPlayer.getVerticalSpeed()).thenReturn(0.0);

        playerController.applyGravity();

        verify(mockPlayer).setVerticalSpeed(0.5);
        verify(mockPlayer).setPosition(new Position(5, 6));
    }

    @Test
    void testStepWithActions() {
        Set<GUI.ACTION> actionSet = new HashSet<>();
        actionSet.add(GUI.ACTION.LEFT);
        actionSet.add(GUI.ACTION.UP);

        playerController.step(mockGame, actionSet, 1000L);

        verify(playerController).applyGravity();
        verify(mockPlayer).setPosition(new Position(4, 5));
    }
}
