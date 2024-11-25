package com.t13g06.project.model;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Property
    void getLeft(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position left = position.getLeft();

        assertEquals(x - 1, left.getX());
        assertEquals(y, left.getY());
    }

    @Property
    void getRight(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position right = position.getRight();

        assertEquals(x + 1, right.getX());
        assertEquals(y, right.getY());
    }

    @Property
    void getUp(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position up = position.getUp();

        assertEquals(x, up.getX());
        assertEquals(y - 1, up.getY());
    }

    @Property
    void getDown(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position down = position.getDown();

        assertEquals(x, down.getX());
        assertEquals(y + 1, down.getY());
    }

    @Property
    void getRandomNeighbour(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Map<Position, Integer> neighborCounts = new HashMap<>();
        Set<Position> validNeighbors = Set.of(
                position.getUp(),
                position.getRight(),
                position.getDown(),
                position.getLeft()
        );

        for (int i = 0; i < 1000; i++) {
            Position neighbor = position.getRandomNeighbour();
            assertTrue(validNeighbors.contains(neighbor));
            neighborCounts.put(neighbor, neighborCounts.getOrDefault(neighbor, 0) + 1);
        }

        assertEquals(4, neighborCounts.size());
    }

    @Test
    void edgeCaseTests() {
        Position max = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE - 1, max.getLeft().getX());
        assertEquals(Integer.MAX_VALUE, max.getLeft().getY());

        Position min = new Position(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE + 1, min.getRight().getX());
        assertEquals(Integer.MIN_VALUE, min.getRight().getY());

        Position zero = new Position(0, 0);
        assertEquals(-1, zero.getLeft().getX());
        assertEquals(1, zero.getRight().getX());
    }

    @Test
    void equalsAndHashCode() {
        Position position1 = new Position(3, 5);
        Position position2 = new Position(3, 5);
        Position position3 = new Position(4, 6);

        assertEquals(position1, position2);
        assertEquals(position1.hashCode(), position2.hashCode());

        assertNotEquals(position1, position3);
        assertNotEquals(null, position1);
        assertNotEquals(new Object(), position1);
    }

    @Test
    void immutabilityTest() {
        Position position = new Position(3, 5);
        Position left = position.getLeft();

        assertEquals(3, position.getX());
        assertEquals(5, position.getY());

        assertEquals(2, left.getX());
        assertEquals(5, left.getY());
    }

    @Property
    void moveBackAndForth(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);

        assertEquals(position, position.getLeft().getRight());
        assertEquals(position, position.getRight().getLeft());
        assertEquals(position, position.getUp().getDown());
        assertEquals(position, position.getDown().getUp());
    }
}
