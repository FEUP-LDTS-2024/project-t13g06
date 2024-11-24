package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Player_1;

public class Player_1_Viewer implements ElementViewer<Player_1> {
    @Override
    public void draw(Player_1 player_1, GUI gui) {
        gui.drawPlayer_1(player_1.getPosition());
    }
}
