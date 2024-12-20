package com.t13g06.project.model.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EndTest {
    private End end;

    @BeforeEach
    public void setUp() {
        end = new End(120);
    }

    @Test
    public void testConstructor() {
        assertEquals(3, end.getNumberEntries());
        assertEquals(120, end.getGameTime());
    }

    @Test
    public void testNextEntry() {
        end.nextEntry();
        assertTrue(end.isSelectedSubmit());

        end.nextEntry();
        assertTrue(end.isSelectedEnd());

        end.nextEntry();
        assertTrue(end.isSelectedNameField());
    }

    @Test
    public void testPreviousEntry() {
        // Start at "Enter Your Name:"
        end.previousEntry();
        assertTrue(end.isSelectedEnd());

        end.previousEntry();
        assertTrue(end.isSelectedSubmit());

        end.previousEntry();
        assertTrue(end.isSelectedNameField());
    }

    @Test
    public void testGetEntry() {
        assertEquals("Enter Your Name:", end.getEntry(0));
        assertEquals("Submit", end.getEntry(1));
        assertEquals("Go Back", end.getEntry(2));
    }

    @Test
    public void testIsSelected() {
        for (int i = 0; i < 3; i++) {
            assertTrue(end.isSelected(i));
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    assertFalse(end.isSelected(j));
                }
            }
            end.nextEntry();
        }
    }

    @Test
    public void testIsSelectedNameField() {
        assertTrue(end.isSelectedNameField());
        end.nextEntry();
        assertFalse(end.isSelectedNameField());
    }

    @Test
    public void testIsSelectedSubmit() {
        end.nextEntry();
        assertTrue(end.isSelectedSubmit());
        end.nextEntry();
        assertFalse(end.isSelectedSubmit());
    }

    @Test
    public void testIsSelectedEnd() {
        end.nextEntry();
        end.nextEntry();
        assertTrue(end.isSelectedEnd());
        end.previousEntry();
        assertFalse(end.isSelectedEnd());
    }

    @Test
    public void testUpdatePlayerName() {
        end.updatePlayerName("A");
        assertEquals("A", end.getPlayerName());

        end.updatePlayerName("B");
        assertEquals("AB", end.getPlayerName());

        for (int i = 0; i < 4; i++) {
            end.updatePlayerName("C");
        }
        assertEquals("ABCCCC", end.getPlayerName());
    }

    @Test
    public void testRemoveLastChar() {
        end.updatePlayerName("A");
        end.updatePlayerName("B");
        assertEquals("AB", end.getPlayerName());

        end.removeLastChar();
        assertEquals("A", end.getPlayerName());

        end.removeLastChar();
        assertEquals("", end.getPlayerName());
    }

    @Test
    public void testIsSubmitted() {
        assertFalse(end.isSubmitted());
        end.setSubmitted();
        assertTrue(end.isSubmitted());
        end.setSubmitted();
        assertFalse(end.isSubmitted());
    }

    @Test
    public void testSetGameTime() {
        end.setGameTime(300);
        assertEquals(300, end.getGameTime());
    }
}
