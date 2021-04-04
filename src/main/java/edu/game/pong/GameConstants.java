package edu.game.pong;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class GameConstants {
    public static final Dimension WINDOW_SIZE = new Dimension(1792, 1120);

    public static final Float CENTER_LOCATION = new Point2D.Float(
        (float)WINDOW_SIZE.getWidth() / 2 - 15,
        (float)WINDOW_SIZE.getHeight() / 2 - 15);
    public static final Dimension BALL_SIZE = new Dimension(30, 30);

    public static final int MIN_SPEED = 24;
    public static final int MAX_SPEED = 52;
    public static final int SPEED_INCREMENT = 4;
}
