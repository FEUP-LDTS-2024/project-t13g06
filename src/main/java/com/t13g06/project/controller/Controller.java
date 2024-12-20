package com.t13g06.project.controller;

import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;

import java.io.IOException;
import java.util.Set;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException;
}
