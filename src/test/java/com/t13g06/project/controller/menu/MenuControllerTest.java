package com.t13g06.project.controller.menu;

import static org.mockito.Mockito.*;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.states.GameState;
import com.t13g06.project.states.LeaderBoardState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MenuControllerTest {

    private Menu mockMenu;
    private Game mockGame;
    private Set<GUI.ACTION> actionSet;
    private MenuController controller;

    @BeforeEach
    public void setup() {
        // Initialize the mock objects before each test
        mockMenu = mock(Menu.class);
        mockGame = mock(Game.class);
        actionSet = new HashSet<>();
        controller = new MenuController(mockMenu);
    }

    @Test
    public void testStep_UpAction_ChangesSelectionToPrevious() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.UP);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockMenu, times(1)).previousEntry();
    }

    @Test
    public void testStep_DownAction_ChangesSelectionToNext() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.DOWN);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockMenu, times(1)).nextEntry();
    }

    @Test
    public void testStep_SelectStartAction() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockMenu.isSelectedStart()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(1)).setState(any(GameState.class));
    }

    @Test
    public void testStep_SelectLeaderBoardAction() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockMenu.isSelectedLeaderBoard()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(1)).setState(any(LeaderBoardState.class));
    }

    @Test
    public void testStep_SelectExitAction() throws IOException {
        actionSet.clear();
        actionSet.add(GUI.ACTION.SELECT);
        when(mockMenu.isSelectedExit()).thenReturn(true);

        controller.step(mockGame, actionSet, 1000L);

        verify(mockGame, times(1)).setState(null);
    }
}
