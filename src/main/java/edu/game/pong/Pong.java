package edu.game.pong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import javax.swing.*;

public class Pong extends JFrame {
    private static final Dimension WINDOW_SIZE = new Dimension(1792, 1120);

    private final InputManager inputManager;
    private PlayField playField;
    private Padel leftPadel;
    private Padel rightPadel;
    private Ball ball;

    private Timer ballStartTimer;

    private final Rectangle leftGoalBounds = new Rectangle(0, 0, 90, 1120);
    private final Rectangle rightGoalBounds = new Rectangle(1702, 0, 90, 1120);

    public Pong() {
        inputManager = new InputManager();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(WINDOW_SIZE);
    }

    void start() {
        playField = new PlayField(null);
        playField.setSize(WINDOW_SIZE);
        playField.setBackground(Color.BLACK);
        add(playField);

        leftPadel = new Padel(inputManager, KeyEvent.VK_W, KeyEvent.VK_S);
        leftPadel.setLocation(new Point(100, 100));
        leftPadel.setSize(new Dimension(20, 100));
        playField.add(leftPadel);

        rightPadel = new Padel(inputManager, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        rightPadel.setLocation((int) WINDOW_SIZE.getWidth() - 120, 100);
        rightPadel.setSize(new Dimension(20, 100));
        playField.add(rightPadel);

        ball = new Ball(new Point2D.Float(
            (float)WINDOW_SIZE.getWidth() / 2 - 15,
            (float)WINDOW_SIZE.getHeight() / 2 - 15));
        ball.setSize(new Dimension(30, 30));
        playField.add(ball);

        addKeyListener(inputManager);
        setVisible(true);

        startNewRound();

        final Timer timer = new Timer(3000 / 60, this::gameLoop);
        timer.setRepeats(true);
        timer.start();
    }

    private void startNewRound() {
        if (playField.hasWinner()) {
            return;
        }

        ball.stop();
        playField.newRound();
        ballStartTimer = new Timer(1000, this::startBall);
        ballStartTimer.setRepeats(true);
        ballStartTimer.start();
    }

    private void gameLoop(final ActionEvent e) {
        if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }

        // check is game over? If so display game over and only check for space
        if (playField.hasWinner()) {
            ball.stop();
            playField.showGameOver();

            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                playField.resetScore();
                startNewRound();
            }

            return;
        }

        // update entities
        leftPadel.update();
        rightPadel.update();
        ball.update();

        // check collision and either score or change direction
        final Float direction = ball.getDirection();
        if ((direction.y < 0 && ball.getLocation().y <= 0) ||
                (direction.y > 0 && ball.getLocation().y >= 1070)) {
            ball.toggleDirectionY();
        } else if ((direction.x < 0 && ball.getBounds().intersects(leftPadel.getBounds())) ||
                (direction.x > 0 && ball.getBounds().intersects(rightPadel.getBounds()))) {
            ball.toggleDirectionX();
        }

        if (direction.x < 0 && ball.getBounds().intersects(leftGoalBounds)) {
            playField.rightScored();
            startNewRound();
        } else if (direction.x > 0 && ball.getBounds().intersects(rightGoalBounds)) {
            playField.leftScored();
            startNewRound();
        }
    }

    private void startBall(ActionEvent e) {
        final boolean startBall = playField.countdown();

        if (startBall) {
            ball.start();
            ballStartTimer.stop();
        }
    }
}
