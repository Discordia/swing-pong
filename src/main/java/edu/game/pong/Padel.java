package edu.game.pong;

import java.awt.*;
import javax.swing.*;

public class Padel extends JComponent {
    private final InputManager inputManager;
    private final int upKey;
    private final int downKey;

    public Padel(final InputManager inputManager, final int upKey, final int downKey) {
        this.inputManager = inputManager;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update() {
        Point location = getLocation();
        if  (inputManager.isKeyDown(upKey) && location.y > 0) {
            setLocation(getX(), getY() - 20);
        }
        if (inputManager.isKeyDown(downKey) && location.y < 1000) {
            setLocation(getX(), getY() + 20);
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
