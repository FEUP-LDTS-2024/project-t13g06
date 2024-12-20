package com.t13g06.project.states;

import com.t13g06.project.controller.menu.InstructionsController;
import com.t13g06.project.controller.menu.LeaderBoardController;
import com.t13g06.project.model.menu.Instructions;
import com.t13g06.project.model.menu.LeaderBoard;
import com.t13g06.project.viewer.menu.InstructionsViewer;
import com.t13g06.project.viewer.menu.LeaderBoardViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderBoardStateTest {
    LeaderBoard leaderBoard;
    LeaderBoardState leaderBoardState;

    @BeforeEach
    void setup() {
        leaderBoard = Mockito.mock(LeaderBoard.class);
        leaderBoardState = new LeaderBoardState(leaderBoard);

    }

    @Test
    void MainContent() {
        assertEquals(leaderBoard, leaderBoardState.getModel());
        assertEquals(LeaderBoardViewer.class, leaderBoardState.getViewer().getClass());
        assertEquals(LeaderBoardController.class, leaderBoardState.getController().getClass());
    }
}
