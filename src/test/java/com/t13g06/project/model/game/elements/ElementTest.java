package com.t13g06.project.model.game.elements;

import com.t13g06.project.model.Position;
import net.jqwik.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {

    @Property
    void testElementPositionInitialization(@ForAll int x, @ForAll int y) {

        Element element = new Element(x, y);
        Position position = element.getPosition();
        assertEquals(x, position.getX());
        assertEquals(y, position.getY());
    }

    @Property
    void testSetPositionUpdatesCorrectly(@ForAll int newX, @ForAll int newY) {
        Element element = new Element(0, 0);
        element.setPosition(new Position(newX, newY));
        Position position = element.getPosition();
        assertEquals(newX, position.getX());
        assertEquals(newY, position.getY());
    }

    @Property
    void testElementPositionInvariant(@ForAll int x, @ForAll int y) {
        Element element = new Element(x, y);
        Position position = element.getPosition();
        assertNotNull(position);
        assertTrue(position.getX() >= Integer.MIN_VALUE && position.getX() <= Integer.MAX_VALUE);
        assertTrue(position.getY() >= Integer.MIN_VALUE && position.getY() <= Integer.MAX_VALUE);
    }
}
