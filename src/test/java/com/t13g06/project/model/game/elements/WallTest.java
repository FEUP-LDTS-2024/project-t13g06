package com.t13g06.project.model.game.elements;

import com.t13g06.project.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private Wall wall;

    @BeforeEach
    void setUp() {
        wall = new Wall(5, 10);
    }

    @Test
    void testWallConstructor() {
        Position position = wall.getPosition();
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    void testWallPositionSetter() {
        int newX = 20;
        int newY = 30;
        wall.setPosition(new Position(newX, newY));

        Position position = wall.getPosition();
        assertEquals(newX, position.getX());
        assertEquals(newY, position.getY());
    }

    @Test
    void testWallPositionInitialization() {
        Position position = wall.getPosition();
        assertNotNull(position);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }
}
