package com.t13g06.project.states;

import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.viewer.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

class StateTest {
    private State state;
    private Viewer viewerMock;
    private Controller controllerMock;
    private Game modelMock;

    @BeforeEach
    void init() {
        viewerMock = Mockito.mock(Viewer.class);
        controllerMock = Mockito.mock(Controller.class);
        modelMock = Mockito.mock(Game.class);

        state = new State(modelMock) {
            @Override
            protected Viewer getViewer() {
                return viewerMock;
            }

            @Override
            protected Controller getController() {
                return controllerMock;
            }
        };
    }

    @Test
    void testStepQuitAction() throws IOException {
        Game gameMock = mock(Game.class);
        GUI guiMock = mock(GUI.class);

        when(guiMock.getActionSet()).thenReturn(Set.of(GUI.ACTION.QUIT));

        state.step(gameMock, guiMock, 0);

        verify(gameMock).setState(null);

        verifyNoInteractions(controllerMock);
        verifyNoInteractions(viewerMock);
    }

    @Test
    void testStepNormalFlow() throws IOException {
        Game gameMock = mock(Game.class);
        GUI guiMock = mock(GUI.class);

        when(guiMock.getActionSet()).thenReturn(Set.of());

        state.step(gameMock, guiMock, 100L);

        verify(controllerMock).step(gameMock, Set.of(), 100L);
        verify(viewerMock).draw(guiMock);
    }
}
