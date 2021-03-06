package edu.game.pong;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import static edu.game.pong.PongConstants.PADEL_SPEED;
import static edu.game.pong.PongConstants.WINDOW_SIZE;

public class Padel extends JComponent {
    private final InputManager inputManager;
    private final Player player;

    public Padel(final InputManager inputManager, Player player) {
        this.inputManager = inputManager;
        this.player = player;

        setSize(20, 100);
    }

    public void reset() {
        if (player == Player.LEFT) {
            setLocation(new Point(100, (int) WINDOW_SIZE.getHeight() / 2 - 50));
        } else {
            setLocation((int) WINDOW_SIZE.getWidth() - 120, (int) WINDOW_SIZE.getHeight() / 2 - 50);
        }
    }

    public void update() {
        Point location = getLocation();
        if  (inputManager.isKeyDown(getUpKey()) && location.y > 0) {
            setLocation(getX(), getY() - PADEL_SPEED);
        }
        if (inputManager.isKeyDown(getDownKey()) && location.y < 1020) {
            setLocation(getX(), getY() + PADEL_SPEED);
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.dispose();
    }

    private int getUpKey() {
        return player == Player.LEFT ? KeyEvent.VK_W : KeyEvent.VK_UP;
    }

    private int getDownKey() {
        return player == Player.LEFT ? KeyEvent.VK_S : KeyEvent.VK_DOWN;
    }
}
