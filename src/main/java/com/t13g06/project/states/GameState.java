package com.t13g06.project.states;

import com.t13g06.project.model.game.arena.Arena;
import com.t13g06.project.viewer.Viewer;
import com.t13g06.project.viewer.game.GameViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

}
