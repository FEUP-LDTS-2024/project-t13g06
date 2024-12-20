package com.t13g06.project.states;

import com.t13g06.project.controller.menu.EndController;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.viewer.menu.EndViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndStateTest {
    EndState endState;
    End end;
    long gameDuration;

    @BeforeEach
    void setup() {
        end = Mockito.mock(End.class);
        gameDuration = 500L;
        endState = new EndState(end, gameDuration);

    }

    @Test
    void MainContent() {
        assertEquals(end, endState.getModel());
        assertEquals(EndViewer.class, endState.getViewer().getClass());
        assertEquals(EndController.class, endState.getController().getClass());
    }
}