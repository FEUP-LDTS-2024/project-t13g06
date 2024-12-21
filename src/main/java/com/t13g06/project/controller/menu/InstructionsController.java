package com.t13g06.project.controller.menu;


import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.states.MenuState;

import java.io.IOException;
import java.util.Set;

public class InstructionsController extends Controller<Instructions> {

    // Constructor: Initializes the controller with the Instructions model.
    public InstructionsController(Instructions settings) {
        super(settings);
    }

    // Handles user actions to navigate from the instructions screen.
    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (actionSet == null || actionSet.isEmpty()) return;

        for (GUI.ACTION action : actionSet) {
            switch (action) {

                case SELECT:
                    if (getModel().isSelectedInstructions()) game.setState(new MenuState(new Menu()));
                    break;

                default:
                    break;
            }
        }
    }

}
