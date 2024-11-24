package com.t13g06.project.model.game.elements;

public class PowerUps extends Element {
    String power;
    public PowerUps(int x, int y, String power_) {
        super(x, y);
        this.power = power_;
    }

    public String getPower() {
        return power;
    }
}
