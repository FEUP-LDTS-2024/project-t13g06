package com.t13g06.project.controller.game;

import com.t13g06.project.controller.Controller;
import com.t13g06.project.model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
