package com.t13g06.project.model.game.elements;

public class Player_2 extends Element {
    private int lives;

    public Player_2(int x, int y) {
        super(x, y);
        this.lives = 5;
    }

    public void decreaseLive() {
        this.lives--;
    }

    public int getLives() {
        return lives;
    }
}
