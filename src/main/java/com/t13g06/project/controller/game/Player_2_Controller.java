package com.t13g06.project.controller.game;


import com.t13g06.project.Game;
import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.arena.Arena;

public class Player_2_Controller extends GameController {
    public Player_2_Controller(Arena arena) {
        super(arena);
    }

    public void moveHeroLeft() {
        moveHero(getModel().getPlayer_2().getPosition().getLeft());
    }

    public void moveHeroRight() {
        moveHero(getModel().getPlayer_2().getPosition().getRight());
    }

    public void moveHeroUp() {
        moveHero(getModel().getPlayer_2().getPosition().getUp());
    }

    public void moveHeroDown() {
        moveHero(getModel().getPlayer_2().getPosition().getDown());
    }

    private void moveHero(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getPlayer_2().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP_2) moveHeroUp();
        if (action == GUI.ACTION.RIGHT_2) moveHeroRight();
        if (action == GUI.ACTION.DOWN_2) moveHeroDown();
        if (action == GUI.ACTION.LEFT_2) moveHeroLeft();
    }
}
