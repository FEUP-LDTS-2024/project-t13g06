package com.t13g06.project.states;

import com.t13g06.project.controller.Controller;
import com.t13g06.project.controller.menu.MenuController;
import com.t13g06.project.model.menu.Menu;
import com.t13g06.project.viewer.Viewer;
import com.t13g06.project.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}