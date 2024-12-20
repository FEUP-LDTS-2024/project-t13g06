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

        Set<GUI.ACTION> actionSet = gui.getActionSet();

        if (actionSet.contains(GUI.ACTION.QUIT)) {
            game.setState(null);
            return;
        }

        controller.step(game, actionSet, time);

        viewer.draw(gui);
    }

}
