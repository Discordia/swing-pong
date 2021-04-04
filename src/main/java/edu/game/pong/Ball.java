package edu.game.pong;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Random;
import java.util.SplittableRandom;
import javax.swing.*;
import static edu.game.pong.PongConstants.BALL_SIZE;
import static edu.game.pong.PongConstants.CENTER_LOCATION;
import static edu.game.pong.PongConstants.BALL_MAX_SPEED;
import static edu.game.pong.PongConstants.BALL_MIN_SPEED;
import static edu.game.pong.PongConstants.BALL_SPEED_INCREMENT;

public class Ball extends JComponent {
    private final Float ballLocation;
    private Float ballDirection;
    private int speed;

    public Ball() {
        this.ballLocation = new Float();
        this.ballLocation.setLocation(CENTER_LOCATION);
        this.ballDirection = new Float(0, 0);
        this.speed = BALL_MIN_SPEED;

        setSize(BALL_SIZE);
        update();
    }

    public Point2D.Float getDirection() {
        final Float direction = new Float();
        direction.setLocation(ballDirection);
        return direction;
    }

    public void start() {
        speed = BALL_MIN_SPEED;
        ballLocation.setLocation(CENTER_LOCATION);

        SplittableRandom srandom = new SplittableRandom();
        Random random = new Random();
        final boolean startToRight = random.nextBoolean();

        float x = startToRight ? 0.5f : -0.5f;
        x += (float) srandom.nextDouble(-0.1, 0.1);

        final boolean upDown = random.nextBoolean();
        float y = upDown ? 0.5f : -0.5f;
        y += (float) srandom.nextDouble(-0.1, 0.1);

        ballDirection.setLocation(x, y);
        ballDirection = normalize(ballDirection);

        update();
    }

    public void stop() {
        ballLocation.setLocation(CENTER_LOCATION);
        ballDirection.setLocation(0, 0);
        update();
    }

    public void update() {
        ballLocation.setLocation(
            ballLocation.getX() + ballDirection.getX() * speed,
            ballLocation.getY() + ballDirection.getY() * speed);
        setLocation(getBallLocation());
    }

    public void toggleDirectionY() {
        ballDirection.setLocation(ballDirection.x, -ballDirection.y);
        ballDirection = normalize(ballDirection);
    }

    public void toggleDirectionX() {
        ballDirection.setLocation(-ballDirection.x, ballDirection.y);
        ballDirection = normalize(ballDirection);
    }

    public void increaseSpeed() {
        if (speed >= BALL_MAX_SPEED) {
            return;
        }

        speed += BALL_SPEED_INCREMENT;
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
