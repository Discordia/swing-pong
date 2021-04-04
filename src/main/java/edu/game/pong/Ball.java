package edu.game.pong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D.Float;
import java.util.Random;
import javax.swing.*;

public class Ball extends JComponent {
    public static final int MIN_SPEED = 10;
    public static final int MAX_SPEED = 30;
    public static final int SPEED_INCREMENT = 2;

    private final Float centerPosition;

    private final Float ballLocation;
    private Float ballDirection;
    private int speed;

    public Ball(Float centerPosition) {
        this.centerPosition = centerPosition;
        this.ballLocation = new Float();
        this.ballLocation.setLocation(centerPosition);
        this.ballDirection = new Float(0, 0);
        this.speed = MIN_SPEED;

        update();
    }

    public void start() {
        speed = MIN_SPEED;
        ballLocation.setLocation(centerPosition);

        Random random = new Random();
        final boolean startToRight = random.nextBoolean();
        float x = startToRight ? 1.0f : -1.0f;
        float y = 0.0f;
        ballDirection.setLocation(x, y);
        ballDirection = normalize(ballDirection);

        update();
    }

    public void update() {
        ballLocation.setLocation(
            ballLocation.getX() + ballDirection.getX() * speed,
            ballLocation.getY() + ballDirection.getY() * speed);
        setLocation(getBallLocation());
    }

    public void increaseSpeed() {
        if (speed >= MAX_SPEED) {
            return;
        }

        speed += SPEED_INCREMENT;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }

    private Point getBallLocation() {
        return new Point((int)(ballLocation.getX()), (int)(ballLocation.getY()));
    }

    private Float normalize(Float direction) {
        final double magnitude = Math.sqrt(direction.getX() * direction.getX() + direction.getY() * direction.getY());

        if (magnitude <= 0) {
            return direction;
        }

        float x = (float) (direction.x / magnitude);
        float y = (float) (direction.y / magnitude);
        return new Float(x, y);
    }
}
