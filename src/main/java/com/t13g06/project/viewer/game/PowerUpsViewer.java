package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.PowerUps;

public class PowerUpsViewer implements ElementViewer<PowerUps> {
    @Override
    public void draw(PowerUps powerUp, GUI gui) {
        gui.drawPowerUp(powerUp.getPosition(), powerUp.getPower());
    }
}
