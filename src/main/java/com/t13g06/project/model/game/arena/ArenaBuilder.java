package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.game.elements.*;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setPlayer_1(createPlayer_1());

        arena.setBalls(createBall());

        arena.setPowerUp(createPowerUp());
        arena.setWalls(createWalls());

        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<PowerUps> createPowerUp();

    protected abstract Player createPlayer_1();

    protected abstract List<Ball> createBall();
}
