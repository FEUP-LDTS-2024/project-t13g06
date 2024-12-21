package com.t13g06.project.controller.menu;

import static org.mockito.Mockito.*;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InstructionsControllerTest {

    private Instructions mockInstructions;
    private Game mockGame;
    private Set<GUI.ACTION> actionSet;
    private InstructionsController controller;

    @BeforeEach
    public void setup() {
        mockInstructions = mock(Instructions.class);
        mockGame = mock(Game.class);
        actionSet = new HashSet<>();
        controller = new InstructionsController(mockInstructions);
    }

    @Test
    public void testStep_SelectSettingsAction_ChangesGameStateToMenuState() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockInstructions.isSelectedInstructions()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(1)).setState(any(MenuState.class));
    }

    @Test
    public void testStep_EmptyActionSet() throws IOException {
        actionSet.clear();

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(0)).setState(any());
    }

    @Test
    public void testStep_SelectAction_NoSelection() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockInstructions.isSelectedInstructions()).thenReturn(false);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(0)).setState(any());
    }
}
