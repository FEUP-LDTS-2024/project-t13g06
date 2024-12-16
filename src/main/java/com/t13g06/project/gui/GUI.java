package com.t13g06.project.gui;

import com.t13g06.project.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawPlayer_1(Position position);
    void drawPlayer_2(Position position);

    void drawBall(Position position);

    void drawWall(Position position);

    void drawPowerUp(Position position, String power);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP_2, RIGHT_2, DOWN_2, LEFT_2, UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
