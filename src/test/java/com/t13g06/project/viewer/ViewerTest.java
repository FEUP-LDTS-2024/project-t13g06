package com.t13g06.project.viewer;

import com.t13g06.project.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ViewerTest {

    private GUI gui;
    private Viewer<String> viewer;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
        viewer = new Viewer<String>("TestModel") {
            @Override
            protected void drawElements(GUI gui) {
            }
        };
    }

    @Test
    void testDrawMethod() throws Exception {
        viewer.draw(gui);
        verify(gui, times(1)).clear();
        verify(gui, times(1)).refresh();
    }
}
