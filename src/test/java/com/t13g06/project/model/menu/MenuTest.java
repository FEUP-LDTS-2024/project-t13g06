package com.t13g06.project.model.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testConstructor() {
        assertEquals(4, menu.getNumberEntries());
        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testNextEntry() {
        menu.nextEntry();
        assertTrue(menu.isSelectedLeaderBoard());

        menu.nextEntry();
        assertTrue(menu.isSelectedInstructions());

        menu.nextEntry();
        assertTrue(menu.isSelectedExit());

        menu.nextEntry();
        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testPreviousEntry() {
        menu.previousEntry();
        assertTrue(menu.isSelectedExit());

        menu.previousEntry();
        assertTrue(menu.isSelectedInstructions());

        menu.previousEntry();
        assertTrue(menu.isSelectedLeaderBoard());

        menu.previousEntry();
        assertTrue(menu.isSelectedStart());
    }

    @Test
    public void testGetEntry() {
        assertEquals("Start", menu.getEntry(0));
        assertEquals("LeaderBoard", menu.getEntry(1));
        assertEquals("Instructions", menu.getEntry(2));
        assertEquals("Exit", menu.getEntry(3));
    }

    @Test
    public void testIsSelected() {
        for (int i = 0; i < 4; i++) {
            assertTrue(menu.isSelected(i));
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    assertFalse(menu.isSelected(j));
                }
            }
            menu.nextEntry();
        }
    }

    @Test
    public void testIsSelectedStart() {
        assertTrue(menu.isSelectedStart());
        menu.nextEntry();
        assertFalse(menu.isSelectedStart());
    }

    @Test
    public void testIsSelectedLeaderBoard() {
        menu.nextEntry();
        assertTrue(menu.isSelectedLeaderBoard());
        menu.nextEntry();
        assertFalse(menu.isSelectedLeaderBoard());
    }

    @Test
    public void testIsSelectedInstructions() {
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelectedInstructions());
        menu.nextEntry();
        assertFalse(menu.isSelectedInstructions());
    }

    @Test
    public void testIsSelectedExit() {
        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();
        assertTrue(menu.isSelectedExit());
        menu.previousEntry();
        assertFalse(menu.isSelectedExit());
    }

    @Test
    public void testEdgeCasesForNextPrevious() {
        menu.previousEntry();
        assertTrue(menu.isSelectedExit());

        menu.previousEntry();
        assertTrue(menu.isSelectedInstructions());

        menu.previousEntry();
        assertTrue(menu.isSelectedLeaderBoard());

        menu.previousEntry();
        assertTrue(menu.isSelectedStart());
    }
}
