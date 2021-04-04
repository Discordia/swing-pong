package edu.game.pong;

import java.awt.*;
import javax.swing.*;

public class PlayField extends JPanel {
    private int leftScore = 0;
    private int rightScore = 0;
    private int countdown = 3;

    public PlayField(final LayoutManager layout) {
        super(layout);
    }

    public void leftScored() {
        leftScore++;
        repaint();
    }

    public void rightScored() {
        rightScore++;
        repaint();
    }

    public void resetScore() {
        leftScore = 0;
        rightScore = 0;
        countdown = 3;
    }

    public boolean hasWinner() {
        return leftScore == 5 || rightScore == 5;
    }

    public boolean countdown() {
        boolean result = --countdown == 0;
        repaint();
        return result;
    }

    public void newRound() {
        countdown = 3;
    }

    public void showGameOver() {

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.LIGHT_GRAY);

        // draw dashed mid line
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10, 50}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        // draw score
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 52));
        g2d.drawString(Integer.toString(leftScore), 20, 50);
        g2d.drawString(Integer.toString(rightScore), 1700, 50);

        if (countdown > 0) {
            g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
            g2d.drawString(Integer.toString(countdown), 1720 / 2, 70);
        }

        g2d.dispose();
    }
}
