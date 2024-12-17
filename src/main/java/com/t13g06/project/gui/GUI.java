package com.t13g06.project.gui;

import com.t13g06.project.model.Position;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public interface GUI {
    ACTION getNextAction() throws IOException;


     void drawCharacterImage(Position position, InputStream imageStream, int targetWidth, int targetHeight) throws IOException;


     void drawPlayer_1(Position position);

    void drawBall(Position position);

    void drawWall(Position position);

    void drawPowerUp(Position position, String power);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
