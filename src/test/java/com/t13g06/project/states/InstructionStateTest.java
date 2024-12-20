package com.t13g06.project.states;

import com.t13g06.project.controller.menu.EndController;
import com.t13g06.project.controller.menu.InstructionsController;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.viewer.menu.EndViewer;
import com.t13g06.project.viewer.menu.InstructionsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionStateTest {
    InstructionsState instructionsState;
    Instructions instructions;

    @BeforeEach
    void setup() {
        instructions = Mockito.mock(Instructions.class);
        instructionsState = new InstructionsState(instructions);

    }

    @Test
    void MainContent() {
        assertEquals(instructions, instructionsState.getModel());
        assertEquals(InstructionsViewer.class, instructionsState.getViewer().getClass());
        assertEquals(InstructionsController.class, instructionsState.getController().getClass());
    }
}
