package com.t13g06.project.model.game.arena;

import com.t13g06.project.model.Position;
import com.t13g06.project.model.game.elements.*;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private Player_1 player_1;
    private Player_2 player_2;

    private Ball ball;

    private List<PowerUps> powerUp;
    private List<Wall> walls;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player_1 getPlayer_1() {
        return player_1;
    }

    public Player_2 getPlayer_2() {
        return player_2;
    }

    public void setPlayer_1(Player_1 player_1_) {
        this.player_1 = player_1_;
    }
    public void setPlayer_2(Player_2 player_2_) {
        this.player_2 = player_2_;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball_) {
        this.ball = ball_;
    }

    public List<PowerUps> getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(List<PowerUps> powerUp_) {
        this.powerUp = powerUp_;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isPowerUp(Position position) {
        for (PowerUps powerUp : powerUp)
            if (powerUp.getPosition().equals(position))
                return true;
        return false;
    }
}
