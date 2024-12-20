package com.t13g06.project.states;

import com.t13g06.project.controller.Controller;
import com.t13g06.project.controller.menu.InstructionsController;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.viewer.Viewer;
import com.t13g06.project.viewer.menu.InstructionsViewer;


public class InstructionsState extends State<Instructions> {
    public InstructionsState(Instructions model) {
        super(model);
    }

    @Override
    protected Viewer<Instructions> getViewer() {
        return new InstructionsViewer(getModel());
    }

    @Override
    protected Controller<Instructions> getController() {
        return new InstructionsController(getModel());
    }
}
