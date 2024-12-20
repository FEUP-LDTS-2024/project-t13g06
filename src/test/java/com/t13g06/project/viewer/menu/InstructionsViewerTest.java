package com.t13g06.project.viewer.menu;

import static org.mockito.Mockito.*;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.Instructions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class InstructionsViewerTest {

    private GUI mockGui;
    private Instructions mockInstructions;
    private InstructionsViewer viewer;

    @BeforeEach
    void setUp() {
        mockGui = mock(GUI.class);
        mockInstructions = mock(Instructions.class);
        viewer = new InstructionsViewer(mockInstructions);
    }

    @Test
    void testDrawStaticTextElements() throws IOException {
        viewer.drawElements(mockGui);

        verify(mockGui).drawText(eq(new Position(4, 8)), eq("WELCOME TO RETRO LEAGUE"), eq("#FFFFFF"));
        verify(mockGui).drawText(eq(new Position(4, 10)), eq("Survive as long as possible;"), eq("#FFFFFF"));
        verify(mockGui).drawText(eq(new Position(4, 14)), eq("LIST OF POWER UPS:"), eq("#FF00FF"));
        verify(mockGui).drawText(eq(new Position(26, 14)), eq("LIST OF CONTROLS:"), eq("#FF00FF"));
    }

    @Test
    void testDrawPowerUps() throws IOException {
        viewer.drawElements(mockGui);

        verify(mockGui).drawText(eq(new Position(5, 15)), eq("%"), eq("#00CCFF"));
        verify(mockGui).drawText(eq(new Position(6, 15)), eq(" : BALL FREEZE"), eq("#FFFFFF"));
        verify(mockGui).drawText(eq(new Position(5, 16)), eq("$"), eq("#FF9900"));
        verify(mockGui).drawText(eq(new Position(6, 16)), eq(" : BALL SPEED-UP"), eq("#FFFFFF"));
    }

    @Test
    void testDrawControls() throws IOException {
        viewer.drawElements(mockGui);

        verify(mockGui).drawText(eq(new Position(26, 16)), eq("USE UR ARROW KEYS TO MOVE"), eq("#FFFFFF"));
        verify(mockGui).drawText(eq(new Position(26, 17)), eq("PRESS P TO PAUSE"), eq("#FFFFFF"));
    }

    @Test
    void testDrawDynamicEntries() throws IOException {
        when(mockInstructions.getNumberEntries()).thenReturn(3);
        when(mockInstructions.getEntry(0)).thenReturn("Option 1");
        when(mockInstructions.getEntry(1)).thenReturn("Option 2");
        when(mockInstructions.getEntry(2)).thenReturn("Option 3");
        when(mockInstructions.isSelected(0)).thenReturn(false);
        when(mockInstructions.isSelected(1)).thenReturn(true);
        when(mockInstructions.isSelected(2)).thenReturn(false);

        viewer.drawElements(mockGui);

        verify(mockGui).drawText(eq(new Position(45, 19)), eq("Option 1"), eq("#FFFFFF"));
        verify(mockGui).drawText(eq(new Position(45, 20)), eq("Option 2"), eq("#FFD700"));
        verify(mockGui).drawText(eq(new Position(45, 21)), eq("Option 3"), eq("#FFFFFF"));
    }
}





