package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class BallViewerTest {
    private GUI gui;
    private Ball ball;
    private BallViewer ballViewer;

    @BeforeEach
    void setUp() {
        ballViewer = new BallViewer();
        ball = new Ball(10,10);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        ballViewer.draw(ball, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBall(ball.getPosition());
    }
}
