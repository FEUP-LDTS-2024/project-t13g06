package com.t13g06.project.states;

import com.t13g06.project.Game;
import com.t13g06.project.controller.Controller;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;

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
        GUI.ACTION action = gui.getNextAction();

        switch (action) {
            case QUIT:
                game.setState(null);
                break;
        }

        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
