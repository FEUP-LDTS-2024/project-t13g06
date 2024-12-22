package com.t13g06.project.model.game.elements;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(10, 20);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals(3, player.getLives());
        assertFalse(player.isJumping());
        assertEquals(0, player.getJumpProgress());
        assertEquals(5, player.getJumpHeight());
        assertEquals(0.0, player.getVerticalSpeed(), 0.01);
    }

    @Test
    public void testLoseLife() {
        player.setLives(1);
        player.loseLife(1);
        assertEquals(0, player.getLives());
    }

    @Test
    public void testLoseLifeEdge() {
        player.setLives(0);
        player.loseLife(1);
        assertEquals(0, player.getLives());
    }

    @Test
    public void testLoseLifeBigValues() {
        player.setLives(1);
        player.loseLife(5);
        assertEquals(0, player.getLives());
    }

    @Test
    public void testJumpingState() {
        assertFalse(player.isJumping());

        player.setJumping(true);
        assertTrue(player.isJumping());

        player.resetJump();
        assertFalse(player.isJumping());
        assertEquals(0, player.getJumpProgress());
    }

    @Test
    public void testVerticalSpeed() {
        player.setVerticalSpeed(5.0);
        assertEquals(5.0, player.getVerticalSpeed(), 0.01);
    }

    @Test
    public void testSetJumpProgress() {
        player.setJumpProgress(5);
        assertEquals(5, player.getJumpProgress());
    }

    @Test
    public void testActivateJumpBoost() {
        int initialjumpheight = player.getJumpHeight();
        player.activateJumpBoost();
        assertEquals(initialjumpheight + 3, player.getJumpHeight());
    }

    @Test
    public void testActivateJumpBoostImmediateReset() throws InterruptedException {
        Player spyPlayer = spy(player);

        spyPlayer.activateJumpBoost();
        Thread.sleep(4100);

        verify(spyPlayer).setJumpHeightStart();
    }


    @Test
    public void testEdgeCases() {
        player.setJumping(true);
        player.resetJump();
        assertFalse(player.isJumping());
        assertEquals(0, player.getJumpProgress());
    }


}
