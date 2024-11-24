package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Ball;
import com.t13g06.project.model.game.elements.Player_2;

public class BallViewer implements ElementViewer<Ball> {
    @Override
    public void draw(Ball ball, GUI gui) {
        gui.drawBall(ball.getPosition());
    }
}
