package com.t13g06.project.states;

import com.t13g06.project.controller.Controller;
import com.t13g06.project.controller.menu.EndController;
import com.t13g06.project.model.menu.End;
import com.t13g06.project.viewer.Viewer;
import com.t13g06.project.viewer.menu.EndViewer;

public class EndState extends State<End> {
    private final long gameDuration; // Add elapsed time

    public EndState(End endModel, long gameDuration) {
        super(endModel);
        this.gameDuration = gameDuration;
        endModel.setGameTime(gameDuration);

    }

    @Override
    protected Viewer<End> getViewer() {
        return new EndViewer(getModel(), gameDuration);
    }


    @Override
    protected Controller<End> getController() {
        // Pass the model and elapsed time to the EndController
        return new EndController(getModel(), (EndViewer) getViewer(), gameDuration);
    }
}
