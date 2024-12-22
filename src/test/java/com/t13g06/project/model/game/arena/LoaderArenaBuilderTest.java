package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.game.elements.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LoaderArenaBuilderTest {


    @Test
    void testConstructorWithValidFile() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        assertNotNull(builder);
    }

    @Test
    void testConstructorThrowsIOExceptionWhenFileNotFound() {
        assertThrows(IOException.class, () -> {
            new LoaderArenaBuilder(999);
        });
    }

    @Test
    void testGetWidth() throws IOException {

        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        assertEquals(53, builder.getWidth());
    }

    @Test
    void testGetHeight() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        assertEquals(20, builder.getHeight());
    }

    @Test
    void testCreateWalls() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Wall> walls = builder.createWalls();
        assertNotNull(walls);
        assertEquals(186, walls.size());
    }

    @Test
    void testCreatePowerUp() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<PowerUps> powerUps = builder.createPowerUp();
        assertNotNull(powerUps);
        assertEquals(5, powerUps.size());
        assertEquals("jumpBoost", powerUps.get(0).getPower());
        assertEquals("speedUpBall", powerUps.get(1).getPower());
        assertEquals("slowDownEnemy", powerUps.get(2).getPower());
        assertEquals("freeze", powerUps.get(3).getPower());
        assertEquals("strongerBall", powerUps.get(4).getPower());
    }

    @Test
    void testCreatePlayer_1() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        Player player = builder.createPlayer_1();
        assertNotNull(player);
    }

    @Test
    void testCreateBall() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<Ball> balls = builder.createBall();
        assertNotNull(balls);
        assertEquals(1, balls.size());
    }
}
