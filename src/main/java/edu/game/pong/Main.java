package edu.game.pong;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Pong pong = new Pong();
            pong.start();
        });
    }
}
