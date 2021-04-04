package edu.game.pong;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class PongConstants {
    public static final Dimension WINDOW_SIZE = new Dimension(1792, 1120);

    public static final Float CENTER_LOCATION = new Point2D.Float(
        (float)WINDOW_SIZE.getWidth() / 2 - 15,
        (float)WINDOW_SIZE.getHeight() / 2 - 15);
    public static final Dimension BALL_SIZE = new Dimension(30, 30);

    public static final int BALL_MIN_SPEED = 8;
    public static final int BALL_MAX_SPEED = 20;
    public static final int BALL_SPEED_INCREMENT = 2;

    public static final int PADEL_SPEED = 10;
}
