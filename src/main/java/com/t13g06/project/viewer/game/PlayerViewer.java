package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(Player player_1, GUI gui) {
        gui.drawPlayer(player_1.getPosition());
    }
}
