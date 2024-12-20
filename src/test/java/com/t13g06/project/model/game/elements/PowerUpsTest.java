package com.t13g06.project.model.game.elements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerUpsTest {
    private PowerUps powerUp;

    @BeforeEach
    void setUp() {
        powerUp = new PowerUps(10, 20, "SpeedBoost");
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(10, powerUp.getPosition().getX());
        assertEquals(20, powerUp.getPosition().getY());
        assertEquals("SpeedBoost", powerUp.getPower());
    }

    @Test
    void testGetPower() {
        assertEquals("SpeedBoost", powerUp.getPower());
    }

    @Test
    void testBoundaryX() {
        PowerUps edgeCase = new PowerUps(0, 20, "SpeedBoost");
        assertEquals(0, edgeCase.getPosition().getX());
    }

    @Test
    void testBoundaryY() {
        PowerUps edgeCase = new PowerUps(10, Integer.MAX_VALUE, "SpeedBoost");
        assertEquals(Integer.MAX_VALUE, edgeCase.getPosition().getY());
    }

    @Test
    void testNullPower() {
        PowerUps edgeCase = new PowerUps(10, 20, null);
        assertNull(edgeCase.getPower());
    }
}
