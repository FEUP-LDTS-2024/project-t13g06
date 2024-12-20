package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.PowerUps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PowerUpsViewerTest {
    private PowerUps powerUps;
    private PowerUpsViewer powerUpsViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        powerUps = new PowerUps(10, 10, "freeze");
        powerUpsViewer = new PowerUpsViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        powerUpsViewer.draw(powerUps, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(powerUps.getPosition(), powerUps.getPower());
    }
}
