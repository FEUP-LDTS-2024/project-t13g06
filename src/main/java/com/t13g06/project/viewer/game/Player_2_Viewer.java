package com.t13g06.project.viewer.game;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.game.elements.Player_1;
import com.t13g06.project.model.game.elements.Player_2;

public class Player_2_Viewer implements ElementViewer<Player_2> {
    @Override
    public void draw(Player_2 player_2, GUI gui) {
        gui.drawPlayer_2(player_2.getPosition());
    }
}
