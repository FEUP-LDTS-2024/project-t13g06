package com.t13g06.project.model.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderBoardTest {
    private LeaderBoard leaderBoard;

    @BeforeEach
    public void setUp() {
        leaderBoard = new LeaderBoard();
    }

    @Test
    public void testConstructor() {
        assertEquals(1, leaderBoard.getNumberEntries());
    }

    @Test
    public void testGetEntry() {
        assertEquals("Go Back", leaderBoard.getEntry(0));
    }

    @Test
    public void testIsSelected() {
        assertTrue(leaderBoard.isSelected(0));
    }

    @Test
    public void testIsSelectedLeaderBoard() {
        assertTrue(leaderBoard.isSelectedLeaderBoard());
    }
}
