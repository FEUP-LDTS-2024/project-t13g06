package com.t13g06.project.model.game.elements;

import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Element {
    public Ball(int x, int y) {
        super(x, y);
    }

    private boolean frozen = false;
    private int speed = 1; // Default speed multiplier

    public void freeze(int duration) {
        frozen = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                frozen = false;
            }
        }, duration);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void increaseSpeed() {
        speed = Math.min(speed + 1, 10); // Cap speed multiplier at 3x
    }

    public void decreaseSpeed() {
        speed = Math.max(speed - 1, 1); // Minimum speed multiplier is 1x
    }

    public int getSpeed() {
        return speed;
    }
}
