package com.t13g06.project.states;

import com.t13g06.project.controller.menu.LeaderBoardController;
import com.t13g06.project.controller.menu.MenuController;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.viewer.menu.LeaderBoardViewer;
import com.t13g06.project.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuStateTest {
    Menu menu;
    MenuState menuState;

    @BeforeEach
    void setup() {
        menu = Mockito.mock(Menu.class);
        menuState = new MenuState(menu);

    }

    @Test
    void MainContent() {
        assertEquals(menu, menuState.getModel());
        assertEquals(MenuViewer.class, menuState.getViewer().getClass());
        assertEquals(MenuController.class, menuState.getController().getClass());
    }
}
