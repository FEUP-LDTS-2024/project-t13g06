package com.t13g06.project.controller.menu;


import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.arena.LoaderArenaBuilder;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.states.GameState;
import com.t13g06.project.states.LeaderBoardState;
import com.t13g06.project.states.InstructionsState;

import java.io.IOException;
import java.util.Set;

public class MenuController extends Controller<Menu> {

    // Constructor: Initializes the controller with the Menu model.
    public MenuController(Menu menu) {
        super(menu);
    }

    // Handles user actions to navigate and select options in the main menu.
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (actionSet == null || actionSet.isEmpty()) return;

        for (GUI.ACTION action : actionSet) {
            switch (action) {
                case UP:
                    getModel().previousEntry();
                    break;

                case DOWN:
                    getModel().nextEntry();
                    break;

                case SELECT:
                    if (getModel().isSelectedStart())
                        game.setState(new GameState(new LoaderArenaBuilder(1).createArena()));
                    if (getModel().isSelectedLeaderBoard())
                        game.setState(new LeaderBoardState(new LeaderBoard()));
                    if (getModel().isSelectedSettings())
                        game.setState(new InstructionsState(new Instructions()));
                    if (getModel().isSelectedExit())
                        game.setState(null);
                    break;

                default:
                    break;
            }
        }
    }

}
