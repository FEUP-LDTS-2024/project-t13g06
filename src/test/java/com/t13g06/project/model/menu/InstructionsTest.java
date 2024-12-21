package com.t13g06.project.model.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InstructionsTest {
    private Instructions instructions;

    @BeforeEach
    public void setUp() {
        instructions = new Instructions();
    }

    @Test
    public void testConstructor() {
        assertEquals(1, instructions.getNumberEntries());
    }

    @Test
    public void testGetEntry() {
        assertEquals("Go Back", instructions.getEntry(0));
    }

    @Test
    public void testIsSelected() {
        assertTrue(instructions.isSelected(0));
    }

    @Test
    public void testIsSelectedInstructions() {
        assertTrue(instructions.isSelectedInstructions());
    }
}
