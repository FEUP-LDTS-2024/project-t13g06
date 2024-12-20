package com.t13g06.project.viewer.menu;

import com.t13g06.project.gui.GUI;
import com.t13g06.project.model.Position;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.viewer.Viewer;

import java.io.IOException;
import java.io.InputStream;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu menu) {
        super(menu);

    }

    @Override
    public void drawElements(GUI gui) throws IOException {
        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("images/MenuBackground.png");
        if (imageStream != null) {
            int scaledWidth = 51;
            int scaledHeight = 21;
            gui.drawCharacterImage(new Position(2, 0), imageStream, scaledWidth, scaledHeight);
        } else {
            System.err.println("Image not found: images/MenuBackground.png");
        }

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(35, 7 + i*2),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF"
            );
        }
    }

}
