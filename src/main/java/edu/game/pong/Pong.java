package edu.game.pong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import javax.swing.*;

public class Pong extends JFrame {
    private static final Dimension WINDOW_SIZE = new Dimension(1792, 1120);

    private final InputManager inputManager;
    private PlayField playField;
    private Padel leftPadel;
    private Padel rightPadel;
    private Ball ball;

    private Timer ballStartTimer;

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

        ballStartTimer = new Timer(1000, this::startBall);
        ballStartTimer.setRepeats(true);
        ballStartTimer.start();

        final Timer timer = new Timer(3000 / 60, this::gameLoop);
        timer.setRepeats(true);
        timer.start();
    }

    private void gameLoop(final ActionEvent e) {
        if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }

        // check is game over? If so display game over and only check for space
        if (playField.hasWinner()) {
            playField.showGameOver();

            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                playField.resetScore();
            }

            return;
        }

        // update entities
        leftPadel.update();
        rightPadel.update();
        ball.update();

        // check collision and either score or change direction


    }

    private void startBall(ActionEvent e) {
        final boolean startBall = playField.countdown();

        if (startBall) {
            ball.start();
            ballStartTimer.stop();
        }
    }
}
