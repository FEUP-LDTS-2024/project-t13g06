package com.t13g06.project.model.game.arena;

import static org.junit.jupiter.api.Assertions.*;

import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player;
import com.t13g06.project.model.game.elements.PowerUps;
import com.t13g06.project.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaTest {
    private Arena arena;
    private Player player;
    private Ball ball;
    private PowerUps powerUp;
    private Wall wall;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
        player = new Player(1, 1);
        ball = new Ball(2, 2);
        powerUp = new PowerUps(3, 3, "freezing");
        wall = new Wall(4, 4);

        arena.setPlayer_1(player);
        arena.setBalls(new ArrayList<>(Arrays.asList(ball)));
        arena.setPowerUp(Arrays.asList(powerUp));
        arena.setWalls(Arrays.asList(wall));
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(10, arena.getWidth());
        assertEquals(10, arena.getHeight());
        assertNotNull(arena.getPlayer_1());
        assertEquals(player, arena.getPlayer_1());
    }

    @Test
    public void testAddBall() {
        Ball newBall = new Ball(5, 5);
        arena.addBall(newBall);
        assertTrue(arena.getBalls().contains(newBall));
    }

    @Test
    public void testIsWall() {
        assertTrue(arena.isWall(new Position(4, 4)));
        assertFalse(arena.isWall(new Position(0, 0)));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(arena.isEmpty(new Position(4, 4)));
        assertTrue(arena.isEmpty(new Position(0, 0)));
    }

    @Test
    public void testIsPowerUp() {
        assertTrue(arena.isPowerUp(new Position(3, 3)));
        assertFalse(arena.isPowerUp(new Position(0, 0)));
    }

    @Test
    public void testGetRandomValidPosition() {
        Position pos = arena.getRandomValidPosition();
        assertNotNull(pos);
        assertTrue(arena.isEmpty(pos));
        assertNotEquals(pos, player.getPosition());
    }

    @Test
    public void testGetPowerUps() {
        List<PowerUps> expectedPowerUps = Arrays.asList(powerUp);

        List<PowerUps> actualPowerUps = arena.getPowerUp();

        assertNotNull(actualPowerUps);
        assertEquals(expectedPowerUps.size(), actualPowerUps.size());
        assertTrue(actualPowerUps.containsAll(expectedPowerUps));
    }

    @Test
    public void testGetWalls() {
        List<Wall> expectedWalls = Arrays.asList(wall);

        List<Wall> actualWalls = arena.getWalls();

        assertNotNull(actualWalls);
        assertEquals(expectedWalls.size(), actualWalls.size());
        assertTrue(actualWalls.containsAll(expectedWalls));
    }


}
