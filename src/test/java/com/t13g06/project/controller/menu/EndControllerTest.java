package com.t13g06.project.controller.menu;

import static org.mockito.Mockito.*;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.states.MenuState;
import com.t13g06.project.viewer.menu.EndViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EndControllerTest {

    private End mockEnd;
    private EndViewer mockEndViewer;
    private Game mockGame;
    private Set<GUI.ACTION> actionSet;
    private EndController controller;
    private long gameDuration = 60000;

    @BeforeEach
    public void setup() {
        mockEnd = mock(End.class);
        mockEndViewer = mock(EndViewer.class);
        mockGame = mock(Game.class);
        actionSet = new HashSet<>();
        controller = new EndController(mockEnd, mockEndViewer, gameDuration);
    }

    @Test
    public void testStep_UpAction_ChangesToPreviousEntry() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.UP);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockEnd, times(1)).previousEntry();
    }

    @Test
    public void testStep_DownAction_ChangesToNextEntry() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.DOWN);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockEnd, times(1)).nextEntry();
    }

    @Test
    public void testStep_SelectSubmitAction_SavesScore() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockEnd.isSelectedSubmit()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockEnd, times(1)).setSubmitted();
        verify(mockEnd, times(1)).getPlayerName();
    }

    @Test
    public void testStep_SelectEndAction_ChangesToMenuState() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockEnd.isSelectedEnd()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(1)).setState(any(MenuState.class));
    }

    @Test
    public void testStep_TypeAction_UpdatesPlayerName() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.TYPE);
        when(mockEnd.isSelectedNameField()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockEnd, times(1)).updatePlayerName(any());
    }

    @Test
    public void testStep_BackspaceAction_RemovesLastChar() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.BACKSPACE);
        when(mockEnd.isSelectedNameField()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockEnd, times(1)).removeLastChar();
    }
}
