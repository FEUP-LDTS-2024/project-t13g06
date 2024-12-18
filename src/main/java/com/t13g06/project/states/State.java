package com.t13g06.project.states;

import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.util.Set;

public abstract class State<T> {
    private final T model;
    private final Viewer<T> viewer;
    private final Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }


    public void step(Game game, GUI gui, long time) throws IOException {
        // Retrieve the current set of active actions from the GUI
        Set<GUI.ACTION> actionSet = gui.getActionSet();

        // Handle global actions like QUIT
        if (actionSet.contains(GUI.ACTION.QUIT)) {
            game.setState(null); // Exit the game
            return; // No need to process further if quitting
        }

        // Delegate action handling to the controller
        controller.step(game, actionSet, time);

        // Render the updated state
        viewer.draw(gui);
    }

}
