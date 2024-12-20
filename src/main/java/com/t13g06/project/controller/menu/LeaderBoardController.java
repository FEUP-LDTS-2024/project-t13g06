package com.t13g06.project.controller.menu;


import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.states.MenuState;

import java.io.IOException;
import java.util.Set;

public class LeaderBoardController extends Controller<LeaderBoard> {

    // Constructor: Initializes the controller with the LeaderBoard model.
    public LeaderBoardController(LeaderBoard leaderBoard) {
        super(leaderBoard);
    }

    // Handles user actions to navigate from the leaderboard screen.
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (actionSet == null || actionSet.isEmpty()) return;

        for (GUI.ACTION action : actionSet) {
            switch (action) {

                case SELECT:
                    if (getModel().isSelectedLeaderBoard()) game.setState(new MenuState(new Menu()));
                    break;

                default:
                    break;
            }
        }
    }

}
