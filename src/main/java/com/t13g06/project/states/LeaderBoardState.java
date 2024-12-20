package com.t13g06.project.states;

import com.t13g06.project.controller.Controller;
import com.t13g06.project.controller.menu.LeaderBoardController;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.viewer.Viewer;
import com.t13g06.project.viewer.menu.LeaderBoardViewer;


public class LeaderBoardState extends State<LeaderBoard> {
    public LeaderBoardState(LeaderBoard model) {
        super(model);
    }

    @Override
    protected Viewer<LeaderBoard> getViewer() {
        return new LeaderBoardViewer(getModel());
    }

    @Override
    protected Controller<LeaderBoard> getController() {
        return new LeaderBoardController(getModel());
    }
}
