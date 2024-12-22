package com.t13g06.project.model.game.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(0, 0);
    }

    @Test
    void testInitializeBallDirection() {
        ball.initializeBallDirection();
        assertTrue(ball.getXDirection() == 1 || ball.getXDirection() == -1);
        assertTrue(ball.getYDirection() == 1 || ball.getYDirection() == -1);
    }

    @Test
    void testResetDirection() {
        Ball mockball = Mockito.spy(new Ball(1,1));
        mockball.resetDirection();
        Mockito.verify(mockball, Mockito.times(1)).initializeBallDirection();
    }

    @Test
    void testFreeze() throws InterruptedException {
        ball.freeze(1000);
        assertTrue(ball.isFrozen());

        Thread.sleep(1100);

        assertFalse(ball.isFrozen());
    }

    @Test
    void testHandleHit() {
        ball.handleHit();
        assertTrue(ball.isHit());
        assertEquals("red", ball.getColor());
    }

    @Test
    void testToggleColor() {
        ball.handleHit();
        assertEquals("red", ball.getColor());
    }

    @Test
    void testMakeStronger() {
        ball.makeStronger();
        assertTrue(ball.isStronger());
    }

    @Test
    void testMakeNormal() {
        ball.makeStronger();
        ball.makeNormal();
        assertFalse(ball.isStronger());
    }

    @Test
    void testIncreaseSpeed() {
        ball.increaseSpeed();
        assertEquals(2, ball.getSpeed());

        for (int i = 0; i < 10; i++) {
            ball.increaseSpeed();
        }
        assertEquals(10, ball.getSpeed());
    }

    @Test
    void testDecreaseSpeed() {
        ball.decreaseSpeed();
        assertEquals(1, ball.getSpeed());
    }

    @Test
    void testIsHit() {
        assertFalse(ball.isHit());
        ball.setHit(true);
        assertTrue(ball.isHit());
    }

    @Test
    void testGetterSetterMethods() {
        ball.setXDirection(5);
        ball.setYDirection(-3);

        assertEquals(5, ball.getXDirection());
        assertEquals(-3, ball.getYDirection());
    }
}
